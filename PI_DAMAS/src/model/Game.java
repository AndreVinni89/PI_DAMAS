package model;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerGame;
import model.dao.PlayerDao;

public class Game {
	// INSTANCIA DO TABULEIRO
	private Tabuleiro tabuleiro;
	
	// CAMPO SELECIONADO PELO USUARIO
	private Posicao selectedField;
	
	
	// ARRAY COM OS MOVIMENTOS NORMAIS POSSIVEIS
	private List<Posicao> possibleNormalMovements = new ArrayList<>();
	// ARRAY COM OS MOVIMENTOS DE CAPTURA POSSIVEIS
	private List<Posicao> possibleCaptureMovements = new ArrayList<>();

	private List<List<Posicao>> capturedPieces = new ArrayList<>();

	List<Posicao> capturedPiecesTemp = new ArrayList<>();

	private List<Posicao> possibleSelectedFields = new ArrayList<>();
	
	private int contWhitePieces;
	
	private int contBlackPieces;

	// INSTANCIA DO CONTROLLER
	private ControllerGame controller;
	// VARIAVEL QUE CONTROLA O JOGADOR DA VEZ
	private PieceColor corDaVez = PieceColor.BRANCO;

	// JOGADORES DA PARTIDA
	private Player player1;
	private Player player2;

