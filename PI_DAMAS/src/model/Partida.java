package model;


public class Partida {
	private Tabuleiro tabuleiro;

	public Partida() {
		this.tabuleiro = new Tabuleiro();

	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void selectPiece(int x, int y) {
		
		
		System.out.printf("Selecionou a pe�a: X: %d  Y: %d", x, y );
		
		
		
	}

	

}
