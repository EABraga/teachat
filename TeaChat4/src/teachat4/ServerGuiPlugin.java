package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

public class ServerGuiPlugin implements Plugin{ 
	


	public String sTitulo = "TeaChat Server 0.4v";
	
	private InputProvider ip; 
	public void register(InputProvider i) { 
		this.ip = i; 
	}
	
	public String getButtonText() {
		return "Iniciar";
	}
	
	protected String getInitialText() {
		return "";
	}

	public ActionListener buttonClicked() throws HeadlessException, IOException { 
		//JOptionPane.showMessageDialog(null, "Ping: "+metodo(getInitialText()));
		return null;
	} 

	public String getAppTitle() { 
		return sTitulo; 
	}

	@Override
	public String getInititalText() {
		// TODO Auto-generated method stub
		return "";
	} 
	
	public int getQtdButtons() {
		return 2;
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
