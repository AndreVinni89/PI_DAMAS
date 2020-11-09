package model;

import java.util.List;

public class Tabuleiro {

	// ATRIBUTO DO TABULEIRO QUE CONTEM A MATRIZ DE POSI��ES
	private Posicao[][] tabuleiro = new Posicao[8][8];

	public Tabuleiro() {
		/*
		 * LOGICA DE CRIA��O DA MATRIZ DO TABULEIRO SEGUE A MESMA LOGICA UTILIZADA NA
		 * VIEW PARA REALIZAR O POSICIONAMENTO DAS PE�AS PARA ELES FICAREM SINCRONIZADOS
		 * NO INICIO DE JOGO
		 */

		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {

				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contY >= 0 && contY <= 2) {
						/*
						 * GUARDA NA POSI��O DA MATRIZ UM OBJETO POSI��O QUE CONTEM AS COORDENADAS E UM
						 * OBJETO PECA IDENTIFICANDO-SE SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO
						 */
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(PieceColor.BRANCO));
					}

					else if (contY >= 5 && contY <= 7) {
						/*
						 * GUARDA NA POSI��O DA MATRIZ UM OBJETO POSI��O QUE CONTEM AS COORDENADAS E UM
						 * OBJETO PECA IDENTIFICANDO-SE SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO
						 */
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(PieceColor.PRETO));

					} else {
						// CASO SEJA UMA POSI��O QUE NAO CONTENHA UMA PE�A � INSTANCIADO UMA POSI��O SEM
						// O ATRIBUTO PE�A
						tabuleiro[contX][contY] = new Posicao(contX, contY);
					}

				} else {
					// CASO SEJA UMA POSI��O QUE NAO CONTENHA UMA PE�A � INSTANCIADO UMA POSI��O SEM
					// O ATRIBUTO PE�A
					tabuleiro[contX][contY] = new Posicao(contX, contY);
				}

			}
		}

	}

	public void movePiece(Posicao origem, Posicao destino) {

		// MOVENDO A PE�A DA POSI��O INICIAL PARA A POSI��O DESTINO
		tabuleiro[destino.getX()][destino.getY()].setTemPeca(true);
		tabuleiro[destino.getX()][destino.getY()].setPeca(tabuleiro[origem.getX()][origem.getY()].getPeca());

		// EXLUINDO A PE�A DA POSI��O ORIGINAL
		tabuleiro[origem.getX()][origem.getY()].setPeca(null);
		tabuleiro[origem.getX()][origem.getY()].setTemPeca(false);

		if ((destino.getY() == 0 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.PRETO)
				|| (destino.getY() == 7 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.BRANCO)) {
			tabuleiro[destino.getX()][destino.getY()].getPeca().setDama(true);
		}

	}

	public void capturePiece(Posicao origem, Posicao destino, List<Posicao> capturedPieces) {
		// MOVENDO A PE�A DA POSI��O INICIAL PARA A POSI��O DESTINO
		tabuleiro[destino.getX()][destino.getY()].setTemPeca(true);
		tabuleiro[destino.getX()][destino.getY()].setPeca(tabuleiro[origem.getX()][origem.getY()].getPeca());

		// EXLUINDO A PE�A DA POSI��O ORIGINAL
		tabuleiro[origem.getX()][origem.getY()].setPeca(null);
		tabuleiro[origem.getX()][origem.getY()].setTemPeca(false);

		// EXCLUINDO A PE�A CAPTURADA
		for(Posicao capturedPiece : capturedPieces) {
			tabuleiro[capturedPiece.getX()][capturedPiece.getY()].setPeca(null);
			tabuleiro[capturedPiece.getX()][capturedPiece.getY()].setTemPeca(false);
		}


		if ((destino.getY() == 0 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.PRETO)
				|| (destino.getY() == 7 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.BRANCO)) {
			tabuleiro[destino.getX()][destino.getY()].getPeca().setDama(true);
			System.out.println("VIROU DAMA");
		}

	}

	public Posicao[][] getTabuleiro() {
		return tabuleiro;
	}

}
