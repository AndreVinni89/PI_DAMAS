package model;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerPartida;

public class Partida {
	// INSTANCIA DO TABULEIRO
	private Tabuleiro tabuleiro;
	// CAMPO SELECIONADO PELO USUARIO
	private Posicao selectedField;
	// ARRAY COM OS MOVIMENTOS POSSIVEIS
	List<Posicao> possibleMovements = new ArrayList<>();
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
		 * SELECIONADO TORNANDO ASSIM SEM SENTIDO SEGUIR COM AS VALIDAÇÕES DE MOVIMENTO
		 */
		if (possibleMovements.size() > 0) {

			// ITERANDO PELO ARRAY DE MOVIMENTOS POSSIVEIS
			for (int cont = 0; cont < possibleMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO POSSIVEL
				if (possibleMovements.get(cont).getX() == x && possibleMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					movePiece(selectedField.getX(), selectedField.getY(), x, y, selectedField.getPeca().getCor());
					//ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
					possibleMovements.clear();

					break;
				}
			}
			// SE NAO HOUVER MOVIMENTOS POSSIVEIS VALIDA-SE SE O CAMPO SELECIONADO CONTEM
			// UMA PEÇA
		} else if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSIÇÃO SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();
		}

	}

	private void movePiece(int x, int y, int destinyX, int destinyY, int cor) {
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(x, y, destinyX, destinyY, cor);
		tabuleiro.movePiece(x, y, destinyX, destinyY);
		//ZERANDO O VALOR O CAMPO SELECIONADO POIS O MOVIMENTO JA FOI REALIZADO
		selectedField = null; 
		// TODO MANDAR PARA A MODEL DO TABULEIRO PARA REALIZAR O MOVIMENTO

	}

	private void verifyPossibleMoviments() {
		// REALIZA A VERIFICAÇÃO E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS
		// VALIDOS

		verifyCaptureMovement();
		verifyNormalMovements();
	}

	private void verifyNormalMovements() {

		// VALIDAÇÃO PARA PEÇAS PRETAS
		if (selectedField.getPeca().getCor() == 0) {
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));

				}
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));

				}
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));

				}
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));

				}

			}
		}
		// VALIDAÇÃO PARA PEÇAS BRANCAS
		if (selectedField.getPeca().getCor() == 1) {
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));

				}
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));

				}
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));

				}
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));

				}

			}
			
			
			
			
			
		}

	}

	private Boolean verifyCaptureMovement() {
		return true;
	}

}
