package model;

import controller.ControllerPartida;

public class Partida {
	private Tabuleiro tabuleiro;
	private Posicao selectedField;
	private ControllerPartida controller;
	
	

	public Partida(ControllerPartida controller) {
		this.controller = new ControllerPartida();
		this.tabuleiro = new Tabuleiro();

	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	public void selectPiece(int x, int y) {	

		
		if(tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			selectedField = tabuleiro.getTabuleiro()[x][y];
			System.out.println(selectedField.getPeca());	
			verifyPossibleMoviments();
		}


	}

	private void verifyPossibleMoviments() {
		if( tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getTemPeca() == false && 
				tabuleiro.getTabuleiro()[selectedField.getX() + 2][selectedField.getY() - 2].getTemPeca() == false) {
			//TODO
			
			
		}
		
		
		
	}

	

}
