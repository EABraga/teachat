package teachat4;

import javax.crypto.*;
import javax.crypto.spec.*;
 
public class Encripta {
    public static void main(String[] args) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] mensagem = "Teste".getBytes();
 
            // Usando chave de 128-bits (16 bytes)
            byte[] chave = "chAv3@di&16bYte5".getBytes();
            System.out.println("chave: " + new String(chave));
            System.out.println("Tamanho da chave: " + chave.length);
            
            System.out.println(" ");            
            System.out.println("Mensagem: " + new String(mensagem));
            System.out.println(" ");
            
            // Encriptando...
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] encrypted = cipher.doFinal(mensagem);
            System.out.println("Criptografado: "+new String(encrypted));
            
            // Decriptando...
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] decrypted = cipher.doFinal(encrypted);
 
            System.out.println("Descriptografado: "+new String(decrypted));
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}