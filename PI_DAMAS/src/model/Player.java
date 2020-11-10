package model;

public class Player implements Comparable<Player>{
	private String nickname;
	private String password;
	private int numVitorias;
	private int numDerrotas;
	private int numEmpates;
	private int pontuation;
	private PieceColor pieceColor;
	
	

	
	
	public Player(String nickname, String password, int numVitorias,  int numEmpates, int numDerrotas, int pontuation) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.numVitorias = numVitorias;
		this.numDerrotas = numDerrotas;
		this.numEmpates = numEmpates;
		this.pontuation = pontuation;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getNumVitorias() {
		return numVitorias;
	}

	public void setNumVitorias(int numVitorias) {
		this.numVitorias += numVitorias;
	}

	public int getNumDerrotas() {
		return numDerrotas;
	}

	public void setNumDerrotas(int numDerrotas) {
		this.numDerrotas += numDerrotas;
	}

	public int getNumEmpates() {
		return numEmpates;
	}

	public void setNumEmpates(int numEmpates) {
		this.numEmpates += numEmpates;
	}

	public int getPontuation() {
		return pontuation;
	}

	public void setPontuation(int pontuation) {
		this.pontuation += pontuation;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public void setPieceColor(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}


	@Override
	public String toString() {
		return nickname + " PONTUAÇÃO: " + pontuation;
	}
	
	
	
	public void attData(int numVitoria, int numEmpate, int numDerrota, int pontuation) {
		this.numVitorias = numVitoria;
		this.numDerrotas = numDerrota;
		this.numEmpates = numEmpate;
		this.pontuation = pontuation;
		
	}
	
    public int compareTo(Player outra) {
        if (this.pontuation < outra.pontuation) {
          return 1;
        }

        if (this.pontuation> outra.pontuation) {
          return -1;
        }

        return 0;
      }

	public String getPassword() {
		return password;
	}
	
	
	
	
}
