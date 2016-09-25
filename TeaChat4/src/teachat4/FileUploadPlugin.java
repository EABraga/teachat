package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileUploadPlugin  implements Plugin{
	
	private InputProvider ip;
	private int iQtdButtons = 2;
	
	public void register(InputProvider i) { 
		this.ip = i; 
	}

	public String getButtonText() {
		return "Navegar...";
	}

	public String getButtonText2() {
		return "Upload";
	}

	protected String getInitialText() {
		return "";
	}

	public ActionListener buttonClicked() throws HeadlessException, IOException {		
		clicou();
		return null;
	} 

	public String getAppTitle() { 
		return "Upload de Arquivo"; 
	}

	public String getInititalText() {		
		return "";
	} 

	public int getQtdButtons() {
		return iQtdButtons;
	}

	@Override
	public int getDebug() {
		return 0;
	}
	
	public void clicou() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);
	}

	@Override
	public ActionListener buttonClicked(String string) throws HeadlessException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Nome do botão: "+string);
		return null;
	}

	
}
