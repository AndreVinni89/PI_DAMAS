package controller;

import java.awt.EventQueue;
import java.util.List;

import model.Partida;
import model.Player;
import model.Posicao;
import view.ViewPartida;

public class ControllerPartida {
	//INSTANCIA DO MODEL
	private Partida game;
	//INSTANCIA DA VIEW
	private ViewPartida view;
	
	private Player player1 = new Player("Andre", "123", 5, 3, 2, 500, 0);
	private Player player2 = new Player("Adailson", "123", 3, 2, 0, 1000, 1);

	public void init(ControllerPartida ctrl) {
		//CRIANDO A TELA DO JOGO
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//INSTANCIANDO O MODEL PARTIDA
					game = new Partida(ctrl, player1, player2);
					
					//INSTANCIANDO A VIEW
					//PASSANDO UMA INSTANCIA DE SI MESMO PARA A VIEW
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
	
	
	
	public List<Posicao> selectPiece(int x, int y) {
		//NO MOMENTO EM QUE O USUARIO CLICA NUM CAMPO ESSA FUNÇÃO E DISPARADA ACIONANDO O MODEL PARTIDA PARA REALIZAR AS VALIDAÇÕES
		return game.selectField(x, y);
	}
	public void movePiece(Posicao origem, Posicao destino) {
		//MANDA PARA A VIEW
		view.movePiece(origem, destino);
	}
	public void capturePiece(Posicao origem, Posicao destino, List<Posicao> capturedPiece) {
		
		view.capturePiece(origem, destino, capturedPiece);
		
	}



	public void sendObrigatedCaptureInfo(List<Posicao> possibleSelectedFields) {
		view.sendObrigatedCaptureInfo(possibleSelectedFields);
		
	}



	public void endGame(int corVitoria) {
		view.endGame(corVitoria);
		
	}
	

}
