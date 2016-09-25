package teaChat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;

public class LeLog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea ta;
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LeLog thisClass = null;
				try {
					thisClass = new LeLog();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
                thisClass.setTitle("Leitura últimos LOGs");
                thisClass.setSize(350, 295);
            }
        });
    }
	
    public LeLog() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Leitura últimos LOGs");
        this.setSize(350, 295);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        initialize();
    }

    private void initialize() throws IOException {

        ta = new JTextArea("10 últimos LOGs do Servidor\n", 80, 80);
        JPanel centerPanel = new JPanel(new GridLayout(1,1));
        centerPanel.add(new JScrollPane(ta));
        ta.setEditable(false);
        ta.setBackground(Color.BLACK);
        add(centerPanel, BorderLayout.CENTER);
        ta.setForeground(Color.GREEN);
        
        // Abre arquivo de log do servidor
        InputStream fos = new FileInputStream("log_server.txt");
        InputStreamReader isr = new InputStreamReader(fos);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        int iLinha = 0;
        
        while (iLinha < 10) {
        	ta.append(s+"\n");
        	s = br.readLine();
        	if(s.equals(null)) { s = "";}
        	iLinha = iLinha + 1;
        }
        br.close();
        isr.close();
        fos.close();

        setVisible(true);    
    }
	
	
	
}
