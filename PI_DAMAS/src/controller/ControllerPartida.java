package controller;

import java.awt.EventQueue;


import model.Partida;
import view.ViewPartida;

public class ControllerPartida {
	//INSTANCIA DO MODEL
	private Partida game;
	//INSTANCIA DA VIEW
	private ViewPartida view;
	

	public void init(ControllerPartida ctrl) {
		//CRIANDO A TELA DO JOGO
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//INSTANCIANDO O MODEL PARTIDA
					game = new Partida(ctrl);
					
					//INSTANCIANDO A VIEW
					//PASSANDO UMA INSTANCIA DE SI MESMO PARA A VIEW
					view = new ViewPartida(ctrl);
					view.setVisible(true);
					view.setSize(1024, 576);
					view.posicionarPecas();
					

					
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
	
	
	
	public void  selectPiece(int x, int y) {
		//NO MOMENTO EM QUE O USUARIO CLICA NUM CAMPO ESSA FUNÇÃO E DISPARADA ACIONANDO O MODEL PARTIDA PARA REALIZAR AS VALIDAÇÕES
		game.selectField(x, y);
	}
	public void movePiece(int x, int y, int destinyX, int destinyY) {
		//MANDA PARA A VIEW
		view.movePiece(x, y, destinyX, destinyY);
	}
	

}
