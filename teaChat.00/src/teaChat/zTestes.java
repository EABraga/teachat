package teaChat;

import java.io.IOException;

public class zTestes {
	public static void main(String[] args) throws IOException {

		String sPalavra = "vá tomar no ovelha viadinho";
		String sRetorno = "";
		Filtro f;
		
		f = new Filtro();
		sRetorno = f.aplicaFiltro(sPalavra);
		System.out.println("Filtro Aplicado retorno: "+sRetorno);
		
	}
	
}
