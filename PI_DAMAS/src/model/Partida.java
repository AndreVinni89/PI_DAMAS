package model;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerPartida;

public class Partida {
	// INSTANCIA DO TABULEIRO
	private Tabuleiro tabuleiro;
	// CAMPO SELECIONADO PELO USUARIO
	private Posicao selectedField;
	// ARRAY COM OS MOVIMENTOS NORMAIS POSSIVEIS
	private List<Posicao> possibleNormalMovements = new ArrayList<>();
	// ARRAY COM OS MOVIMENTOS DE CAPTURA POSSIVEIS
	private List<Posicao> possibleCaptureMovements = new ArrayList<>();
	// INSTANCIA DO CONTROLLER
	private ControllerPartida controller;
	
	//JOGADORES DA PARTIDA
	private Player player1 = new Player("Andre", "123", 5, 3, 2, 500);
	private Player player2 = new Player("Adailson", "123", 3, 2, 0, 1000);

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
		System.out.println("PEÇA SELECIONADA: " + selectedField);
		if (possibleCaptureMovements.size() > 0) {
			System.out.println("VERIFICAÇÃO DE CAPTURA");
			System.out.println(possibleCaptureMovements);
			for (int cont = 0; cont < possibleNormalMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO DE CAPTURA POSSIVEL

				if (possibleCaptureMovements.get(cont).getX() == x && possibleCaptureMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					capturePiece(selectedField.getX(), selectedField.getY(), x, y, selectedField.getPeca().getCor());
					break;
				}
			}
			// ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
			possibleCaptureMovements.clear();
			possibleNormalMovements.clear();

		} else if (possibleNormalMovements.size() > 0) {
			System.out.println("VERIFICAÇÃO DE MOVIMENTOS NORMAIS");
			// ITERANDO PELO ARRAY DE MOVIMENTOS POSSIVEIS
			for (int cont = 0; cont < possibleNormalMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO POSSIVEL
				if (possibleNormalMovements.get(cont).getX() == x && possibleNormalMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					movePiece(selectedField.getX(), selectedField.getY(), x, y, selectedField.getPeca().getCor());
					// ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
					possibleNormalMovements.clear();
					break;
				}
			}

		}
		// SE NAO HOUVER MOVIMENTOS POSSIVEIS VALIDA-SE SE O CAMPO SELECIONADO CONTEM
		// UMA PEÇA

		else if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true) {
			System.out.println("SELEÇÃO DA PEÇA");
			// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSIÇÃO SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			System.out.println("PEÇA SELECIONADA ALTERADA PARA" + selectedField);
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();
		}
		System.out.println("------------------------------------------------------");

	}

	private void movePiece(int x, int y, int destinyX, int destinyY, int cor) {
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(x, y, destinyX, destinyY, cor);
		tabuleiro.movePiece(x, y, destinyX, destinyY);
		// ZERANDO O VALOR O CAMPO SELECIONADO POIS O MOVIMENTO JA FOI REALIZADO
		selectedField = null;

	}
	private void capturePiece(int x, int y, int destinyX, int destinyY, int cor) {
		int capturedPieceX;
		int capturedPieceY;
		
		//LOGICA PARA IDENTIFICAR A POSIÇÃO DA PEÇA CAPTURADA
		if (destinyX > x) {
			capturedPieceX = x + 1;
		} else {
			capturedPieceX = x - 1;
		}
		if (destinyY > y) {
			capturedPieceY = y + 1;
		} else {
			capturedPieceY = y - 1;
		}

		
		
		controller.capturePiece(x, y, destinyX, destinyY, capturedPieceX, capturedPieceY, cor);
		tabuleiro.capturePiece(x, y, destinyX, destinyY, capturedPieceX, capturedPieceY);
		
		selectedField = null;
		
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
					possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));

				}
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));

				}
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));

				}
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));

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
					possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));

				}
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));

				}
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));

				}
				if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1]
						.getTemPeca() == false) {
					possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));

				}

			}

		}

	}

	private Boolean verifyCaptureMovement() {

		// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
		// PARA ESQUERDA

		if (selectedField.getX() <= 1) {
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO INFERIOR ESQUERDO
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementRigTop();
			}
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO SUPERIOR ESQUERDO
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementRigBot();
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NA DIREITA NO MEIO
			else {
				verifyCaptureMovementRigTop();
				verifyCaptureMovementRigBot();
			}

		}

		// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
		// PARA DIREITA
		else if (selectedField.getX() >= 6) {
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO INFERIOR DIREITO
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementLefTop();
			}
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO SUPERIOR DIREITO
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementLefBot();
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NA ESQUERDA NO MEIO
			else {
				verifyCaptureMovementLefTop();
				verifyCaptureMovementLefBot();
			}
		}
		// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
		else {
			// SE A POSIÇÃO SELECIONADA ESTIVER NAS LINHA SUPERIOR
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementLefTop();
				verifyCaptureMovementRigTop();
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NAS LINHA SUPERIOR
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementLefBot();
				verifyCaptureMovementRigBot();
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NO MEIO
			else {
				verifyCaptureMovementLefTop();
				verifyCaptureMovementLefBot();
				verifyCaptureMovementRigBot();
				verifyCaptureMovementRigTop();
			}
		}
		return true;
	}

	private void verifyCaptureMovementRigTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getPeca()
						.getCor() != selectedField.getPeca().getCor()) {
			// VERIFICANDO SE A POSIÇÃO APÓS A PEÇA ESTA VAZIA
			if (tabuleiro.getTabuleiro()[selectedField.getX() + 2][selectedField.getY() - 2].getTemPeca() == false) {
				possibleCaptureMovements.add(new Posicao(selectedField.getX() + 2, selectedField.getY() - 2));
			}
		}
	}

	private void verifyCaptureMovementLefTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1].getPeca()
						.getCor() != selectedField.getPeca().getCor()) {
			if (tabuleiro.getTabuleiro()[selectedField.getX() - 2][selectedField.getY() - 2].getTemPeca() == false) {
				possibleCaptureMovements.add(new Posicao(selectedField.getX() - 2, selectedField.getY() - 2));
			}

		}
	}

	private void verifyCaptureMovementRigBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1].getPeca()
						.getCor() != selectedField.getPeca().getCor()) {
			if (tabuleiro.getTabuleiro()[selectedField.getX() + 2][selectedField.getY() + 2].getTemPeca() == false) {
				possibleCaptureMovements.add(new Posicao(selectedField.getX() + 2, selectedField.getY() + 2));
			}

		}
	}

	private void verifyCaptureMovementLefBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1].getPeca()
						.getCor() != selectedField.getPeca().getCor()) {
			if (tabuleiro.getTabuleiro()[selectedField.getX() - 2][selectedField.getY() + 2].getTemPeca() == false) {
				possibleCaptureMovements.add(new Posicao(selectedField.getX() - 2, selectedField.getY() + 2));
			}

		}
	}

}
