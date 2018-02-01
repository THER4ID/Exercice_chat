/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niwatorichan.ChatClient;

import com.niwatorichan.chatLib.ChatMessage;
import com.niwatorichan.terminal.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author NiwatoriChan
 */
public class Client {
    
    Socket socket;
    String serveraddress, username;
    int port;
    
    ObjectInputStream input;
    ObjectOutputStream output;


    public Client(String serveraddress, String username, int port) {
        this.serveraddress = serveraddress;
        this.username = username;
        this.port = port;
    }

    public boolean showMessage(String msg){
        if(msg!=null){
            Console.println(msg);
            return true;
        }
        return false;
    }

    
    public void sendMessage(ChatMessage msg) {

        try {

            output.writeObject(msg);

        }

        catch(IOException e) {

            showMessage("Exception lié à l'envoi au serveur " + e);

        }

    }


    public boolean start(){
        return true;
    }
    
}
