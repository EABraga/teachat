package teaChat;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

/* O Client com interface Gráfica  */
public class ClientGui extends JFrame implements ActionListener {
	 
	    private static final long serialVersionUID = 1L;
	    // Primeiro será "Usuário:", depois "Menssagem"
	    private JLabel label;
	    // Entrada de  Username e depois das mensagens
	    private JTextField tf;
	    // to hold the server address an the port number
	    private JTextField tfServer, tfPort;
	    // to Logout and get the list of the users
	    private JButton login, logout, whoIsIn, leLog;
	    // for the chat room
	    private JTextArea ta;
	    // if it is for connection
	    private boolean connected;
	    // the Client object
	    private Client client;
	    // the default port number
	    private int defaultPort;
	    private String defaultHost;
	    
	    // ------------------------------------------------------------------------
		private JComboBox<Object> combo, combo2, cmbcripto;
		private Color cCorFundo, cCorFonte; 
		private String sCorFonte;
	    public String usuario;
	    public String sTexto;
	    public byte[] bTexto;
	    public int iCript;
	    public EncriptaDecriptaDES oCripty;
	    // ------------------------------------------------------------------------
		
	    
	    // Constructor connection receiving a socket number
	    ClientGui(String host, int port) {

	    	super("TeaChat Client");
	    	
	    	//new ClientGui("localhost", 1500);	    	
	    	
	        defaultPort = 1500;
	        defaultHost = "localhost";	        

	    	EncriptaDecriptaDES oCripty = new EncriptaDecriptaDES();
	        
	        // ------------------------------------------------------------------------------
	        // Define cores Default
	        cCorFundo = new Color(255,255,255);
	        sCorFonte = "RGB(0,0,0)";
	        cCorFonte = new Color(0,0,0);
	
 	        String[] coresStrings = { "Preto", "Branco", "Verde" };
			JComboBox<Object> jComboBox = new JComboBox<Object>(coresStrings);
			combo = jComboBox;
			String[] coresFundoStrings = { "Branco", "Preto", "Verde" };
			combo2 = new JComboBox<Object>(coresFundoStrings);
			String[] criptoList = { "Sem Cripto.", "AES"};
			cmbcripto = new JComboBox<Object>(criptoList);
			cmbcripto.enable(false);
			// ------------------------------------------------------------------------------
			
	        // The NorthPanel with:
	        JPanel northPanel = new JPanel(new GridLayout(3,1));
	        // the server name anmd the port number
	        JPanel serverAndPort = new JPanel(new GridLayout(1,5, 1, 3));
	        // the two JTextField with default value for server address and port number
	        tfServer = new JTextField(host);
	        tfPort = new JTextField("" + port);
	        tfPort.setHorizontalAlignment(SwingConstants.RIGHT);
	 
	        serverAndPort.add(new JLabel("End.Servidor: "));
	        serverAndPort.add(tfServer);
	        serverAndPort.add(new JLabel("Porta: "));
	        serverAndPort.add(tfPort);
	        serverAndPort.add(new JLabel(""));
			// -----------------------------------------------------------------------------------
	        // Cor da Letra
	        serverAndPort.add(new JLabel("Cor Letra: "));
	        serverAndPort.add(combo);
	        combo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                	String contents = (String)combo.getSelectedItem();
                	
                	if (contents == "Branco") { 
                			sCorFonte = "RGB(255,255,255)"; 
                			cCorFonte = new Color(255,255,255);
                	}
                	if (contents == "Preto") { 
                		sCorFonte = "RGB(0,0,0)"; 
                		cCorFonte = new Color(0,0,0);
                	}
                	if (contents == "Verde") { 
                		sCorFonte = "RGB(0,255,0)"; 
                		cCorFonte = new Color(0,255,0);
                	}
                	ta.setForeground( cCorFonte);
                	//System.out.println( sCorFonte );
                }
            });   	        
	        
	        // Cor de Fundo
	        serverAndPort.add(new JLabel("Cor Fundo: "));
	        serverAndPort.add(combo2);
	        combo2.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                	String contents = (String)combo2.getSelectedItem();
                	
                	if (contents == "Branco") { cCorFundo = new Color(255,255,255); }
                	if (contents == "Preto") { cCorFundo = new Color(0,0,0); }
                	if (contents == "Verde") { cCorFundo = new Color(0,255,0); }
                	ta.setBackground(cCorFundo);
                }
            });   	        
	        // -----------------------------------------------------------------------------------java
	        // adds the Server an port field to the GUI
	        northPanel.add(serverAndPort);
	 
	        // the Label and the TextField
	        label = new JLabel("Entre nome do usuário", SwingConstants.CENTER);
	        northPanel.add(label);
	        tf = new JTextField("Anônimo");
	        tf.setBackground(Color.WHITE);	        
	        northPanel.add(tf);
	        add(northPanel, BorderLayout.NORTH);
	 
	        // The CenterPanel which is the chat room
	        ta = new JTextArea("Bem vindo a sala de Chat\n", 80, 80);
	        JPanel centerPanel = new JPanel(new GridLayout(1,1));
	        centerPanel.add(new JScrollPane(ta));
	        ta.setEditable(false);
	        ta.setBackground(cCorFundo);
	        add(centerPanel, BorderLayout.CENTER);

	        // Botão de leitura de LOG (10 linhas)
	        leLog = new JButton("Visualiza Log Servidor");
	        leLog.addActionListener(this);
	        leLog.setEnabled(false);      // Só habilita depois do login
	        	        
	        // the 3 buttons
	        login = new JButton("Entrar");
	        login.addActionListener(this);
	        logout = new JButton("Sair");
	        logout.addActionListener(this);
	        logout.setEnabled(false);       // you have to login before being able to logout
	        whoIsIn = new JButton("Participantes");
	        whoIsIn.addActionListener(this);
	        whoIsIn.setEnabled(false);      // you have to login before being able to Who is in
	 
	        JPanel southPanel = new JPanel();
	        southPanel.add(leLog);					// Lê LOG do servidor
	        southPanel.add(login);					// Login
	        southPanel.add(logout);
	        southPanel.add(whoIsIn);
	        southPanel.add(new JLabel("Cripto: "));	// Criptografia
	        southPanel.add(cmbcripto);				// Criptografia
	        add(southPanel, BorderLayout.SOUTH);
	        
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(880, 600);
	        setVisible(true);
	        tf.requestFocus();
	 
	    }
	 
	    // called by the Client to append text in the TextArea
	    void append(String str, String sCor) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	    
	    	EncriptaDecriptaDES oCripty = new EncriptaDecriptaDES();
	    	
	    	// -----------------------------------------------------------------------------------------
	    	// Escrita no Arquivo de LOG
	    	OutputStream fos = new FileOutputStream("log_user_"+client.usuario+".txt");	    	
	        fos.write(ta.getText().getBytes()); //Escreve no objeto
			fos.flush(); 						//Descarrega Buffer de Escrita	    	
			fos.close();
			// -----------------------------------------------------------------------------------------			
	        // Verifica Criptografia	    			
        	String sTipoCript = (String)cmbcripto.getSelectedItem();	
        	
        	if(sTipoCript.equals("AES")) {
        		//16:04:41 Anônimo: K��ﭶ�^�+�DX:z
        		oCripty.setCripty(sTexto," "," ");
        		bTexto = oCripty.getCript();
        	}
        	
			// -----------------------------------------------------------------------------------------			
			ta.insert(str,0);
	        ta.setCaretPosition(ta.getText().length() - 1);
	        //sTexto = null;
	    }
	    
	    // called by the GUI is the connection failed
	    // we reset our buttons, label, textfield
	    void connectionFailed() {
	        leLog.setEnabled(false);
	    	login.setEnabled(true);
	        logout.setEnabled(false);
	        whoIsIn.setEnabled(false);
	        label.setText("Digite seu nome de usuário");
	        tf.setText("Anônimo");
	        // reset port number and host name as a construction time
	        tfPort.setText("" + defaultPort);
	        tfServer.setText(defaultHost);
	        // let the user change them
	        tfServer.setEditable(false);
	        tfPort.setEditable(false);
	        // don't react to a <CR> after the username
	        tf.removeActionListener(this);
	        connected = false;
	    }
	         
	    /*
	    * Button or JTextField clicked
	    */
	    public void actionPerformed(ActionEvent e) {
	        
	    	EncriptaDecriptaDES oCripty = new EncriptaDecriptaDES();
	    	
	    	Object o = e.getSource();
	        // if it is the Logout button
	        if(o == logout) {
	            try {
					client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, "","",0));
				} catch (FileNotFoundException e1) {
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
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            return;
	        }
	        
	        // if it the who is in button
	        if(o == whoIsIn) {
	            try {
					client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, "","",0));
				} catch (FileNotFoundException e1) {
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
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}              
	            return;
	        }

	        // ----------------------------------------------------------------------------
	        if(o == leLog) {

	        	try {
					LeLog ll = new LeLog();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	return;
	        }
	        
	        // ok it is coming from the JTextField
	        if(connected) {
	            sTexto = tf.getText();
	        	
	        	// just have to send the message
	            try {
	    			// -----------------------------------------------------------------------------------------
	    	        // Verifica Criptografia	    			
	            	String sTipoCript = (String)cmbcripto.getSelectedItem();	
	            	
	            	if(sTipoCript.equals("AES")) {
	            		//  String sTexto, String sSenha, int iMao ,String sCripto
	            		//EncriptaDecriptaDES oCripty = new EncriptaDecriptaDES(tf.getText(),"",1,"");
	            		System.out.println("");
	            		oCripty.setCripty(tf.getText(),"1","AES");
	            		bTexto = oCripty.getCript();
	            		iCript = 1;
//	            		System.out.println("bTexto.: "+ new String (bTexto));
	            	}
	            	
	    			// -----------------------------------------------------------------------------------------	            	
	            	//client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText(), sCorFonte, iCripto));
	            	if(iCript==1) {
	            		client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText(), bTexto, sCorFonte, iCript));
	            	} else {
	            		System.out.println("Entrei CLIENTGUI");
	            		client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, sTexto, sCorFonte, iCript));
	            		System.out.println("Saí CLIENTGUI");
	            	}
	            	
				} catch (FileNotFoundException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("327");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("331");
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("335");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}            
	            tf.setText("");
	            return;
	        }
	         
	 
	        if(o == login) {
	            // ok it is a connection request
	            String username = tf.getText().trim();
	            usuario = username;
	            // empty username ignore it
	            if(username.length() == 0)
	                return;
	            // empty serverAddress ignore it
	            String server = tfServer.getText().trim();
	            if(server.length() == 0)
	                return;
	            // empty or invalid port numer, ignore it
	            String portNumber = tfPort.getText().trim();
	            if(portNumber.length() == 0)
	                return;
	            int port = 0;
	            try {
	                port = Integer.parseInt(portNumber);
	            }
	            catch(Exception en) {
	                return;   // nothing I can do if port number is not valid
	            }
	 
	            // try creating a new Client with GUI
	            client = new Client(server, port, username, this, sCorFonte);
	            // test if we can start the Client
	            try {
					if(!client.start())
					    return;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	            tf.setText("");
	            label.setText("Digite sua mensagem");
	            connected = true;
	             
	            leLog.setEnabled(true);
	            // disable login button
	            login.setEnabled(false);
	            // enable the 2 buttons
	            logout.setEnabled(true);
	            whoIsIn.setEnabled(true);
	            // disable the Server and Port JTextField
	            tfServer.setEditable(false);
	            tfPort.setEditable(false);
	            // Action listener for when the user enter a message
	            tf.addActionListener(this);
	        }
	 
	    }
	 

		// to start the whole thing the server
/*	    public static void main(String[] args) {
	        new ClientGui("localhost", 1500);
	    }
*/
	    int getClientCripto(){
	    	return iCript;
	    }
   
	    
	}
