package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

public class ServerPluginStarter {
	public static final boolean bDEBUG = false;
	
	public static void main(String[] args) throws IOException{ 
		new App2(new ServerPlugin()).setVisible(true);
	}

}
