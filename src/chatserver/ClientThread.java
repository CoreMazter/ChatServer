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

    String inputs;
    String[] input;
    public ClientThread(Socket clientSocket, BaseDeDatos BD, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        this.BD=BD;
        maxClientsCount = threads.length;
    }
    
    @Override
    public void run(){
        
        user = new Usuario();
        try{
            is= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            byte[] bytes;
                    
            while(user.getId()==0){
                System.out.println("asdjaosfjoia");
                while(clientSocket.getInputStream().available()==0);
                bytes=new byte[clientSocket.getInputStream().available()];
                
                clientSocket.getInputStream().read(bytes);
                inputs= new String(bytes);
                System.out.println("flñkasdmflksamdlkfm");
                System.out.println(" asdasd    ");
                input=inputs.split(" ");
                user.setNickname(input[1]);
                user.setPassword(input[2]);
                input[2]=input[2];
                switch(input[0]){
                    case "signIn":
                        user.setId(BD.insertUser(user.getNickname(), user.getPassword()));
                        System.out.println("USER:"+user.getId());
                        int grupo = BD.insertPertenencia(user.getId(), 1);
                        System.out.println("PERTENECE:"+grupo);
                        BD.updatePertenencia(grupo);
                        BD.deletePertenencia(grupo);
                    case "login":
                        user=BD.selectUser(user.getNickname());
                        System.out.println("*"+user.password.length()+"*");
                        System.out.println("*"+input[2].length()+"*");
                        if(!input[2].equals(user.password)){
                            user.setId(0);
                            os.print('0');
                        }else os.print('1');
                        break;
                }
                
            }
            while(true){
                
            }
        }catch(IOException ex){
        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                
 
    }
   
}   
