package model;

public class Peca {
	//ATRIBUTO QUE IDENTIFICA A COR DA PEÇA
	private final PieceColor cor;
	
	
	private Boolean dama;
	
	
	public Peca(PieceColor cor){
		this.cor = cor;
		dama = false;
	}


	public PieceColor getCor() {
		return cor;
	}


	public Boolean getDama() {
		return dama;
	}


	public void setDama(Boolean dama) {
		this.dama = dama;
	}
	
	
	
	
}
