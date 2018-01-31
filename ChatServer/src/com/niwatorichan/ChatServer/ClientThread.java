

package com.niwatorichan.ChatServer;

import com.niwatorichan.chatLib.ChatMessage;
import com.niwatorichan.chatLib.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientThread extends Thread{

    Socket socket;
    User user;
    ChatMessage cm;
    ObjectInputStream input;
    ObjectOutputStream output;

    public ClientThread(Socket socket, Server server){
            
            this.socket=socket;
            server.showMessage("Création des flux d'entrée / sortie");

            try
            {
                output = new ObjectOutputStream(socket.getOutputStream());
                input  = new ObjectInputStream(socket.getInputStream());

                String username = (String) input.readObject();
                user = server.addUser(username);

                server.showMessage(username + " s'est connecté.");

            }

            catch (IOException e) {

                server.showMessage("Incapable  de créer les flux d'entré / sortie : " + e);

            } catch (ClassNotFoundException e) {
            server.showMessage("Message reçu invalide : " + e);
        }
    }

    public void run(){

             boolean keepGoing = true;
            while(keepGoing) {


                try {

                    cm = (ChatMessage) input.readObject();

                }

                catch (IOException e) {

                    server.showMessage(user.username + " Exception reading Streams: " + e);

                    keepGoing=false;             

                }

                catch(ClassNotFoundException e2) {

                    keepGoing=false;

                }


                String message = cm.getMessage();

 
                if(keepGoing){
                switch(cm.getType()) {

 

                case MESSAGE:

                    server.broadcast(user.username + ": " + message);

                    break;

                case LOGOUT:

                    server.showMessage(user.username + " disconnected with a LOGOUT message.");

                    keepGoing = false;

                    break;

                case WHOISIN:

                    server.writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");

                    // scan al the users connected

                    for(int i = 0; i < al.size(); ++i) {

                        ClientThread ct = al.get(i);

                        server.writeMsg((i+1) + ") " + ct.username + " since " + ct.date);

                    }

                    break;

                }
}
    }

    public void close(){
        input.close();
        output.close();
        socket.close();
    }


}