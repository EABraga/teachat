package teaChat;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.*;
 
public class EncriptaDecriptaDES{

	public byte[] bRetorno;
	public String sRetorno;
	private String sCriptTmp;
	private byte[] chave;
	
	// constructor
    //EncriptaDecriptaDES(String sTexto, String sSenha, int iMao ,String sCripto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
	public static void EncriptaDecriptaDES() {}
	
	public void setCripty(String sTexto, String sSenha, String sCripto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
    	/*
    	 * sTexto	= Mensagem a ser criptografada
    	 * sSenha	= Senha para criptografar a mensagem
    	 * sCripto 	= tipo de criptografia ("AES"...)
    	 */

		// Imprime parâmetros recebidos
		//System.out.println("sTexto.: "+sTexto);
		//System.out.println("sSenha.: "+sSenha);
		//System.out.println("sCripto: "+sCripto);
		
   		// Define DEFAULT
   		// ---------------------------------------------------------------------------------
    		
   		// Define senha padrão se não houver
   		if(sSenha.length()<16){ chave = "chAv3@di&16bYte5".getBytes(); } // Usando chave de 128-bits (16 bytes) 
   		else { chave = sSenha.getBytes(); }

   		if(sCripto.length()< 3){ sCriptTmp = "AES"; } 
   		else { sCriptTmp = sCripto; }
    		
		// Encriptando...
   		Cript(chave, sCriptTmp, sTexto);
   		
    }
   	
	void setUnCripty(byte[] bTexto, String sSenha, String sCripto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		
   		// Define DEFAULT
   		// ---------------------------------------------------------------------------------
    		
   		// Define senha padrão se não houver
   		if(sSenha.length()<16){ chave = "chAv3@di&16bYte5".getBytes(); } // Usando chave de 128-bits (16 bytes) 
   		else { chave = sSenha.getBytes(); }

   		if(sCripto.length()< 3){ sCriptTmp = "AES"; } 
   		else { sCriptTmp = sCripto; }
    		
		// Encriptando...
   		UnCript(chave, sCriptTmp, bTexto);
	}
   		
   	void Cript(byte[] bChave, String sTipoCript, String sMensagem) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
   		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
   		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bChave, sTipoCript));
	    byte[] encrypted = cipher.doFinal(sMensagem.getBytes());
		bRetorno = encrypted;   		
   	}
   	   	
   	void UnCript(byte[] bChave, String sTipoCript, byte[] sMensagem) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
   		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
   		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bChave, sTipoCript));
	    byte[] uncrypted = cipher.doFinal(sMensagem);
		sRetorno = new String(uncrypted);   		
   	}
   	   	
   		
    byte[] getCript() {
        return bRetorno;
    }

    String getUnCript() {
        return sRetorno;
    }
    
}