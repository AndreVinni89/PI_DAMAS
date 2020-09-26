package controller;

import java.awt.EventQueue;
import java.awt.Frame;

import model.Partida;
import view.ViewPartida;

public class ControllerPartida {
	private Partida game;
	private ViewPartida frame;
	

	public void init(ControllerPartida ctrl) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewPartida(ctrl);
					frame.setVisible(true);
					frame.setSize(1024, 576);
					frame.posicionarPecas();
					
					
					game = new Partida(ctrl);
					
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
	
	
	
	public void  selectPiece(int x, int y) {
		game.selectPiece(x, y);
	}


}
