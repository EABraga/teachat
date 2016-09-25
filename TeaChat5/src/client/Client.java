package client;

import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import geral.ChatMessage;
	 
/*
 * O Client pode ser executado o console ou na interface gráfica
*/
public class Client  {
 
    // for I/O
    private ObjectInputStream sInput;       // to read from the socket
    private ObjectOutputStream sOutput;     // to write on the socket
    private Socket socket;
 
    // Se usa Interface Gráfica ou não
    private static ClientGui cg;
     
    // the server, the port and the username
    private String server, username, cor;
    private int port;
	public String usuario;
	 
    /*
     *  Constructor called by console mode
     *  server: the server address
     *  port: the port number
     *  username: the username
     */

    Client(String server, int port, String username) {
        // which calls the common constructor with the GUI set to null
        this(server, port, username, null, null);
    }
 
    /* Constructor call when used from a GUI in console mode the ClienGUI parameter is null */
    Client(String server, int port, String username, ClientGui cg, String cor) {
        this.server = server;
        this.port = port;
        this.username = username;
        usuario = username;
        // save if we are in GUI mode or not
        Client.cg = cg;
        this.cor = cor;
    }
	     
    /* To start the dialog  */
    public boolean start() throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
       // try to connect to the server
        try {
            socket = new Socket(server, port);
        }
        // if it failed not much I can so
        catch(Exception ec) {
            display("Error connectiong to server:" + ec);
            return false;
        }
         
        String msg = "Conexão acatada " + socket.getInetAddress() + ":" + socket.getPort();
        display(msg);
     
        /* Creating both Data Stream */
        try
        {
            sInput  = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }
 
        // creates the Thread to listen from the server
        new ListenFromServer().start();
        // Send our username to the server this is the only message that we
        // will send as a String. All other messages will be ChatMessage objects
        try
        {
        	sOutput.writeObject(username);
        }
        catch (IOException eIO) {
            display("Exception doing login : " + eIO);
            disconnect();
            return false;
        }
        // success we inform the caller that it worked
        return true;
    }
 
    /*
     * To send a message to the console or the GUI
     */
    private void display(String msg) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if(cg == null)
            System.out.println(msg);      // println in console mode
        else
            cg.append(msg + "\n",cor);      // append to the ClientGUI JTextArea (or whatever)
	    }
	     
    /*
     * To send a message to the server
     */
    void sendMessage(ChatMessage msg) throws FileNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        try {
        	System.out.println("Client linha 112");
        	System.out.println("MSG: "+msg);
        	sOutput.writeObject(msg);
        	//sOutput.writeObject("OLA");
        	System.out.println("Client linha 114");
        }
        catch(IOException e) {
            try {
				display("Exception writing to server: " + e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
 
    /*
     * When something goes wrong
     * Close the Input/Output streams and disconnect not much to do in the catch clause
     */
    private void disconnect() {
        try {
            if(sInput != null) sInput.close();
        }
        catch(Exception e) {} // not much else I can do
        try {
            if(sOutput != null) sOutput.close();
        }
        catch(Exception e) {} // not much else I can do
       try{
            if(socket != null) socket.close();
        }
        catch(Exception e) {} // not much else I can do
	         
        // inform the GUI
        if(cg != null)
            cg.connectionFailed();
             
    }
    /*
     * To start the Client in console mode use one of the following command
     * > java Client
     * > java Client username
     * > java Client username portNumber
     * > java Client username portNumber serverAddress
     * at the console prompt
     * If the portNumber is not specified 1500 is used
     * If the serverAddress is not specified "localHost" is used
     * If the username is not specified "Anonymous" is used
     * > java Client
     * is equivalent to
     * > java Client Anonymous 1500 localhost
     * are eqquivalent
     *
     * In console mode, if an error occurs the program simply stops
     * when a GUI id used, the GUI is informed of the disconnection
     */
    public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        // default values
        int portNumber = 1500;
        String serverAddress = "localhost";
        String userName = "Anonymous";
 
        // depending of the number of arguments provided we fall through
        switch(args.length) {
            // > javac Client username portNumber serverAddr
            case 3:
                serverAddress = args[2];
            // > javac Client username portNumber
            case 2:
                try {
                    portNumber = Integer.parseInt(args[1]);
                }
                catch(Exception e) {
                    System.out.println("Invalid port number.");
                    System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
                    return;
                }
            // > javac Client username
            case 1:
                userName = args[0];
            // > java Client
            case 0:
                break;
	            // invalid number of arguments
	            default:
	                System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
	            return;
	        }
	        // create the Client object
	        Client client = new Client(serverAddress, portNumber, userName);
	        // test if we can start the connection to the Server
	        // if it failed nothing we can do
	        try {
				if(!client.start())
				    return;
			} catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        // wait for messages from user
	        Scanner scan = new Scanner(System.in);
	        // loop forever for message from the user
	        while(true) {
	            System.out.print("> ");
	            // read message from user
	            String msg = scan.nextLine();
	            // logout if message is LOGOUT
	            if(msg.equalsIgnoreCase("LOGOUT")) {
	                client.sendMessage(new geral.ChatMessage(ChatMessage.LOGOUT, "","",0));
	                // break to do the disconnect
	                break;
	            }
	            // message WhoIsIn
	            else if(msg.equalsIgnoreCase("WHOISIN")) {
	                client.sendMessage(new geral.ChatMessage(ChatMessage.WHOISIN, "","",0));              
	            }
	            else {              // default to ordinary message
	            	System.out.println("MSG_2: "+msg);
	            	if(Client.cg == null) {
	            		client.sendMessage(new geral.ChatMessage(ChatMessage.MESSAGE, msg, "",0));
	            	} else {
	            		client.sendMessage(new geral.ChatMessage(ChatMessage.MESSAGE, msg, "",0));
	            	}
	            }
	        }
	        // done disconnect
	        client.disconnect();   
	    }
	 
	    /*
	     * a class that waits for the message from the server and append them to the JTextArea
	     * if we have a GUI or simply System.out.println() it in console mode
	     */
	    class ListenFromServer extends Thread {
	 
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
	                        cg.append(msg, cor);
	                    }
	                }
	                catch(IOException e) {
	                    try {
							display("Server has close the connection: " + e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidKeyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalBlockSizeException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BadPaddingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchPaddingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    if(cg != null)
	                        cg.connectionFailed();
	                    break;
	                }
	                // can't happen with a String object but need the catch anyhow
	                catch(ClassNotFoundException e2) {
	                } catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    }
	
}

