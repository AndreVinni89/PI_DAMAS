package model;

public class Player {
	private String nickname;
	private String password;
	private int numVitorias;
	private int numDerrotas;
	private int numEmpates;
	private int pontuation;
	private CorPeca corPeca;
	
	public Player(String nickname, String password, int numVitorias,  int numEmpates, int numDerrotas, int pontuation) {
		super();
		this.nickname = nickname;
		this.setPassword(password);
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

	public CorPeca getCorPeca() {
		return corPeca;
	}

	public void setCorPeca(CorPeca corPeca) {
		this.corPeca = corPeca;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return nickname + " PONTUAÇÃO: " + pontuation;
	}
	

	public String toString2() {
		return nickname + " PONTUAÇÃO: " + pontuation + "Vitorias " + numVitorias+ "empates " + numEmpates + "derrotas " + numDerrotas;
	}
	
	
	public void attData(int numVitoria, int numEmpate, int numDerrota, int pontuation) {
		this.numVitorias = numVitoria;
		this.numDerrotas = numDerrota;
		this.numEmpates = numEmpate;
		this.pontuation = pontuation;
		
	}
	
	
}
