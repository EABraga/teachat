package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

class CalcPlugin implements Plugin { 
	private InputProvider ip; 

	public void register(InputProvider i) { 
		this.ip = i; 
	}

	
	public String getButtonText() { 
		return "calculate"; 
	} 

	public String getInititalText() { 
		return "10 / 2 + 6"; 
	} 

	public ActionListener buttonClicked() {
		 JOptionPane.showMessageDialog(null, "Teste de Botão da Calculadora");
		return null;
		//return "Apertou o botão";
	} 
	
	public String getAppTitle() { 
		return "Calculadora"; 
	} 

	private String calculate(String m) { //...
		return null;
	}

	public int getQtdButtons() {
		return 1;
	}


	@Override
	public String getButtonText2() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getDebug() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ActionListener buttonClicked(String string) throws HeadlessException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
