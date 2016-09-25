package teachat4;
import java.io.IOException;

public class ServerGuiStarter {

	public static final boolean bDEBUG = false;
	
	public static void main(String[] args) throws IOException{ 
		new App2(new ServerGuiPlugin()).setVisible(true); 
	}

}
