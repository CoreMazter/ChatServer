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
        init();
        while (true){
            
        }
                
 
    }
    
    void init(){
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
                        BD.insertUser(user.getNickname(), user.getPassword());
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
        while(initSteps<4){
            try {
                while(clientSocket.getInputStream().available()==0);
                bytes=new byte[clientSocket.getInputStream().available()];
                clientSocket.getInputStream().read(bytes);
                command= new String(bytes);
                splitted=command.split("<s>");
                switch(splitted[0]){
                    case "friends":{
                        initSteps++;
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
