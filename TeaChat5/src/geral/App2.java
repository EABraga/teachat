package geral;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class App2 extends JFrame implements InputProvider { 

	private JTextField textfield; 
	private JButton btn01, btn02, btn03, btn04, btn05;
	private JTextField edt01, edt02, edt03, edt04, edt05;
	
	private Plugin plugin; 
	public App2(Plugin p) throws IOException { 
		this.plugin = p;  
		p.register(this); 
		init(); 
	} 

	protected void init() throws IOException { 
		
		// Painel Norte
		JPanel painelNorte = new JPanel(new GridLayout(1,2));
		painelNorte.setPreferredSize(new Dimension(500, 20));		
		painelNorte.add(new JLabel("Lbl 01: "));
		edt01 = new JTextField("valor");
        painelNorte.add(edt01);
        this.add(painelNorte);
        this.pack();
		
        // Painel Central
        JPanel painelCentral01 = new JPanel();
		// Botão 01
		JButton button = new JButton(); 
		button.setText(plugin.getButtonText());
		painelCentral01.add(button);
		button.addActionListener(plugin.buttonClicked());
		
		// Verifica qtd de Botões
		if(plugin.getQtdButtons()==2){
			JButton button2 = new JButton();
			button2.setText(plugin.getButtonText2());
			//contentPane.add(button2, BorderLayout.SOUTH);
		}		
		
		textfield = new JTextField(""); 
		textfield.setText(plugin.getInititalText()); 
		textfield.setPreferredSize(new Dimension(200, 20)); 
		//contentPane.add(textfield, BorderLayout.WEST); 
		extracted(button); 
		//this.setContentPane(contentPane); 
		this.pack();
		this.setLocation(100,100);		
		String sTitulo = plugin.getAppTitle();

		// DEBUG
		if (plugin.getDebug()==1) { 
			System.out.print(getClass());
			System.out.print("\n");
		}
		
		this.setTitle(sTitulo); 	// Original
		
		// Código para fechar a janela
	}

	private void extracted(JButton button)  {
		try {
			button.addActionListener( plugin.buttonClicked() );
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getInput() { 
		return textfield.getText();
	}
}