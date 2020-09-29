package controller;

import java.awt.EventQueue;


import model.Partida;
import view.ViewPartida;

public class ControllerPartida {
	//INSTANCIA DO MODEL
	private Partida game;
	//INSTANCIA DA VIEW
	private ViewPartida frame;
	

	public void init(ControllerPartida ctrl) {
		//CRIANDO A TELA DO JOGO
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//INSTANCIANDO A VIEW
					//PASSANDO UMA INSTANCIA DE SI MESMO PARA A VIEW
					frame = new ViewPartida(ctrl);
					frame.setVisible(true);
					frame.setSize(1024, 576);
					frame.posicionarPecas();
					
					//INSTANCIANDO O MODEL PARTIDA
					game = new Partida(ctrl);
					
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
	
	
	
	public void  selectPiece(int x, int y) {
		//NO MOMENTO EM QUE O USUARIO CLICA NUM CAMPO ESSA FUNÇÃO E DISPARADA ACIONANDO O MODEL PARTIDA PARA REALIZAR AS VALIDAÇÕES
		game.selectPiece(x, y);
	}
	public void movePiece(int x, int y, int destinyX, int destinyY) {
		//MANDA PARA A VIEW
		frame.movePiece(x, y, destinyX, destinyY);
	}
	

}
