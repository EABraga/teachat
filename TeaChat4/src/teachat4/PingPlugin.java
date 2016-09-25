package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

class PingPlugin implements Plugin{
	private InputProvider ip; 
	public void register(InputProvider i) { 
		this.ip = i; 
	}
	
	public String getButtonText() {
		return "ping";
	}
	
	protected String getInitialText() {
		return "127.0.0.1";
	}

	public ActionListener buttonClicked() throws HeadlessException, IOException { 
		JOptionPane.showMessageDialog(null, "Ping: "+metodo(getInitialText()));
		return null;
	} 

	public String getAppTitle() { 
		return "Ping"; 
	}

	@Override
	public String getInititalText() {
		// TODO Auto-generated method stub
		return "127.0.0.1";
	} 
	
	public String metodo(String ip) throws IOException{
        String resposta="";
        String comando="ping -c 1 "+ip;
        try {
            Scanner S = new Scanner( Runtime.getRuntime().exec(comando).getInputStream());  
            while(S.hasNextLine()) {  
               resposta+=S.nextLine()+"\n";  
            }
        } catch (IIOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Resposta PING:"+resposta);
        return resposta;
    }
	
	public int getQtdButtons() {
		return 1;
	}

	@Override
	public String getButtonText2() {
		return null;
	}

	@Override
	public int getDebug() {
		return 0;
	}

	@Override
	public ActionListener buttonClicked(String string) throws HeadlessException, IOException {
		System.out.print("Nome do botão: "+string);
		return null;
	}


}
