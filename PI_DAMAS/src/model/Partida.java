package model;


public class Partida {
	private Tabuleiro tabuleiro;

	public Partida() {
		this.tabuleiro = new Tabuleiro();
		this.tabuleiro.print();
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	


}
