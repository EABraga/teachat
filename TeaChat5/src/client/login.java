package client;

import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.*;

import java.awt.*;

public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ClientGui cg;
	int controla;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login thisClass = new login();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
                thisClass.setTitle("Login TeaChat v.0.2");
                thisClass.setSize(289, 195);
            }
        });
    }

    public login() {
        super("Login");
        initialize();
    }


    private void initialize() {
        this.setSize(290, 180);
        this.setTitle("Login");
        this.setVisible(true);
        Container tela = getContentPane();
        tela.setLayout(null);
        this.setContentPane(tela);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // LABELS
        JLabel titulolbl = new JLabel("Entre com seu usuário e senha");
        tela.add(titulolbl);
        titulolbl.setBounds(10, 5, 290, 15);

        JLabel loginlbl = new JLabel("Usuário: ");
        tela.add(loginlbl);
        loginlbl.setBounds(10, 35, 80, 20);

        JLabel senhalbl = new JLabel("Senha.: ");
        tela.add(senhalbl);
        senhalbl.setBounds(10, 60, 60, 20);

        // TEXTFIELDS
        final JTextField loginTxt = new JTextField(6);
        tela.add(loginTxt);
        loginTxt.setBounds(75, 35, 90, 20);


        final JTextField senhaTxt = new JPasswordField(6);
        tela.add(senhaTxt);
        senhaTxt.setBounds(75, 60, 90, 20);


        // BOTÕES
        JButton okbtn = new JButton("OK");
        tela.add(okbtn);
        okbtn.setBounds(30, 95, 80, 30);
        okbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {


            	//EVENTO QUE FAZ O CONTROLE DE LOGIN
                String senha = new String(((JPasswordField) senhaTxt).getPassword());

                if (loginTxt.getText().equals("") || senhaTxt.getText().equals(""))// se login e senha em branco
                	JOptionPane.showMessageDialog(null,"Campos usuário e senha são obrigatórios");//mensagem

                else //senao
                {

                	// Debug
                	System.out.println( senha );
                   
                	if(senha.equals("123456")) 
                	{
                		//JOptionPane.showMessageDialog(null,"Login Efetuado com Sucesso! Aguarde abertura do TeaChat.");
                		
                		// Chama tela principal do TeaChat
                		ClientGui cg = new ClientGui("localhost", 1500);
                		// Esconde Tela de login
                		dispose();// fecha o formulario de login ao entrar no principal
                		
                	}
                	else
                	{
                		JOptionPane.showMessageDialog(null, "Dados incoretos.");
                		loginTxt.requestFocus();
                	}

                }
            }

        });
        
        JButton cancelarbtn = new JButton("Cancelar");
        tela.add(cancelarbtn);
        cancelarbtn.setBounds(130, 95, 100, 30);
        cancelarbtn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                dispose(); 
            } 
        }); 




    }
}