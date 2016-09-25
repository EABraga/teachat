package teachat4;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;

interface Plugin { 
	String getAppTitle(); 
	String getButtonText();
	String getButtonText2();
	String getInititalText();
	int getQtdButtons();
	int getDebug();
	ActionListener buttonClicked() throws HeadlessException, IOException ; 
	void register(InputProvider app);
	ActionListener buttonClicked(String string) throws HeadlessException, IOException; 
}	

