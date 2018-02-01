/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niwatorichan.ChatServer;

import java.io.IOException;

/**
 *
 * @author NiwatoriChan
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server(1500);
        server.start();
    }
    
}
