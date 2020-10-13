package model;

public class Peca {
	//ATRIBUTO QUE IDENTIFICA A COR DA PEÇA
	private final int cor;
	
	
	private Boolean dama;
	
	//0 - REPRESENTA PRETO
	//1 - REPRESENTA BRANCO
	
	public Peca(int cor){
		this.cor = cor;
		dama = false;
	}


	public int getCor() {
		return cor;
	}


	public Boolean getDama() {
		return dama;
	}


	public void setDama(Boolean dama) {
		this.dama = dama;
	}
	
	
	
	
}
