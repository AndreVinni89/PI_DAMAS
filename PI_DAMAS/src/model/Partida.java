package model;

import controller.ControllerPartida;

public class Partida {
	// INSTANCIA DO TABULEIRO
	private Tabuleiro tabuleiro;
	// CAMPO SELECIONADO PELO USUARIO
	private Posicao selectedField;
	// ARRAY COM OS MOVIMENTOS POSSIVEIS
	private Posicao[] possibleMovements = new Posicao[8];
	// INSTANCIA DO CONTROLLER
	private ControllerPartida controller;

	// CONSTRUTOR
	public Partida(ControllerPartida controller) {
		// RECEBE A INSTANCIA DO CONTROLLER
		this.controller = controller;
		// INSTANCIA O TABULEIRO
		this.tabuleiro = new Tabuleiro();

	}

	// FUNCAO DISPARADA NO MOMENTO QUE O JOGADOR CLICA EM UM CAMPO
	public void selectPiece(int x, int y) {
		/*
		 * VERIFICANDO SE EXISTEM MOVIMENTOS POSSIVEIS POIS SE NAO TIVER SIDO
		 * SELECIONADA NENHUMA PECA OU NAO NAO HAVER MOVIMENTOS POSSIVEIS PARA A PECA
		 * SELECIONADO TORNANDO ASSIM SEM SENTIDO SEGUIR COM AS VALIDAÇÕES DE MOVIMENTO
		 */
		if (possibleMovements[0] != null) {
			// ITERANDO PELO ARRAY DE MOVIMENTOS POSSIVEIS
			for (int cont = 0; cont < possibleMovements.length; cont++) {
				// VERIFICANDO SE EXISTEM UM MOVIMENTO POSSIVEL NESSA POSIÇÃO DO ARRAY
				if (possibleMovements[cont] != null) {
					// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
					// MOVIMENTO POSSIVEL
					if (possibleMovements[cont].getX() == x && possibleMovements[cont].getY() == y) {
						// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
						movePiece(selectedField.getX(), selectedField.getY(), x, y);
						// TODO LOGICA PARA DEFINIR COMO NULL TODOS OS MOVIMENTOS POSSIVEIS POIS O
						// MOVIMENTO JA FOI REALIZADO
						possibleMovements[0] = null;
					}
				}

			}
		//SE NAO HOUVER MOVIMENTOS POSSIVEIS VALIDA-SE SE O CAMPO SELECIONADO CONTEM UMA PEÇA
		} else if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			//GUARDANDO NA VARIAVEL SELECTEDFIELD A POSIÇÃO SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();
		}

	}

	private void movePiece(int x, int y, int destinyX, int destinyY) {
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(x, y, destinyX, destinyY);
	}

	private void verifyPossibleMoviments() {
		//REALIZA A VERIFICAÇÃO E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS VALIDOS
		
		
		
		// MODO DE SALVAR AS POSIÇÕES VALIDAS
		possibleMovements[0] = new Posicao(5, 4);

		// TODO VERIFICAÇÃO DE MOVIMENTOS VALIDOS

		/*
		 * if( tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() -
		 * 1].getTemPeca() == false && tabuleiro.getTabuleiro()[selectedField.getX() +
		 * 2][selectedField.getY() - 2].getTemPeca() == false) {
		 * 
		 * 
		 * 
		 * 
		 * }
		 */

	}

}
