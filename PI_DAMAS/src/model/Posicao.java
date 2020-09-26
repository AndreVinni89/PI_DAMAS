package model;

public class Posicao {
	private int x;
	private int y;
	private Peca peca; 
	private Boolean temPeca;
	
	
	
	
	
	public Boolean getTemPeca() {
		return temPeca;
	}

	public void setTemPeca(Boolean temPeca) {
		this.temPeca = temPeca;
	}

	public Posicao(int x, int y, Peca peca) {
		super();
		this.x = x;
		this.y = y;
		this.peca = peca;
		this.temPeca = true;
		
	}
	
	public Posicao(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.temPeca = false;
		
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
