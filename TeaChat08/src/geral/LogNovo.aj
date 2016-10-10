package geral;

public aspect LogNovo {

	pointcut LN() : call( void geral.ChatMessage.getMessage(*) );

	// Eventos
	after(): LN() {
		System.out.print("....Chamada do PointCut em After.");
	}

	before(): LN() {
		System.out.print("....Chamada do PointCut em Before.");
	}	
	
}