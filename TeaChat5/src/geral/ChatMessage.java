package geral;

import java.io.*;
/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server.
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no
 * need to count bytes or to wait for a line feed at the end of the frame
 */
public class ChatMessage implements Serializable {
 
	protected static final long serialVersionUID = 1112122200L;
 
    // The different types of message sent by the Client
    // WHOISIN to receive the list of the users connected
    // MESSAGE an ordinary message
    // LOGOUT to disconnect from the Server

    public static final int WHOISIN = 0;

	public static final int MESSAGE = 1;

	public static final int LOGOUT = 2;
    private int type;
    private String message;
	public Filtro fi;
	public int iCripto;
    private byte[] bMsg;

    // Com Objeto
    ChatMessage(int type, String message) throws IOException {
    	System.out.println("ChatMessage )---> OBJETO");
    	this.type = type;
        this.message = message;        
    }

    // constructor SEM Criptografia
    public ChatMessage(int type, String message, String cor, int iCripto) throws IOException {
    	//System.out.println("ChatMessage )---> SEM CRIPTO");
    	Filtro fi = new Filtro();
    	this.type = type;
        //this.message = message;
    	this.message = fi.aplicaFiltro(message);        
    }
    
    // constructor COM Criptografia
    public ChatMessage(int type, String message, byte[] bMsg, String cor, int iCripto) throws IOException {
    	//System.out.println("ChatMessage )---> COM CRIPTO");
    	fi = new Filtro();
    	this.type = type;        
        this.bMsg = bMsg;
    }
    
    // getters
    public int getType() {
        return type;
    }
    
    // Sem criptografia
    public String getMessage() {	
    	System.out.println("CM )--> getMessage: "+message);
    	//System.out.println(iCripto);
    	//return fi.aplicaFiltro(this.message);
    	return message;
    }

	// Mensagem Criptografada
	byte[] getbMessage() {
		return this.bMsg;		
	}
}