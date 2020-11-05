package controller;

import java.awt.EventQueue;
import java.util.List;

import model.CorPeca;
import model.Partida;
import model.Player;
import model.Posicao;
import view.ViewPartida;

public class ControllerPartida {
	// INSTANCIA DO MODEL
	private Partida game;
	// INSTANCIA DA VIEW
	private ViewPartida view;
	private ControllerLogin ctrlogin1;
	private ControllerLogin ctrlogin2;
	private Player player1 = null;
	private Player player2 = null;
	private ControllerPartida ctrl;

	public void init(ControllerPartida ctrl) {
		this.ctrl = ctrl;
		ctrlogin1 = new ControllerLogin(ctrl);
		ctrlogin1.init(ctrlogin1);

		ctrlogin2 = new ControllerLogin(ctrl);
		ctrlogin2.init(ctrlogin2);

	}

	// CRIANDO A TELA DO JOGO
	public void iniciarJogo() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// INSTANCIANDO O MODEL PARTIDA
					game = new Partida(ctrl, player1, player2);

					// INSTANCIANDO A VIEW
					// PASSANDO UMA INSTANCIA DE SI MESMO PARA A VIEW
					view = new ViewPartida(ctrl, player1, player2);
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

	public void movePiece(Posicao origem, Posicao destino) {
		// MANDA PARA A VIEW
		view.movePiece(origem, destino);
	}

	public void capturePiece(Posicao origem, Posicao destino, List<Posicao> capturedPiece) {

		view.capturePiece(origem, destino, capturedPiece);

	}

	public void sendObrigatedCaptureInfo(List<Posicao> possibleSelectedFields) {
		view.sendObrigatedCaptureInfo(possibleSelectedFields);

	}

	public void endGame(CorPeca corVitoria, Boolean empate) {
		view.endGame(corVitoria, empate);

	}

	public void cancelarPartida() {
		ctrlogin1.fecharjanela();
		ctrlogin2.fecharjanela();
	}

}
