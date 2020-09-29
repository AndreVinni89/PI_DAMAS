package model;

import controller.ControllerPartida;

public class Partida {
	private Tabuleiro tabuleiro;
	private Posicao selectedField;
	private Posicao[] possibleMovements = new Posicao[8];
	private ControllerPartida controller;
	
	

	public Partida(ControllerPartida controller) {
		this.controller = controller;
		this.tabuleiro = new Tabuleiro();

	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	public void selectPiece(int x, int y) {	
		if (possibleMovements[0] != null) {
			for(int cont = 0; cont<possibleMovements.length; cont++) {
				if(possibleMovements[cont] != null) {
					if(possibleMovements[cont].getX() == x && possibleMovements[cont].getY() == y) {
						movePiece(selectedField.getX(), selectedField.getY(), x, y);
					}
				}

			}
			
		}
		else if(tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			selectedField = tabuleiro.getTabuleiro()[x][y];
			verifyPossibleMoviments();
		}


	}
	
	private void movePiece(int x, int y, int destinyX, int destinyY) {
		controller.movePiece(x, y, destinyX, destinyY);
	}
	

	private void verifyPossibleMoviments() {
		
		
		possibleMovements[0] = new Posicao(5, 4);
		
		/*if( tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getTemPeca() == false && 
				tabuleiro.getTabuleiro()[selectedField.getX() + 2][selectedField.getY() - 2].getTemPeca() == false) {
			//TODO
			

			
		}*/
		
		
		
	}

	

}
