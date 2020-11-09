package controller;

import java.awt.EventQueue;
import java.util.List;

import model.PieceColor;
import model.Game;
import model.Player;
import model.Posicao;
import view.ViewGame;

public class ControllerGame {
	// INSTANCIA DO MODEL
	private Game game;
	// INSTANCIA DA VIEW
	private ViewGame view;
	private ControllerLogin ctrlogin1;
	private ControllerLogin ctrlogin2;
	private Player player1 = null;
	private Player player2 = null;
	private ControllerGame ctrl;

	public void init(ControllerGame ctrl) {
		this.ctrl = ctrl;
		ctrlogin1 = new ControllerLogin(ctrl);
		ctrlogin1.init(ctrlogin1);

		ctrlogin2 = new ControllerLogin(ctrl);
		ctrlogin2.init(ctrlogin2);

	}

	// CRIANDO A TELA DO JOGO
	public void initializeGame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// INSTANCIANDO O MODEL PARTIDA
					game = new Game(ctrl, player1, player2);

					// INSTANCIANDO A VIEW
					// PASSANDO UMA INSTANCIA DE SI MESMO PARA A VIEW
					view = new ViewGame(ctrl, player1, player2);
					view.setVisible(true);
					view.setSize(1024, 576);
					view.posicionarPecas();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public List<Posicao> selectPiece(int x, int y) {
		// NO MOMENTO EM QUE O USUARIO CLICA NUM CAMPO ESSA FUNÇÃO E DISPARADA ACIONANDO
		// O MODEL PARTIDA PARA REALIZAR AS VALIDAÇÕES
		return game.selectField(x, y);
	}

	public void movePiece(Posicao origin, Posicao destiny) {
		// MANDA PARA A VIEW
		view.movePiece(origin, destiny);
	}

	public void capturePiece(Posicao origin, Posicao destiny, List<Posicao> capturedPiece) {

		view.capturePiece(origin, destiny, capturedPiece);

	}

	public void sendObrigatedCaptureInfo(List<Posicao> possibleSelectedFields) {
		view.sendObrigatedCaptureInfo(possibleSelectedFields);

	}

	public void endGame(PieceColor victoryColor, Boolean empate) {
		view.endGame(victoryColor, empate);

	}

	public void cancelGame() {
		ctrlogin1.closeWindow();
		ctrlogin2.closeWindow();
	}

	
	

	public void fim(PieceColor branco, boolean b) {
		game.fim(branco, b);
		
	}

}
