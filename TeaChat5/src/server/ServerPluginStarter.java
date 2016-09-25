package server;
import java.io.IOException;

public class ServerPluginStarter {
	public static final boolean bDEBUG = false;
	
	public static void main(String[] args) throws IOException { 
		new geral.App2(new ServerPlugin()).setVisible(true);
	}

}
