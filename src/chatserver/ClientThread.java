/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class ClientThread extends Thread {
    private Usuario user= null;
    private BufferedReader is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ClientThread[] threads;
    private int maxClientsCount;
    private BaseDeDatos BD;
    public ClientThread(Socket clientSocket, BaseDeDatos BD, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        this.BD=BD;
        maxClientsCount = threads.length;
        try {
            os = new PrintStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run(){
        byte[] bytes;
        String command;
        String[] splitted;
        init();
        while (true){
            try {
                if(clientSocket.getInputStream().available()!=0){
                    bytes=new byte[clientSocket.getInputStream().available()];
                    clientSocket.getInputStream().read(bytes);
                    command= new String(bytes);
                    splitted=command.split("<s>");
                    switch(splitted[0]){
                        case "mensaje":{
                            switch(splitted[1]){
                                case "amigo":{
                                    
                                for (ClientThread thread : threads) {
                                    if(thread!=this&&thread!=null){
                                        if(thread.user.getId()==Integer.parseInt(splitted[2])){
                                            thread.os.print("mensaje<s>amigo<s>"+user.getId()+"<s>"+splitted[3]);
                                        }
                                    }
                                }
                                    break;
                                }
                                case "grupo":{{
                                    for (ClientThread thread : threads) {
                                    if(thread!=this){
                                        if(thread.user.getId()==Integer.parseInt(splitted[2])){
                                            thread.os.print("mensaje<s>amigo<s>"+user.getId()+"<s>"+splitted[3]);
                                        }
                                    }
                                }
                                    
                                }
                                    break;
                                }
                                case "noamigo":{
                                    break;
                                }
                            }
                            break;
                        }
                        case "solicitud":{
                            switch(splitted[1]){
                                case "amigo":{
                                    break;
                                }
                                case "grupo":{
                                    break;
                                }
                            }
                            break;
                        }
                        case "aceptar":{
                            switch(splitted[1]){
                                case "amigo":{
                                    break;
                                }
                                case "grupo":{
                                    break;
                                }
                            }
                            break;
                        }
                        case "rechazar":{
                            switch(splitted[1]){
                                case "amigo":{
                                    break;
                                }
                                case "grupo":{
                                    break;
                                }
                            }
                            break;
                        }
                    }
                
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    void init(){
        final ArrayList<Usuario> online=new ArrayList<>();
        user = new Usuario();
        byte[] bytes;
        String command;
        String[] splitted;
        while(user.getId()==0){
            try {
                while(clientSocket.getInputStream().available()==0);
                bytes=new byte[clientSocket.getInputStream().available()];
                clientSocket.getInputStream().read(bytes);
                command= new String(bytes);
                splitted=command.split(" ");
                user.setNickname(splitted[1]);
                user.setPassword(splitted[2]);
                switch(splitted[0]){
                    case "signIn":
                        user.setId(BD.insertUser(user.getNickname(), user.getPassword()));
                        if(user.getId()!=0)
                            os.print('1');
                        else
                            os.print('0');
                        break;
                    case "login":
                        user=BD.selectUser(user.getNickname());
                        if(!splitted[2].equals(user.password)){
                            user.setId(0);
                            os.print('0');
                        }else os.print('1');
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int initSteps=0;
        while(initSteps<3){
            try {
                while(clientSocket.getInputStream().available()==0);
                bytes=new byte[clientSocket.getInputStream().available()];
                clientSocket.getInputStream().read(bytes);
                command= new String(bytes);
                splitted=command.split("<s>");
                switch(splitted[0]){
                    case "friends":{
                        ArrayList<Amigos> amigos=BD.selectAllAmigos(user.getId());
                        os.print("<amigos>");
                        amigos.forEach((amigo)->{
                            ArrayList<MensajesAmigos> mensajes=BD.selectAllMensajesAmigos(amigo.id);
                            os.print("<amigo>");
                            os.print("<id>");
                            os.print(amigo.id_u1==user.getId()?amigo.id_u2:amigo.id_u1+"");
                            os.print("</id>");
                            os.print("<alias>");
                            os.print(amigo.id_u1==user.getId()?amigo.alias2:amigo.alias1);
                            os.print("</alias>");
                            os.print("<mensajes>");
                            mensajes.forEach((mensaje)->{
                                os.print("<mensaje>");
                                os.print("<origen>");
                                os.print(""+(mensaje.getUsuario()==user.getId()?0:mensaje.getUsuario()));
                                os.print("</origen>");
                                os.print("<texto>");
                                os.print(mensaje.getMensaje());
                                os.print("</texto>");
                                os.print("<tiempo>");
                                os.print(""+mensaje.getTimestamp());
                                os.print("</tiempo>");
                                os.print("</mensaje>");
                            });
                            os.print("</mensajes>");
                            os.print("</amigo>");
                        });
                        os.print("</amigos>");
                        initSteps++;
                        break;
                    }
                    case "groups":{
                        ArrayList<Grupo> grupos=BD.selectAllGrupoAceptado(user.getId());
                        os.print("<grupos>");
                        grupos.forEach((grupo)->{
                            ArrayList<MensajesGrupo> mensajes=BD.selectAllMensajesGrupo(grupo.id_g);
                            os.print("<grupo>");
                            os.print("<id>");
                            os.print(grupo.id_g+"");
                            os.print("</id>");
                            os.print("<nombre>");
                            os.print(grupo.nombre);
                            os.print("</nombre>");
                            os.print("<mensajes>");
                            mensajes.forEach((mensaje)->{
                                os.print("<mensaje>");
                                os.print("<origen>");
                                os.print((mensaje.getUsuario()==user.getId()?"0":BD.selectUserById(mensaje.getUsuario())));
                                
                                os.print("</origen>");
                                os.print("<texto>");
                                os.print(mensaje.getMensaje());
                                os.print("</texto>");
                                os.print("<tiempo>");
                                os.print(""+mensaje.getTimestamp());
                                os.print("</tiempo>");
                                os.print("</mensaje>");
                            });
                            os.print("</mensajes>");
                            os.print("</grupo>");
                        });
                        os.print("</grupos>");
                        initSteps++;
                        break;
                    }
                    case "requests":{
                        
                        ArrayList<Grupo> grupos=BD.selectAllGrupoInvitado(user.getId());
                        os.print("<grupos>");
                        grupos.forEach((grupo)->{
                            os.print("<grupo>");
                            os.print("<id>");
                            os.print(grupo.id_g+"");
                            os.print("</id>");
                            os.print("<nombre>");
                            os.print(grupo.nombre);
                            os.print("</nombre>");
                            os.print("</grupo>");
                        });
                        os.print("</grupos>");                        
                        initSteps++;
                        break;
                    }
                    case "online":{
                        os.print("<online>");
                        for(ClientThread thread:threads)
                            if(thread!=this&&thread!=null)
                                online.add(thread.user);
                        online.forEach((user)->{
                            os.print("<usuario>");
                            os.print("<id>");
                            os.print(""+user.getId());
                            os.print("</id>");
                            os.print("<nombre>");
                            os.print(user.getNickname());
                            os.print("</nombre>");
                            os.print("</usuario>");

                        });
                        os.print("</online>");
                        break;
                    }
                    
                    case "offline":{
                        ArrayList<Usuario> usuarios = BD.selectAllUsers();
                        os.print("<offline>");
                        usuarios.forEach((user)->{
                            if(!online.contains(user)&&user.getId()!=this.user.getId()){
                                os.print("<usuario>");
                                os.print("<id>");
                                os.print(""+user.getId());
                                os.print("</id>");
                                os.print("<nombre>");
                                os.print(user.getNickname());
                                os.print("</nombre>");
                                os.print("</usuario>");
                            }
                        });
                        os.print("</offline>");
                        break;
                    }
                    default:
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
