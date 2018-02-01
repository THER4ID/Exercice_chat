
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

    public boolean getServerState(){
        return keepGoing;
    }

    public Server(int port){
        //this.ip=ip;
        this.port=port;
        listUser = new LinkedList<User>();
        UniqueID=0;
    }

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

    public LinkedList<User> getListUser() {
        return listUser;
    }

    public ArrayList<ClientThread> getListClientThread() {
        return listClientThread;
    }
    
    public void stop() throws IOException{
        keepGoing=false;

      for(ClientThread clientth : listClientThread){ 
            clientth.close(); 
     } 
      
     ct = null; 
    }
    
     public synchronized void broadcast(String message) throws IOException { 
 
        String messageLf = message + "\n"; 
 
        Console.print(messageLf); 
        // we loop in reverse order in case we would have to remove a Client 
 
        // because it has disconnected 
 
        for(int i = listClientThread.size(); --i >= 0;) { 

            // try to write to the Client if it fails remove it from the list 
 
            if(!listClientThread.get(i).writeMsg(message)) { 
 
                listClientThread.remove(i); 
 
                System.out.println("Disconnected Client " + listClientThread.get(i).user + " removed from list."); 
 
            } 
 
        } 
 
    } 
 
} 


