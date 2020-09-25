package model;

public class Posicao {
	private int x;
	private int y;
	private Peca peca; 
	
	
	
	public Posicao(int x, int y, Peca peca) {
		super();
		this.x = x;
		this.y = y;
		this.peca = peca;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Peca getPeca() {
		return peca;
	}
	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
