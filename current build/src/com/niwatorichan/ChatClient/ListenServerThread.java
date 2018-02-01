

package com.niwatorichan.ChatClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ListenServerThread extends Thread{

    ObjectInputStream input;
    ObjectOutputStream output;
    Client client;
    ClientGUI cg;

    public ListenServerThread(Client client) {
        this.client = client;
    }

    public ListenServerThread(Client client, ClientGUI cg) {
        this.client = client;
        this.cg = cg;
    }

     public void run() {

            while(true) {

                try {

                    String msg = (String) input.readObject();

                    // if console mode print the message and add back the prompt

                    if(cg == null) {

                        System.out.println(msg);

                        System.out.print("> ");

                    }

                    else {

                        cg.append(msg);

                    }

                }

                catch(IOException e) {

                    client.showMessage("Server has close the connection: " + e);

                    if(cg != null)

                        cg.connectionFailed();

                    break;

                }

                // can't happen with a String object but need the catch anyhow

                catch(ClassNotFoundException e2) {

                }

            }

    }
}