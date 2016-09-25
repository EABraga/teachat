package server;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;

//import teachat4.ServerGUI.ServerRunning;

public class ServerPlugin implements geral.Plugin{ 

	public String sTitulo = "TeaChat Server 0.4v";
	private Server server;
	
	private geral.InputProvider ip; 
	public void register(geral.InputProvider i) { 
		this.ip = i; 
	}
	
	public String getButtonText() {
		return "Iniciar";
	}
	
	protected String getInitialText() {
		return "";
	}

	public ActionListener buttonClicked() throws HeadlessException, IOException { 
        // create a new Server
        server = new Server(1500);
		server.start();         // should execute until if fails
		return null;
	} 

	public String getAppTitle() { 
		return sTitulo; 
	}

	public String getInititalText() {
		// TODO Auto-generated method stub
		return "";
	} 
	
	public int getQtdButtons() {
		return 2;
	}

	public String getButtonText2() {
		return null;
	}

	public int getDebug() {
		return 0;
	}

	@Override
	public ActionListener buttonClicked(String string) throws HeadlessException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
