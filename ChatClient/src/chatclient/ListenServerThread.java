

package chatclient;

public class ListenServerThread extends Thread{



     public void run() {

            while(true) {

                try {

                    String msg = (String) sInput.readObject();

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

                    display("Server has close the connection: " + e);

                    if(cg != null)

                        cg.connectionFailed();

                    break;

                }

                // can't happen with a String object but need the catch anyhow

                catch(ClassNotFoundException e2) {

                }

            }

}