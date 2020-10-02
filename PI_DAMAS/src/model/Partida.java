package model;

import controller.ControllerPartida;

public class Partida {
	// INSTANCIA DO TABULEIRO
	private Tabuleiro tabuleiro;
	// CAMPO SELECIONADO PELO USUARIO
	private Posicao selectedField;
	// ARRAY COM OS MOVIMENTOS POSSIVEIS
	private Posicao[] possibleMovements = new Posicao[8];
	// VARIAVEL PARA CONTROLAR A ULTIMA POSI��O DO ARRAY POSSIBLE MOVEMENTS
	private int i = 0;
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
	public void selectField(int x, int y) {
		/*
		 * VERIFICANDO SE EXISTEM MOVIMENTOS POSSIVEIS POIS SE NAO TIVER SIDO
		 * SELECIONADA NENHUMA PECA OU NAO NAO HAVER MOVIMENTOS POSSIVEIS PARA A PECA
		 * SELECIONADO TORNANDO ASSIM SEM SENTIDO SEGUIR COM AS VALIDA��ES DE MOVIMENTO
		 */
		if (possibleMovements[0] != null) {

			// ITERANDO PELO ARRAY DE MOVIMENTOS POSSIVEIS
			for (int cont = 0; cont < possibleMovements.length; cont++) {
				// VERIFICANDO SE EXISTEM UM MOVIMENTO POSSIVEL NESSA POSI��O DO ARRAY
				if (possibleMovements[cont] != null) {
					// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
					// MOVIMENTO POSSIVEL
					System.out.println(possibleMovements[cont].getX());
					System.out.println(possibleMovements[cont].getY());
					if (possibleMovements[cont].getX() == x && possibleMovements[cont].getY() == y) {
						System.out.println("Cai Aki");
						// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
						movePiece(selectedField.getX(), selectedField.getY(), x, y);
						// TODO LOGICA PARA DEFINIR COMO NULL TODOS OS MOVIMENTOS POSSIVEIS POIS O
						// MOVIMENTO JA FOI REALIZADO
						possibleMovements = new Posicao[8];
						break;
					}
				}
			}
			// SE NAO HOUVER MOVIMENTOS POSSIVEIS VALIDA-SE SE O CAMPO SELECIONADO CONTEM
			// UMA PE�A
		} else if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSI��O SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();
		}

	}

	private void movePiece(int x, int y, int destinyX, int destinyY) {
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(x, y, destinyX, destinyY);
		//TODO MANDAR PARA A MODEL DO TABULEIRO PARA REALIZAR O MOVIMENTO
		
	}

	private void verifyPossibleMoviments() {
		// REALIZA A VERIFICA��O E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS
		// VALIDOS

		verifyCaptureMovement();
		verifyNormalMovements();
	}

	private void verifyNormalMovements() {

		// VALIDA��O PARA PE�AS PRETAS
		if (selectedField.getPeca().getCor() == 0) {
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements[i] = new Posicao(selectedField.getX() + 1, selectedField.getY() - 1);
					i++;
				}
			}
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements[i] = new Posicao(selectedField.getX() - 1, selectedField.getY() - 1);
					i++;
				}
			}
			// VERIFICA��O PARA AS DEMAIS PE�AS
			else {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements[i] = new Posicao(selectedField.getX() + 1, selectedField.getY() - 1);
					i++;

				}
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements[i] = new Posicao(selectedField.getX() - 1, selectedField.getY() - 1);
					i++;
				}

			}
		}

	}

	private Boolean verifyCaptureMovement() {
		return true;
	}

}
