
package com.niwatorichan.ChatServer;
import com.niwatorichan.chatLib.User;
import com.niwatorichan.terminal.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author NiwatoriChan
 */

public class Server {

    //String ip;
    int port;
    LinkedList<User> listUser;
    ArrayList<ClientThread> listClientThread;
    boolean keepGoing;
    int UniqueID;
    ConnectionThread ct;


    public Server(int port){
        //this.ip=ip;
        this.port=port;
        listUser = new LinkedList<User>();
        UniqueID=0;
    }

    
    //Getters and Setters
    public LinkedList<User> getListUser() {
        return listUser;
    }

    public ArrayList<ClientThread> getListClientThread() {
        return listClientThread;
    }
    
    public boolean getServerState(){
        return keepGoing;
    }

    //Methodes
    private int GiveUniqueID(){
        return (this.UniqueID+=1);
    }
    public User addUser(String username){
        User user = new User();
        user.setUsername(username);
        user.setId(GiveUniqueID());
        listUser.add(user);
        return user;
    }
    public void addClientThread(ClientThread clientThread){
        listClientThread.add(clientThread);
        clientThread.run();
    }

    public void showMessage(String message){
        Console.println(message);
    }

    public void start() throws IOException{
        keepGoing = true;

        try{
            this.showMessage("Démarrage du serveur en cours...");

            ServerSocket serverSocket = new ServerSocket(port);

            ct = new ConnectionThread(this, serverSocket);
            ct.run();

        }
        catch(IOException e){
            this.showMessage("Impossible de démarrer le serveur : " + e);
        }
        
        
    }
    
    public void stop() throws IOException{
        
     keepGoing=false;

        for(ClientThread ct : listClientThread){
            ct.
        }
    }
    
    private synchronized void broadcast(String message) {

        
        String messageLf = message + "\n";

        // display message on console or GUI

        //sg représente le server GUI, donc ce qui concerne l'interface
        if(sg == null)

            System.out.print(messageLf);

        else

            sg.appendRoom(messageLf);     // append in the room window

         

        // we loop in reverse order in case we would have to remove a Client

        // because it has disconnected

        for(int i = listClientThread.size(); --i >= 0;) {

            ClientThread ct = listClientThread.get(i);

            // try to write to the Client if it fails remove it from the list

            if(!ct.writeMsg(messageLf)) {

                listClientThread.remove(i);

                System.out.println("Disconnected Client " + ct.user + " removed from list.");

            }

        }

    }

}

  


}
