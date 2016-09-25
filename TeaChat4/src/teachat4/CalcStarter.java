package teachat4;
import java.io.IOException;

class CalcStarter { 
	public static final int iDEBUG = 0;
	
	public static void main(String[] args) throws IOException{ 
		new App(new CalcPlugin()).setVisible(true); 
	}
} 