/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niwatorichan.ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NiwatoriChan
 */
public class ConnectionThread extends Thread {
    
    Server server;
    ServerSocket serverSocket;

    public ConnectionThread(Server server, ServerSocket serverSocket){
        this.server = server;
        this.serverSocket = serverSocket;
    }

    public void run(){
        while(server.getServerState()){
            try {
                Socket socket = serverSocket.accept();
                ClientThread t = new ClientThread(socket,server);
                server.addClientThread(t);
            } catch (IOException ex) {
                server.showMessage("Erreur lors de la connection d'un client " + ex);
            }
        }

    try{
        serverSocket.close();
    } catch(Exception e) {

                server.showMessage("Quelque chose s'est mal passé en fermant le serveur : " + e);

            }
        
    }
}
