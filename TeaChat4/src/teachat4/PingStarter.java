package teachat4;
import java.io.IOException;

public class PingStarter {
	
	public static final boolean bDEBUG = false;
	
	public static void main(String[] args) throws IOException{ 
		new App(new PingPlugin()).setVisible(true); 
	}
}
