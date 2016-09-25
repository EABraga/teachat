package teachat4;
import java.io.IOException;

public class FileUploadStarter {
	
	public static final int iDEBUG = 0;
	
	public static void main(String[] args) throws IOException{ 
		new App(new FileUploadPlugin()).setVisible(true); 
	}
}
