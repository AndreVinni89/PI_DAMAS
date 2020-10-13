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
	// VARIAVEL QUE CONTROLA O JOGADOR DA VEZ
	private int corDaVez = 1;

	// JOGADORES DA PARTIDA
	private Player player1;
	private Player player2;

	// CONSTRUTOR
	public Partida(ControllerPartida controller, Player p1, Player p2) {
		// RECEBE A INSTANCIA DO CONTROLLER
		this.controller = controller;
		// INSTANCIA O TABULEIRO
		this.tabuleiro = new Tabuleiro();

		this.player1 = p1;
		this.player2 = p2;
	}

	// FUNCAO DISPARADA NO MOMENTO QUE O JOGADOR CLICA EM UM CAMPO
	public List<Posicao> selectField(int x, int y) {
		/*
		 * VERIFICANDO SE EXISTEM MOVIMENTOS POSSIVEIS POIS SE NAO TIVER SIDO
		 * SELECIONADA NENHUMA PECA OU NAO NAO HAVER MOVIMENTOS POSSIVEIS PARA A PECA
		 * SELECIONADO TORNANDO ASSIM SEM SENTIDO SEGUIR COM AS VALIDA��ES DE MOVIMENTO
		 */
		
		List<Posicao> movimentosView = new ArrayList<>();
		
		
		System.out.println("PE�A SELECIONADA: " + selectedField);
		if (possibleCaptureMovements.size() > 0) {
			System.out.println("VERIFICA��O DE CAPTURA");
			System.out.println(possibleCaptureMovements);
			for (int cont = 0; cont < possibleCaptureMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO DE CAPTURA POSSIVEL
				System.out.println("Posi��o clicada como destino: X: " + x + " Y: " + y);
				if (possibleCaptureMovements.get(cont).getX() == x && possibleCaptureMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					capturePiece(selectedField, new Posicao(x, y));
					break;
				}
			}
			// ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
			possibleCaptureMovements.clear();
			possibleNormalMovements.clear();
			selectedField = null;

		} else if (possibleNormalMovements.size() > 0) {
			System.out.println("VERIFICA��O DE MOVIMENTOS NORMAIS");
			System.out.println(possibleNormalMovements);
			// ITERANDO PELO ARRAY DE MOVIMENTOS POSSIVEIS
			for (int cont = 0; cont < possibleNormalMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO POSSIVEL
				if (possibleNormalMovements.get(cont).getX() == x && possibleNormalMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					movePiece(selectedField, new Posicao(x, y));
					// ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
					break;
				}

			}
			possibleNormalMovements.clear();
			selectedField = null;

		}
		// SE NAO HOUVER MOVIMENTOS POSSIVEIS VALIDA-SE SE O CAMPO SELECIONADO CONTEM
		// UMA PE�A

		if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[x][y].getPeca().getCor() == corDaVez) {
			System.out.println("SELE��O DA PE�A");
			// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSI��O SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			System.out.println("PE�A SELECIONADA ALTERADA PARA" + selectedField);
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();
			
			//ADICIONANDO A POSI�AO DA PE�A SELECIONADA
			movimentosView.add(tabuleiro.getTabuleiro()[x][y]);
			//ADICIONANDO AS POSI��ES DOS MOVIMENTOS NORMAIS POSSIVEIS
			if (possibleCaptureMovements.size() > 0) {
				for(Posicao movimentos : possibleCaptureMovements) {
					movimentosView.add(movimentos);
				}
			}
			else {
				for(Posicao movimentos : possibleNormalMovements) {
					movimentosView.add(movimentos);
				}
			}
		}
		System.out.println("------------------------------------------------------");
		return movimentosView;
	}

	private void movePiece(Posicao origem, Posicao destino) {
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(origem, destino);
		tabuleiro.movePiece(origem, destino);
		// ZERANDO O VALOR O CAMPO SELECIONADO POIS O MOVIMENTO JA FOI REALIZADO
		selectedField = null;
		if (corDaVez == 1) {
			corDaVez = 0;
		} else {
			corDaVez = 1;
		}

	}

	private void capturePiece(Posicao origem, Posicao destino) {
		int capturedPieceX;
		int capturedPieceY;

		// LOGICA PARA IDENTIFICAR A POSI��O DA PE�A CAPTURADA
		if (destino.getX() > origem.getX()) {
			capturedPieceX = origem.getX() + 1;
		} else {
			capturedPieceX = origem.getX() - 1;
		}
		if (destino.getY() > origem.getY()) {
			capturedPieceY = origem.getY() + 1;
		} else {
			capturedPieceY = origem.getY() - 1;
		}

		controller.capturePiece(origem, destino, new Posicao(capturedPieceX, capturedPieceY, tabuleiro.getTabuleiro()[capturedPieceX][capturedPieceY].getPeca()));
		tabuleiro.capturePiece(origem, destino, new Posicao(capturedPieceX, capturedPieceY,  tabuleiro.getTabuleiro()[capturedPieceX][capturedPieceY].getPeca()));

		selectedField = null;
		if (corDaVez == 1) {
			corDaVez = 0;
		} else {
			corDaVez = 1;
		}
	}

	private void verifyPossibleMoviments() {
		// REALIZA A VERIFICA��O E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS
		// VALIDOS
		verifyCaptureMovement();

		verifyNormalMovements();
	}

	private void verifyNormalMovements() {

		// VALIDA��O PARA PE�AS PRETAS
		if (selectedField.getPeca().getCor() == 0 && selectedField.getPeca().getDama() == false) {
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				verifyNormalMovementRigTop();
			}
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				verifyNormalMovementLefTop();
			}
			// VERIFICA��O PARA AS DEMAIS PE�AS
			else {
				verifyNormalMovementLefTop();
				verifyNormalMovementRigTop();
			}
		}
		// VALIDA��O PARA PE�AS BRANCAS
		if (selectedField.getPeca().getCor() == 1 && selectedField.getPeca().getDama() == false) {
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				verifyNormalMovementRigBot();
			}
			// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				verifyNormalMovementLefBot();
			}
			// VERIFICA��O PARA AS DEMAIS PE�AS
			else {
				verifyNormalMovementLefBot();
				verifyNormalMovementRigBot();
			}

		}
		if (selectedField.getPeca().getDama() == true) {
			if ( selectedField.getY() == 0 ) {
				if( selectedField.getX()  == 0 ) {
					verifyNormalMovementRigBot();
				} else if (selectedField.getX() == 7) {
					verifyNormalMovementLefBot();
				} else {
					verifyNormalMovementLefBot();
					verifyNormalMovementRigBot();
				}
			} else if ( selectedField.getY() == 7 ) {
				if ( selectedField.getX() == 0 ) {
					verifyNormalMovementRigTop();
				}
				else if ( selectedField.getX() == 7 ) {
					verifyNormalMovementLefTop();
				} else {
					verifyNormalMovementLefTop();
					verifyNormalMovementRigTop();
				}
			} else {
				if (selectedField.getX() == 0) {
					verifyNormalMovementRigTop();
					verifyNormalMovementRigBot();
				} else if (selectedField.getX() == 7) {
					verifyNormalMovementLefBot();
					verifyNormalMovementLefTop();
				} else {
					verifyNormalMovementLefTop();
					verifyNormalMovementRigTop();
					verifyNormalMovementLefBot();
					verifyNormalMovementRigBot();
				}
			}
		}
	}
	
	private void verifyNormalMovementRigTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1]
				.getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));
		}
	}

	private void verifyNormalMovementLefTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1]
				.getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));
		}
	}

	private void verifyNormalMovementRigBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1]
				.getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));
		}
	}

	private void verifyNormalMovementLefBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1]
				.getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));
		}
	}

	
	
	

	private Boolean verifyCaptureMovement() {

		// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA DIREITA
		// PARA ESQUERDA

		if (selectedField.getX() <= 1) {
			// SE A POSI��O SELECIONADA FOR A DO CANTO INFERIOR ESQUERDO
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementRigTop();
			}
			// SE A POSI��O SELECIONADA FOR A DO CANTO SUPERIOR ESQUERDO
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementRigBot();
			}
			// SE A POSI��O SELECIONADA ESTIVER NA DIREITA NO MEIO
			else {
				verifyCaptureMovementRigTop();
				verifyCaptureMovementRigBot();
			}

		}

		// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA ESQUERDA
		// PARA DIREITA
		else if (selectedField.getX() >= 6) {
			// SE A POSI��O SELECIONADA FOR A DO CANTO INFERIOR DIREITO
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementLefTop();
			}
			// SE A POSI��O SELECIONADA FOR A DO CANTO SUPERIOR DIREITO
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementLefBot();
			}
			// SE A POSI��O SELECIONADA ESTIVER NA ESQUERDA NO MEIO
			else {
				verifyCaptureMovementLefTop();
				verifyCaptureMovementLefBot();
			}
		}
		// VERIFICA��O PARA AS DEMAIS PE�AS
		else {
			// SE A POSI��O SELECIONADA ESTIVER NAS LINHA SUPERIOR
			if (selectedField.getY() >= 6) {
				verifyCaptureMovementLefTop();
				verifyCaptureMovementRigTop();
			}
			// SE A POSI��O SELECIONADA ESTIVER NAS LINHA SUPERIOR
			else if (selectedField.getY() <= 1) {
				verifyCaptureMovementLefBot();
				verifyCaptureMovementRigBot();
			}
			// SE A POSI��O SELECIONADA ESTIVER NO MEIO
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
			// VERIFICANDO SE A POSI��O AP�S A PE�A ESTA VAZIA
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
