package model;

public class Posicao {

	// COORDENADAS
	private int x;
	private int y;

	// OBJETO PECA
	private Peca peca;

	// BOOLEAN QUE INDICA SE A POSIÇÃO TEM UMA PEÇA
	private Boolean temPeca;

	
	private int contCaptured=0;
	
	
	public int getContCaptured() {
		return contCaptured;
	}

	public void setContCaptured(int contCaptured) {
		this.contCaptured = contCaptured;
	}

	// CONSTRUTOR COM PECA
	public Posicao(int x, int y, Peca peca) {
		super();
		this.x = x;
		this.y = y;
		this.peca = peca;
		// DEFININDO O ATRIBUTO TEMPECA COMO TRUE
		this.temPeca = true;

	}

	// CONTRUTOR SEM PECA
	public Posicao(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		// COMO NAO FOI INFORMADO A PECA JA É DEFINIDO O ATRIBUTO TEMPECA COMO FALSE
		this.temPeca = false;

	}
	// GETTERS E SETTERS
	public Boolean getTemPeca() {
		return temPeca;
	}

	public void setTemPeca(Boolean temPeca) {
		this.temPeca = temPeca;
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
	
	@Override
	public String toString() {
		return "X: " + getX() +  " Y: " + getY();
	}
	

}
