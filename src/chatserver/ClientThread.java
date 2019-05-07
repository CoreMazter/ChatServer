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
    }
    
    @Override
    public void run(){
        try{
            user = new Usuario();
            is= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            String[] input;
            while(user.getId()==0){
                input = is.readLine().split(" ");
                user.setNickname(input[1]);
                user.setPassword(input[2]);
                switch(input[0]){
                    case "signIn":
                        BD.insertUser(user.getNickname(), user.getPassword());
                    case "login":
                        user=BD.selectUser(user.getNickname());
                        if(!user.getPassword().equals(input[2])){
                            user.setId(0);
                            os.print(false);
                        }else os.print(true);
                        break;
                }
                
            }
            while(true){
                
            }
        }
        catch(IOException ex){
            
        }
    }
   
}   
