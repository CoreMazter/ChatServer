/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class ChatServer {

    static ServerSocket serverSocket = null;
    static Socket clientSocket = null;
    static ClientThread[] threads = new ClientThread[18];
    
    public static void main(String[] args) {
        BaseDeDatos BD = new BaseDeDatos();
     try {
            serverSocket=new ServerSocket(5501);
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
            try{
                clientSocket=serverSocket.accept();
                for (int i = 0; i < 18; i++) {
                    if(threads[i]==null){
                        (threads[i]=new ClientThread(clientSocket, BD, threads)).start();
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
