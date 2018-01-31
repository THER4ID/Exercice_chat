
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

     for(ClientThread clientth : listClientThread){
            clientth.close();
     }
     
     ct = null;
     
    }
    

}