	private int contEmpateMoves = 0;
	
	
	// CONSTRUTOR
	public Game(ControllerGame controller, Player p1, Player p2) {
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
			System.out.println("Pe�as que v�o ser capturadas");
			System.out.println(capturedPieces);

			System.out.println("VERIFICA��O DE CAPTURA");
			System.out.println(possibleCaptureMovements);
			for (int cont = 0; cont < possibleCaptureMovements.size(); cont++) {
				// VERIFICANDO SE AS COORDENADAS DO CAMPO SELECIONADO CORRESPONDEM A DE UM
				// MOVIMENTO DE CAPTURA POSSIVEL
				if (possibleCaptureMovements.get(cont).getX() == x && possibleCaptureMovements.get(cont).getY() == y) {
					// EXECUTANDO O METODO DE REALIZAR MOVIMENTO
					capturePiece(selectedField, possibleCaptureMovements.get(cont));
					break;
				}
			}
			// ZERANDO O VETOR DE MOVIMENTOS POSSIVEIS POIS O MOVIMENTO JA FOI REALIZADO
			possibleCaptureMovements.clear();
			possibleNormalMovements.clear();
			selectedField = null;

		} else if (possibleNormalMovements.size() > 0) {

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

			if (possibleSelectedFields.size() > 0) {
				if (possibleSelectedFields.contains(tabuleiro.getTabuleiro()[x][y])) {

					// ZERANDO OS MOVIMENTOS POSSIVEIS
					possibleNormalMovements.clear();
					possibleCaptureMovements.clear();
					capturedPieces.clear();

					System.out.println("SELE��O DA PE�A");
					// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSI��O SELECIONADA
					selectedField = tabuleiro.getTabuleiro()[x][y];
					System.out.println("PE�A SELECIONADA ALTERADA PARA" + selectedField);
					// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
					verifyPossibleMoviments();

					// ADICIONANDO A POSI�AO DA PE�A SELECIONADA
					movimentosView.add(tabuleiro.getTabuleiro()[x][y]);
					// ADICIONANDO AS POSI��ES DOS MOVIMENTOS NORMAIS POSSIVEIS
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

			} else {

				// ZERANDO OS MOVIMENTOS POSSIVEIS
				possibleNormalMovements.clear();
				possibleCaptureMovements.clear();
				capturedPieces.clear();

				System.out.println("SELE��O DA PE�A");
				// GUARDANDO NA VARIAVEL SELECTEDFIELD A POSI��O SELECIONADA
				selectedField = tabuleiro.getTabuleiro()[x][y];
				System.out.println("PE�A SELECIONADA ALTERADA PARA" + selectedField);
				// EXECUTANDO O METODO QUE IDENTIFICA OS MOVIMENTOS VALIDOS
				verifyPossibleMoviments();

				// ADICIONANDO A POSI�AO DA PE�A SELECIONADA
				movimentosView.add(tabuleiro.getTabuleiro()[x][y]);
				// ADICIONANDO AS POSI��ES DOS MOVIMENTOS NORMAIS POSSIVEIS
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
			


		}

		System.out.println("------------------------------------------------------");

		return movimentosView;
	}

	// COMANDO DE MOVER PE�A
	private void movePiece(Posicao origem, Posicao destino) {
		contEmpateMoves++;
		System.out.println("Contagens de movimentos sem captura: " + contEmpateMoves);
		// MANDA PARA O CONTROLLER E O CONTROLLER MANDA PARA A VIEW
		controller.movePiece(origem, destino);
		tabuleiro.movePiece(origem, destino);
		// ZERANDO O VALOR O CAMPO SELECIONADO POIS O MOVIMENTO JA FOI REALIZADO
		selectedField = null;
		if (corDaVez == PieceColor.BRANCO) {
			corDaVez = PieceColor.PRETO;
		} else {
			corDaVez = PieceColor.BRANCO;
		}

		int maxCapture = 0;
		Boolean noMoves = true;
		
		
		possibleCaptureMovements.clear();
		possibleNormalMovements.clear();
		
		for (Posicao[] lin : tabuleiro.getTabuleiro()) {
			for (Posicao pos : lin) {
				if (pos.getTemPeca()) {
					if (pos.getPeca().getCor() == corDaVez) {
						selectedField = pos;
						verifyPossibleMoviments();
						if (possibleCaptureMovements.size() > 0) {

							if (possibleSelectedFields.size() > 0) {
								if (possibleCaptureMovements.get(0).getContCaptured() > maxCapture) {
									possibleSelectedFields.clear();
									possibleSelectedFields.add(selectedField);
									maxCapture = possibleCaptureMovements.get(0).getContCaptured();
								} else if (possibleCaptureMovements.get(0).getContCaptured() == maxCapture) {
									possibleSelectedFields.add(selectedField);
								}
							} else {
								possibleSelectedFields.add(selectedField);
								maxCapture = possibleCaptureMovements.get(0).getContCaptured();
							}
						}
					}
				}

				if(possibleCaptureMovements.size() > 0 || possibleNormalMovements.size() > 0) {

					noMoves = false;
				}

				
				
				possibleCaptureMovements.clear();
				possibleNormalMovements.clear();
				capturedPieces.clear();
				selectedField = null;
				pos.setContCaptured(0);

			}
		}
		if(possibleSelectedFields.size()> 0 ) {
			sendObrigatedCaptureInfo();
		}
		

		if(contEmpateMoves == 20) {
			endGame(null, true);
		}
		if(noMoves == true) {
			if(corDaVez == PieceColor.PRETO) {
				endGame(PieceColor.BRANCO , false);
			} else {
				endGame(PieceColor.PRETO, false);
			}	
		}
		
		
	}

	// COMANDO DE CAPTURAR PE�A
	private void capturePiece(Posicao origem, Posicao destino) {
		contEmpateMoves = 0;
		int capturedPieceX;
		int capturedPieceY;
		possibleSelectedFields.clear();

		// LOGICA PARA IDENTIFICAR A POSI��O DA PE�A CAPTURADA CASO SO SEJA CAPTURADA
		// UMA UNICA PE�A
		if (destino.getContCaptured() == 0) {
			System.out.println("Captura Simples");
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
			// CRIA-SE UMA LISTA PARA PASSAR GUARDAR A PE�A CAPTURADA
			List<Posicao> capturedPiece = new ArrayList<>();
			// ADICIONA-SE ESSA LISTA UM OBJETO DA CLASSE POSICAO BASEADO NAS INFORMA��ES
			// PASSADAS
			capturedPiece.add(new Posicao(capturedPieceX, capturedPieceY,
					tabuleiro.getTabuleiro()[capturedPieceX][capturedPieceY].getPeca()));

			// MANDA A INFORMA��O PARA O CONTROLLER E O TABULEIRO
			controller.capturePiece(origem, destino, capturedPiece);
			tabuleiro.capturePiece(origem, destino, capturedPiece);

		}

		// LOGICA PARA A CAPTURA DE MAIS DE UMA PE�A
		else {
			System.out.println("Captura Composta");
			int i;
			i = possibleCaptureMovements.indexOf(destino);
			controller.capturePiece(origem, destino, capturedPieces.get(i));
			tabuleiro.capturePiece(origem, destino, capturedPieces.get(i));

		}

		// ZERA O CAMPO SELECIONADO
		selectedField = null;

		// LIMPA A LISTA DE PE�AS CAPTURADAS
		capturedPieces.clear();
		possibleCaptureMovements.clear();
		possibleNormalMovements.clear();
		// ZERA AS O CONTCAPTURED DA LSTA DE CAPTURA
		for (Posicao[] lin : tabuleiro.getTabuleiro()) {
			for (Posicao pos : lin) {
				pos.setContCaptured(0);
			}
		}

		// ALTERA A COR DA VEZ
		if (corDaVez == PieceColor.BRANCO) {
			corDaVez = PieceColor.PRETO;
		} else {
			corDaVez = PieceColor.BRANCO;
		}
		
		Boolean noMoves = true;
		int maxCapture = 0;
		
		contBlackPieces=0;
		contWhitePieces=0;

		for (Posicao[] lin : tabuleiro.getTabuleiro()) {
			for (Posicao pos : lin) {
				if (pos.getTemPeca()) {
					
					if(pos.getPeca().getCor() == PieceColor.PRETO) {
						contBlackPieces++;
					}else {
						contWhitePieces++;
					}
					
					
					
					if (pos.getPeca().getCor() == corDaVez) {
						selectedField = pos;

						verifyPossibleMoviments();
						if (possibleCaptureMovements.size() > 0) {

							if (possibleSelectedFields.size() > 0) {
								if (possibleCaptureMovements.get(0).getContCaptured() > maxCapture) {
									possibleSelectedFields.clear();
									possibleSelectedFields.add(selectedField);
									maxCapture = possibleCaptureMovements.get(0).getContCaptured();
								} else if (possibleCaptureMovements.get(0).getContCaptured() == maxCapture) {
									possibleSelectedFields.add(selectedField);
								}
							} else {
								possibleSelectedFields.add(selectedField);
								maxCapture = possibleCaptureMovements.get(0).getContCaptured();
							}
						}

					}
				}

				if(possibleCaptureMovements.size() > 0 || possibleNormalMovements.size() > 0) {
					System.out.println("Caiu dentro");
					noMoves = false;
				}

				
				
				possibleCaptureMovements.clear();
				possibleNormalMovements.clear();
				selectedField = null;
				capturedPieces.clear();
				pos.setContCaptured(0);

			}
		}
		
		
		if(possibleSelectedFields.size()> 0 ) {
			sendObrigatedCaptureInfo();
		}
		
		
		if(contBlackPieces == 0) {
			endGame(PieceColor.PRETO, false);
		} else if(contWhitePieces == 0){
			endGame(PieceColor.BRANCO, false);
		}
		
		if(noMoves == true) {
			if(corDaVez == PieceColor.PRETO) {
				endGame(PieceColor.BRANCO , false);
			} else {
				endGame(PieceColor.PRETO, false);
			}	
		}
		
		
		

	}
	
	
	
	

	private void endGame(PieceColor corVitoria, Boolean empate) {
		
		if(empate) {
			System.out.println("PARTIDA EMPATADA");
			player1.setNumEmpates(1);
			player2.setNumEmpates(1);
			player1.setPontuation(1);
			player2.setPontuation(1);
			
			controller.endGame(corVitoria, empate);
			
		} else {
			System.out.println("O jogador da cor " + corVitoria + " Venceu");
			
			if(player1.getPieceColor() == corVitoria) {
				player1.setNumVitorias(1);
				player1.setPontuation(3);
				
				
				player2.setNumDerrotas(1);
				
						
			} else {
				player2.setNumVitorias(1);
				player2.setPontuation(3);
				player1.setNumDerrotas(1);
				
			}
			controller.endGame(corVitoria, empate);
		}
		
		PlayerDao.attUserData(player1);
		PlayerDao.attUserData(player2);
	}

	private void sendObrigatedCaptureInfo() {
		controller.sendObrigatedCaptureInfo(possibleSelectedFields);
		
	}

	// FUN��O QUE CHAMA AS VERIFICA��ES
	private void verifyPossibleMoviments() {
		// REALIZA A VERIFICA��O E SALVA NO ARRAY POSSIBLEMOVEMENTS OS MOVIMENTOS
		// VALIDOS

		List<Posicao> possibleCaptureMovementsTemp = new ArrayList<>();

		possibleCaptureMovementsTemp = verifyCaptureMovement(selectedField, selectedField.getPeca().getCor(),
				selectedField);

		if (possibleCaptureMovementsTemp.size() > 0) {
			List<Posicao> capturedPiecesTemp2 = new ArrayList<>();
			for (Posicao e : capturedPiecesTemp) {
				capturedPiecesTemp2.add(e);
			}
			capturedPiecesTemp.clear();

			for (int cont = 0; cont < possibleCaptureMovementsTemp.size(); cont++) {
				List<Posicao> tempMove = new ArrayList<>();
				List<Posicao> tempCap = new ArrayList<>();
				tempCap.add(capturedPiecesTemp2.get(cont));
				tempMove.add(possibleCaptureMovementsTemp.get(cont));

				verifyMultipleCapture(tempMove, 0, selectedField, true, tempCap);
			}

		} else {
			verifyNormalMovements();
		}

	}

	private void verifyMultipleCapture(List<Posicao> possibleCaptureMovementsTemp, int contCaptured, Posicao noVerify,
			Boolean primeira, List<Posicao> capturedPiecesTemporario) {

		List<Posicao> capPiece = new ArrayList<>();
		List<Posicao> capPieceTemp = new ArrayList<>();

		for (Posicao e : capturedPiecesTemporario) {
			capPiece.add(e);
		}

		// CONT QUE � USADO PARA IDENTIFICAR A POSSI��O DA LISTA QUE ESTA SENDO GUARDADO
		// OS DADOS NESSA ITERA��O
		int cont = 0;

		// REMOVENDO A POSI��O QUE � USADA COMO REFERENCIA PARA NAO VERIFICAR DO ARRAY
		if (primeira == false) {
			possibleCaptureMovementsTemp.remove(noVerify);
		}

		List<List<Posicao>> possibleCaptureMovementsTemp2 = new ArrayList<>();

		// ITERANDO SOBRE O MOVIMENTOS POSSIVEIS QUE FORAM RECEBIDOS COMO PARAMETRO
		for (int contMove = 0; contMove < possibleCaptureMovementsTemp.size(); contMove++) {
			List<Posicao> listaTemporaria3 = new ArrayList<>();
			listaTemporaria3 = verifyCaptureMovement(possibleCaptureMovementsTemp.get(contMove),
					selectedField.getPeca().getCor(), noVerify);

			if (listaTemporaria3.size() > 0) {

				// CASO TENHA SIDO ENCONTRADO MOVIMENTOS POSSIVEIS ADICIONA-OS NA LISTA
				possibleCaptureMovementsTemp2.add(listaTemporaria3);
				// ADICIONANDO A POSI��O QUE NAO DEVE SER VERIFICADA NO PROXIMA CHAMADA
				possibleCaptureMovementsTemp2.get(cont).add(possibleCaptureMovementsTemp.get(contMove));
				// AUMENTANDO O CONT POSI FORAM GRAVADOS DADOS NA LISTA

				for (Posicao e : capturedPiecesTemp) {
					capPieceTemp.add(e);
				}
				capturedPiecesTemp.clear();

				cont++;
			}

		}
		// SE FORAM IDENTIFICADOS MOVIMENTOS POSSIVEIS
		if (possibleCaptureMovementsTemp2.size() > 0) {
			// AUMENTANDO A CONTAGEM DE PE�AS CAPTURADAS
			contCaptured++;

			// FAZ-SE UMA ITERA��O DUPLA POIS A LISTA DE LISTAS FUNCIONA COMO SE FOSSE UMA
			// MATRIZ
			for (List<Posicao> list : possibleCaptureMovementsTemp2) {
				// ITERA-SE AT� A PENULTIMA POSI��O DE CADA LISTA POIS O ULTIMO ELEMENTO �
				// UTILIZADO PARA LIMITAR VERIFICA��ES POSTERIORES

				for (int contPos = 0; contPos < list.size() - 1; contPos++) {

					capPiece.add(capPieceTemp.get(contPos));

					// SE O ATRIBUTOS QUE CONTROLA QUANTAS PE�AS SER�O CAPTURADOS DENTRO DA ITERA��O
					// DESSA POSSI��O FOR MENOR QUE O Q ESTA SENDO VERIFICADO AGORA SUBSTITUI-SE
					// ESSE VALOR
					if (list.get(contPos).getContCaptured() < contCaptured) {
						list.get(contPos).setContCaptured(contCaptured);
					}

					// SE N�O EXISTEM DADOS NA LISTA DE MOVIMENTOS POSSIVEIS SALVA-SE AS POSI��ES
					// DESSA ITERA��O
					if (possibleCaptureMovements.size() == 0) {
						possibleCaptureMovements.add(list.get(contPos));

						List<Posicao> temp = new ArrayList<>();
						for (Posicao e : capPiece) {
							temp.add(e);
						}
						capturedPieces.add(temp);
					}
					// SE NAO

					else {

						// VERIFICANDO SE AS POSI��O DE LISTA DE MOVIMENTOS DE CAPTURA POSSIVEIS
						// CAPTURAM UM MENOR NUMERO DE PE�AS DO QUE AS QUE ESTAO SEND VERIFICADAS AGORA
						if (possibleCaptureMovements.get(0).getContCaptured() < list.get(contPos).getContCaptured()) {

							// ZERA OS DADOS QUE PE�AS CAPTURADAS DAS POSI��ES DE MOVIMENTOS DE CAPTURA
							// POSSIVEIS
							for (Posicao pos : possibleCaptureMovements) {
								pos.setContCaptured(0);
							}

							// APAGANDO OS DADOS
							possibleCaptureMovements.clear();
							capturedPieces.clear();

							// ADICIONANDO A POSI��O DA ITERA��O
							possibleCaptureMovements.add(list.get(contPos));

							List<Posicao> temp = new ArrayList<>();
							for (Posicao e : capPiece) {
								temp.add(e);
							}
							capturedPieces.add(temp);

						}

						// SE AS POSI��ES DA LISTA DE CAPTURA POSSIVELS TIVEREM O MESMO NUMERO DE
						// CAPTURA ADICIONA -SE AS DA ITERA��O ATUAL NELA
						else if (possibleCaptureMovements.get(0).getContCaptured() == list.get(contPos)
								.getContCaptured() && !possibleCaptureMovements.contains(list.get(contPos))) {

							System.out.println(
									"CAI DENTRO DESSA PORRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
							possibleCaptureMovements.add(list.get(contPos));

							List<Posicao> temp = new ArrayList<>();
							for (Posicao e : capPiece) {
								temp.add(e);
							}
							capturedPieces.add(temp);

						}

						else {
							// SE OS VALORES DE PE�A CAPTURADA DESSA POSI��O CORRESPONDEM AO NY=UMERO DE
							// PE�AS CAOTURADAS ZERA O SEU VALOR
							if (list.get(contPos).getContCaptured() == contCaptured) {
								list.get(contPos).setContCaptured(0);
							}
						}

					}
					System.out.println("PECAS CAPTURADAS");
					System.out.println(capturedPieces);

					List<Posicao> temp = new ArrayList<>();
					temp.add(list.get(contPos));

					System.out.println("LISTA DE MOVIMENTOS DE CAPTURA POSSIVEIS");
					System.out.println(possibleCaptureMovements);
					System.out.println("Pe�as capuradas nesse contexto");
					System.out.println(possibleCaptureMovements.get(0).getContCaptured() + 1);

					System.out.println("Pe�a que nao deve ser verificada");
					System.out.println(list.get(list.size() - 1));

					verifyMultipleCapture(temp, contCaptured, list.get(list.size() - 1), false, capPiece);
					capPiece.remove(capPieceTemp.get(contPos));

				}

			}

		} else {
			// SE NAO FORAM IDENTIFICADOS MAIS CAPTURAS E N�O EXISTEM DADOS NOS ATRIBUTOS DE
			// MOVIMENTOS DE CAPTURA POSSIVEIS ADICIONA-SE AS POSI��ES QUE FORAM RECEBIDAS
			// COMO PARAMETRO
			if (possibleCaptureMovements.size() == 0) {
				possibleCaptureMovements = possibleCaptureMovementsTemp;
			} else if (possibleCaptureMovements.get(0).getContCaptured() == 0) {
				for (Posicao e : possibleCaptureMovementsTemp) {
					possibleCaptureMovements.add(e);
				}
			}

		}
	}

	// VERIFICA��O DE MOVIMENTOS NORMAIS
	private void verifyNormalMovements() {

		// VALIDA��O PARA PE�AS PRETAS
		if (selectedField.getPeca().getCor() == PieceColor.PRETO && selectedField.getPeca().getDama() == false) {
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
		if (selectedField.getPeca().getCor() == PieceColor.BRANCO && selectedField.getPeca().getDama() == false) {
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
		// VERIFICA��O DOS MOVIMENTOS DA DAMA
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

	void verifyNormalMovementRigTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() - 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() - 1));
		}
	}

	void verifyNormalMovementLefTop() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() - 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() - 1));
		}
	}

	void verifyNormalMovementRigBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() + 1][selectedField.getY() + 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() + 1, selectedField.getY() + 1));
		}
	}

	void verifyNormalMovementLefBot() {
		if (tabuleiro.getTabuleiro()[selectedField.getX() - 1][selectedField.getY() + 1].getTemPeca() == false) {
			possibleNormalMovements.add(new Posicao(selectedField.getX() - 1, selectedField.getY() + 1));
		}
	}

	// VERIFICA��O DE CAPTURA

	private List<Posicao> verifyCaptureMovement(Posicao originPiece, PieceColor cor, Posicao noVerify) {
		List<Posicao> captureInfo = new ArrayList<>();

		System.out.println("VERIFICA��O DE CAPTURA CHAMADA");
		// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA DIREITA
		// PARA ESQUERDA
		if (originPiece.getX() <= 1) {
			// SE A POSI��O SELECIONADA FOR A DO CANTO INFERIOR ESQUERDO
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementRigTop(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA FOR A DO CANTO SUPERIOR ESQUERDO
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementRigBot(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA ESTIVER NA DIREITA NO MEIO
			else {
				verifyCaptureMovementRigTop(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, captureInfo, noVerify);
			}
		}
		// VERIFICA��O DE MOVIMENTOS PARA AS PE�A QUE EST�O NA ULTIMA CASA DA ESQUERDA
		// PARA DIREITA
		else if (originPiece.getX() >= 6) {
			// SE A POSI��O SELECIONADA FOR A DO CANTO INFERIOR DIREITO
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementLefTop(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA FOR A DO CANTO SUPERIOR DIREITO
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementLefBot(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA ESTIVER NA ESQUERDA NO MEIO
			else {
				verifyCaptureMovementLefTop(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementLefBot(originPiece, cor, captureInfo, noVerify);
			}
		}
		// VERIFICA��O PARA AS DEMAIS PE�AS
		else {
			// SE A POSI��O SELECIONADA ESTIVER NAS LINHA SUPERIOR
			if (originPiece.getY() >= 6) {
				verifyCaptureMovementLefTop(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementRigTop(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA ESTIVER NAS LINHA SUPERIOR
			else if (originPiece.getY() <= 1) {
				verifyCaptureMovementLefBot(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, captureInfo, noVerify);
			}
			// SE A POSI��O SELECIONADA ESTIVER NO MEIO
			else {
				verifyCaptureMovementLefTop(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementLefBot(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementRigBot(originPiece, cor, captureInfo, noVerify);
				verifyCaptureMovementRigTop(originPiece, cor, captureInfo, noVerify);
			}
		}

		System.out.println("Posi��es verificadas::;;;:::;;::::;;");
		System.out.println(captureInfo);

		return captureInfo;
	}

	void verifyCaptureMovementRigTop(Posicao originPiece, PieceColor cor, List<Posicao> captureInfo,
			Posicao noVerify) {

		if (tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1].getPeca().getCor() != cor) {

			// VERIFICANDO SE A POSI��O AP�S A PE�A ESTA VAZIA

			if (tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() - 2].getTemPeca() == false) {

				if (noVerify.getX() != originPiece.getX() + 2 || noVerify.getY() != originPiece.getY() - 2) {

					System.out.println("RIGTOP");
					Boolean aux = false;

					if (capturedPieces.size() > 0) {
						for (List<Posicao> e : capturedPieces) {
							if (e.contains(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1])) {
								aux = true;
							}
						}
						if (!aux) {
							capturedPiecesTemp
									.add(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1]);
							captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() - 2]);
						}

					} else {
						capturedPiecesTemp
								.add(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() - 1]);
						captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() - 2]);
					}

				}
			}
		}
	}

	void verifyCaptureMovementLefTop(Posicao originPiece, PieceColor cor, List<Posicao> captureInfo,
			Posicao noVerify) {

		if (tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() - 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() - 2 || noVerify.getY() != originPiece.getY() - 2) {

					Boolean aux = false;

					if (capturedPieces.size() > 0) {
						for (List<Posicao> e : capturedPieces) {
							if (e.contains(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1])) {
								aux = true;
							}
						}
						if (!aux) {
							capturedPiecesTemp
									.add(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1]);

							captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() - 2]);

						}

					} else {
						capturedPiecesTemp
								.add(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() - 1]);

						captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() - 2]);

					}

					System.out.println("LEFTOP");

				}

			}

		}
	}

	void verifyCaptureMovementRigBot(Posicao originPiece, PieceColor cor, List<Posicao> captureInfo,
			Posicao noVerify) {
		if (tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() + 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() + 2 || noVerify.getY() != originPiece.getY() + 2) {

					Boolean aux = false;

					if (capturedPieces.size() > 0) {
						for (List<Posicao> e : capturedPieces) {
							if (e.contains(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1])) {
								aux = true;
							}
						}
						if (!aux) {
							capturedPiecesTemp
									.add(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1]);

							System.out.println("RIGBOT");
							captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() + 2]);
						}

					} else {
						capturedPiecesTemp
								.add(tabuleiro.getTabuleiro()[originPiece.getX() + 1][originPiece.getY() + 1]);

						System.out.println("RIGBOT");
						captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() + 2][originPiece.getY() + 2]);

					}

				}

			}

		}
	}

	void verifyCaptureMovementLefBot(Posicao originPiece, PieceColor cor, List<Posicao> captureInfo,
			Posicao noVerify) {
		if (tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1].getTemPeca() == true
				&& tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1].getPeca().getCor() != cor) {
			if (tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() + 2].getTemPeca() == false) {
				if (noVerify.getX() != originPiece.getX() - 2 || noVerify.getY() != originPiece.getY() + 2) {

					Boolean aux = false;

					if (capturedPieces.size() > 0) {
						for (List<Posicao> e : capturedPieces) {
							if (e.contains(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1])) {
								aux = true;
							}
						}
						if (!aux) {
							capturedPiecesTemp
									.add(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1]);
							captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() + 2]);
						}

					} else {
						capturedPiecesTemp
								.add(tabuleiro.getTabuleiro()[originPiece.getX() - 1][originPiece.getY() + 1]);
						captureInfo.add(tabuleiro.getTabuleiro()[originPiece.getX() - 2][originPiece.getY() + 2]);

					}
				}
			}
		}
	}

	
	

	public void desistir() {
		if (corDaVez == PieceColor.BRANCO) {
		endGame(PieceColor.PRETO, false);
		
		}
		
		else {
			endGame(PieceColor.BRANCO, false);
		}
	}

}
