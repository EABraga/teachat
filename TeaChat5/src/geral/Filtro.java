package geral;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Filtro {
	
	public ArrayList<String> bl;
	
	// Construtor único
	Filtro() throws IOException{
		
		bl = new ArrayList<String>();		// blacklist
		
		// Carrega Black List de Palavras do arquivo "blacklist.txt"
        // ---------------------------------------------------------
        // Abre arquivo de Lista Negra
        InputStream fos = new FileInputStream("listanegra.txt");
        InputStreamReader isr = new InputStreamReader(fos);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        
        while (s != null) {        	
        	//System.out.println("s: "+s);
       		bl.add(s);
       		s = br.readLine();
        }
        br.close();
        isr.close();
        fos.close();
        System.out.println("Carreguei filtros.");
	}
	
	String aplicaFiltro(String sTexto){
		System.out.println("Inicio aplicar filtro.");
		Object  oWord;		// Recebe paravra do List (Set)
		String sTxt, sTmp;	// Recebe palava de parâmetro
		sTxt = sTexto;
		System.out.println("01. "+sTxt);
		Iterator<String> bli = bl.iterator();
		
		System.out.println("Inicio Loop no LIST do Filtro");
		while(bli.hasNext()) {
				
			oWord = bli.next();
			sTmp = oWord.toString();
			sTmp = sTmp.toLowerCase();
			
			System.out.println("sTxt: "+sTxt+" - sTmp: "+sTmp);
			
			if (sTxt.toLowerCase().contains(sTmp)) {			
				//System.out.println("***");
				sTxt = "*** Palavra inapropriada no texto ***";
			}
		}
		System.out.println("Fim Loop no LIST do Filtro");
		return sTxt;
	};
	
	
	
	
	
	
	
	
	
}
