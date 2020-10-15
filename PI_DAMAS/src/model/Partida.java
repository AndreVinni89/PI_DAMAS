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
		 * SELECIONADO TORNANDO ASSIM SEM SENTIDO SEGUIR COM AS VALIDAÇÕES DE MOVIMENTO
		 */

		List<Posicao> movimentosView = new ArrayList<>();

		System.out.println("PEÇA SELECIONADA: " + selectedField);
		if (possibleCaptureMovements.size() > 0) {
			System.out.println("VERIFICAÇÃO DE CAPTURA");
			System.out.println(possibleCaptureMovements);
			for (int cont = 0; cont < possibleCaptureMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO DE CAPTURA POSSIVEL
				System.out.println("Posição clicada como destino: X: " + x + " Y: " + y);
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
			System.out.println("VERIFICAÇÃO DE MOVIMENTOS NORMAIS");
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
		// UMA PEÇA

		if (tabuleiro.getTabuleiro()[x][y].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[x][y].getPeca().getCor() == corDaVez) {

			// ZERANDO OS MOVIMENTOS POSSIVEIS
			possibleNormalMovements.clear();
			possibleCaptureMovements.clear();

			System.out.println("SELEÇÃO DA PEÇA");
			// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSIÇÃO SELECIONADA
			selectedField = tabuleiro.getTabuleiro()[x][y];
			System.out.println("PEÇA SELECIONADA ALTERADA PARA" + selectedField);
			// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
			verifyPossibleMoviments();

			// ADICIONANDO A POSIÇAO DA PEÇA SELECIONADA
			movimentosView.add(tabuleiro.getTabuleiro()[x][y]);
			// ADICIONANDO AS POSIÇÕES DOS MOVIMENTOS NORMAIS POSSIVEIS
			if (possibleCaptureMovements.size() > 0) {
				for (Posicao movimentos : possibleCaptureMovements) {
					movimentosView.add(movimentos);
				}
			} else {
				for (Posicao movimentos : possibleNormalMovements) {
					movimentosView.add(movimentos);
				}
			}

		}
		System.out.println("------------------------------------------------------");
		return movimentosView;
	}

	// COMANDO DE MOVER PEÇA
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

	// COMANDO DE CAPTURAR PEÇA
	private void capturePiece(Posicao origem, Posicao destino) {
		int capturedPieceX;
		int capturedPieceY;

		// LOGICA PARA IDENTIFICAR A POSIÇÃO DA PEÇA CAPTURADA
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

		controller.capturePiece(origem, destino, new Posicao(capturedPieceX, capturedPieceY,
				tabuleiro.getTabuleiro()[capturedPieceX][capturedPieceY].getPeca()));
		tabuleiro.capturePiece(origem, destino, new Posicao(capturedPieceX, capturedPieceY,
				tabuleiro.getTabuleiro()[capturedPieceX][capturedPieceY].getPeca()));

		selectedField = null;
		if (corDaVez == 1) {
			corDaVez = 0;
		} else {
			corDaVez = 1;
		}
	}

	// FUNÇÃO QUE CHAMA AS VERIFICAÇÕES
	private void verifyPossibleMoviments() {
		// REALIZA A VERIFICAÇÃO E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS
		// VALIDOS

		List<Posicao> possibleCaptureMovementsTemp = new ArrayList<>();

		possibleCaptureMovementsTemp = verifyCaptureMovement(selectedField, selectedField.getPeca().getCor(),
				selectedField);

		if (possibleCaptureMovementsTemp.size() > 0) {
			System.out.println(possibleCaptureMovementsTemp);
			verifyMultipleCapture(possibleCaptureMovementsTemp, 0, selectedField, true);
		} else {
			verifyNormalMovements();
		}

	}

	private void verifyMultipleCapture(List<Posicao> possibleCaptureMovementsTemp, int contCaptured, Posicao noVerify, Boolean primeira) {
		int cont = 0;
		if (primeira ==false) {
			possibleCaptureMovementsTemp.remove(noVerify);
		}

		List<List<Posicao>> possibleCaptureMovementsTemp2 = new ArrayList<>();
		for (Posicao movements : possibleCaptureMovementsTemp) {
			List<Posicao> listaTemporaria3 = new ArrayList<>();
			listaTemporaria3 = verifyCaptureMovement(movements, selectedField.getPeca().getCor(), noVerify);

			if (listaTemporaria3.size() > 0) {		
				possibleCaptureMovementsTemp2.add(listaTemporaria3);
				possibleCaptureMovementsTemp2.get(cont).add(movements);
				cont++;
			}
		}
		if (possibleCaptureMovementsTemp2.size() > 0) {

			if (possibleCaptureMovementsTemp2.get(0).size() > 0) {
				contCaptured++;
				for (List<Posicao> list : possibleCaptureMovementsTemp2) {
					for (int contPos = 0; contPos < list.size() - 1; contPos++) {
						list.get(contPos).setContCaptured(contCaptured);

						if (possibleCaptureMovements.size() == 0
								&& possibleCaptureMovements.contains(list.get(contPos)) == false) {
							possibleCaptureMovements.add(list.get(contPos));
						} else {

							if (possibleCaptureMovements.get(0).getContCaptured() < list.get(contPos).getContCaptured()) {
								for (Posicao pos : possibleCaptureMovements) {
									pos.setContCaptured(0);
								}
								possibleCaptureMovements.clear();
								possibleCaptureMovements.add(list.get(contPos));

							} else if (possibleCaptureMovements.get(0).getContCaptured() == list.get(contPos)
									.getContCaptured()) {
								possibleCaptureMovements.add(list.get(contPos));
							} else {
								list.get(contPos).setContCaptured(0);
							}
						}

					}

					System.out.println("Peça que nao deve ser verificada");
					System.out.println(list.get(list.size() - 1));

					System.out.println("LISTA DE MOVIMENTOS DE CAPTURA POSSIVEIS");
					System.out.println(possibleCaptureMovements);

					verifyMultipleCapture(list, contCaptured, list.get(list.size() - 1), false);

				}
			}

		} else {
			if (possibleCaptureMovements.size() == 0) {
				possibleCaptureMovements = possibleCaptureMovementsTemp;
			}
		}
	}

	// VERIFICAÇÃO DE MOVIMENTOS NORMAIS
	private void verifyNormalMovements() {

		// VALIDAÇÃO PARA PEÇAS PRETAS
		if (selectedField.getPeca().getCor() == 0 && selectedField.getPeca().getDama() == false) {
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				verifyNormalMovementRigTop();
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				verifyNormalMovementLefTop();
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				verifyNormalMovementLefTop();
				verifyNormalMovementRigTop();
			}
		}
		// VALIDAÇÃO PARA PEÇAS BRANCAS
		if (selectedField.getPeca().getCor() == 1 && selectedField.getPeca().getDama() == false) {
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
			// PARA ESQUERDA
			if (selectedField.getX() == 0) {
				verifyNormalMovementRigBot();
			}
			// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
			// PARA DIREITA
			else if (selectedField.getX() == 7) {
				verifyNormalMovementLefBot();
			}
			// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
			else {
				verifyNormalMovementLefBot();
				verifyNormalMovementRigBot();
			}

		}
		// VERIFICAÇÃO DOS MOVIMENTOS DA DAMA
		if (selectedField.getPeca().getDama() == true) {
			if (selectedField.getY() == 0) {
				if (selectedField.getX() == 0) {
					verifyNormalMovementRigBot();
				} else if (selectedField.getX() == 7) {
					verifyNormalMovementLefBot();
				} else {
					verifyNormalMovementLefBot();
					verifyNormalMovementRigBot();
				}
			} else if (selectedField.getY() == 7) {
				if (selectedField.getX() == 0) {
					verifyNormalMovementRigTop();
				} else if (selectedField.getX() == 7) {
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
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));
		}
	}

	private void verifyNormalMovementLefTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));
		}
	}

	private void verifyNormalMovementRigBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));
		}
	}

	private void verifyNormalMovementLefBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));
		}
	}

	// VERIFICAÇÃO DE CAPTURA

	private List<Posicao> verifyCaptureMovement(Posicao originPiece, int cor, Posicao noVerify) {
		List<Posicao> possibleCaptureMovementsTemp = new ArrayList<>();

		System.out.println("VERIFICAÇÃO DE CAPTURA CHAMADA");
		// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA DIREITA
		// PARA ESQUERDA
		if (originPiece.getX() <= 1) {
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO INFERIOR ESQUERDO
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementRigTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO SUPERIOR ESQUERDO
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementRigBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NA DIREITA NO MEIO
			else {
				verifyCaptureMovementRigTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
		}
		// VERIFICAÇÃO DE MOVIMENTOS PARA AS PEÇA QUE ESTÃO NA ULTIMA CASA DA ESQUERDA
		// PARA DIREITA
		else if (originPiece.getX() >= 6) {
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO INFERIOR DIREITO
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementLefTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA FOR A DO CANTO SUPERIOR DIREITO
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementLefBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NA ESQUERDA NO MEIO
			else {
				verifyCaptureMovementLefTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementLefBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
		}
		// VERIFICAÇÃO PARA AS DEMAIS PEÇAS
		else {
			// SE A POSIÇÃO SELECIONADA ESTIVER NAS LINHA SUPERIOR
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementLefTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementRigTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NAS LINHA SUPERIOR
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementLefBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
			// SE A POSIÇÃO SELECIONADA ESTIVER NO MEIO
			else {
				verifyCaptureMovementLefTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementLefBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
				verifyCaptureMovementRigTop(originPiece, cor, possibleCaptureMovementsTemp, noVerify);
			}
		}
		System.out.println("PEÇA DE ORIGEM");
		System.out.println(originPiece);
		System.out.println("Posições verificadas::;;;:::;;::::;;");
		System.out.println(possibleCaptureMovementsTemp);
		return possibleCaptureMovementsTemp;
	}

	private void verifyCaptureMovementRigTop(Posicao originPiece, int cor, List<Posicao> possibleCaptureMovementsTemp,
			Posicao noVerify) {

		if (tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1].getPeca().getCor() != cor) {

			// VERIFICANDO SE A POSIÇÃO APÓS A PEÇA ESTA VAZIA

			if (tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() - 2].getTemPeca() == false) {

				if (noVerify.getX() != originPiece.getX() + 2 || noVerify.getY() != originPiece.getY() - 2) {

					System.out.println("RIGTOP");

					possibleCaptureMovementsTemp
							.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() - 2]);
				}

			}
		}
	}

	private void verifyCaptureMovementLefTop(Posicao originPiece, int cor, List<Posicao> possibleCaptureMovementsTemp,
			Posicao noVerify) {

		if (tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() - 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() - 2 || noVerify.getY() != originPiece.getY() - 2) {
					System.out.println("LEFTOP");
					possibleCaptureMovementsTemp
							.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() - 2]);
				}

			}

		}
	}

	private void verifyCaptureMovementRigBot(Posicao originPiece, int cor, List<Posicao> possibleCaptureMovementsTemp,
			Posicao noVerify) {
		if (tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() + 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() + 2 || noVerify.getY() != originPiece.getY() + 2) {

					System.out.println("RIGBOT");

					possibleCaptureMovementsTemp
							.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() + 2]);
				}

			}

		}
	}

	private void verifyCaptureMovementLefBot(Posicao originPiece, int cor, List<Posicao> possibleCaptureMovementsTemp,
			Posicao noVerify) {
		if (tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() + 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() - 2 || noVerify.getY() != originPiece.getY() + 2) {

					System.out.println("LEFBOT");

					possibleCaptureMovementsTemp
							.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() + 2]);
				}
			}
		}
	}

}
