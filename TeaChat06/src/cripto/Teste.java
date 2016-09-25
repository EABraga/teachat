package cripto;

import java.security.InvalidKeyException; 
import java.security.NoSuchAlgorithmException; 

import javax.crypto.BadPaddingException; 
import javax.crypto.IllegalBlockSizeException; 
import javax.crypto.NoSuchPaddingException; 

public  class  Teste {
	

    public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

    	EncriptaDecriptaDES ed;
    	String sTxt = "Qual será a fase lunar de hoje?";
    	byte[] yCrp;
    			
    	System.out.println("Exemplo de uso do sistema de Criptografia");
    	System.out.println("-----------------------------------------");
    	System.out.println("");
    	System.out.println("Texto Puro: "+sTxt);
    	
    	ed = new EncriptaDecriptaDES();
    	ed.setCripty(sTxt, "", "");
    	yCrp = ed.getCript();
    	
    	System.out.println("Texto Criptografado (com senha padrão): "+yCrp.toString());
    	//System.out.println(yCrp);
    	
    }


}
