package teachat4;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.awt.event.ActionEvent; 
import javax.swing.*;
import java.awt.*;

public class ListaNegra extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea ta;
	private JButton btnSalvar;
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ListaNegra thisClass = null;
				try {
					thisClass = new ListaNegra();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
                thisClass.setTitle("Lista Negra de palavras do TeaChat");
                thisClass.setSize(350, 295);
            }
        });
    }
	
    public ListaNegra() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Lista Negra de palavras no TeaChat");
        this.setSize(350, 295);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        initialize();
    }

    private void initialize() throws IOException {
    	
    	JPanel northPanel = new JPanel();
    	
        // botão Lista Negra
        btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(this);
        northPanel.add(btnSalvar);
        add(northPanel, BorderLayout.NORTH);

    	
    	ta = new JTextArea("", 80, 80);
        JPanel centerPanel = new JPanel(new GridLayout(1,1));
        centerPanel.add(new JScrollPane(ta));
        ta.setEditable(true);
        ta.setBackground(Color.BLACK);
        add(centerPanel, BorderLayout.CENTER);
        ta.setForeground(Color.WHITE);
        
        // Abre arquivo de Lista Negra
        InputStream fos = new FileInputStream("listanegra.txt");
        InputStreamReader isr = new InputStreamReader(fos);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        int iLinha = 0;
        //int iTotLin = br.
        
        while (iLinha < 5000) {
        	if(!s.equals("null")) {
        		ta.append(s+"\n");
        	}
        	
        	try {s = br.readLine();}
        	catch (Exception e) {s = "";}
        	
        	//if(s.equals(null)) { s = "";}
        	iLinha = iLinha + 1;
        }
        br.close();
        isr.close();
        fos.close();

        //setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
    	// Escrita no Arquivo de Lista Negra
    	OutputStream fos = null;
		try {
			fos = new FileOutputStream("listanegra.txt");
			fos.write(ta.getText().getBytes());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//System.out.println("Lista Negra");		
	}
	
}
