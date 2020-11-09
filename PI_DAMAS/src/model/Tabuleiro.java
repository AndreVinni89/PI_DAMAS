package model;

import java.util.List;

public class Tabuleiro {

	// ATRIBUTO DO TABULEIRO QUE CONTEM A MATRIZ DE POSIÇÕES
	private Posicao[][] tabuleiro = new Posicao[8][8];

	public Tabuleiro() {
		/*
		 * LOGICA DE CRIAÇÃO DA MATRIZ DO TABULEIRO SEGUE A MESMA LOGICA UTILIZADA NA
		 * VIEW PARA REALIZAR O POSICIONAMENTO DAS PEÇAS PARA ELES FICAREM SINCRONIZADOS
		 * NO INICIO DE JOGO
		 */

		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {

				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contY >= 0 && contY <= 2) {
						/*
						 * GUARDA NA POSIÇÃO DA MATRIZ UM OBJETO POSIÇÃO QUE CONTEM AS COORDENADAS E UM
						 * OBJETO PECA IDENTIFICANDO-SE SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO
						 */
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(PieceColor.BRANCO));
					}

					else if (contY >= 5 && contY <= 7) {
						/*
						 * GUARDA NA POSIÇÃO DA MATRIZ UM OBJETO POSIÇÃO QUE CONTEM AS COORDENADAS E UM
						 * OBJETO PECA IDENTIFICANDO-SE SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO
						 */
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(PieceColor.PRETO));

					} else {
						// CASO SEJA UMA POSIÇÃO QUE NAO CONTENHA UMA PEÇA É INSTANCIADO UMA POSIÇÃO SEM
						// O ATRIBUTO PEÇA
						tabuleiro[contX][contY] = new Posicao(contX, contY);
					}

				} else {
					// CASO SEJA UMA POSIÇÃO QUE NAO CONTENHA UMA PEÇA É INSTANCIADO UMA POSIÇÃO SEM
					// O ATRIBUTO PEÇA
					tabuleiro[contX][contY] = new Posicao(contX, contY);
				}

			}
		}

	}

	public void movePiece(Posicao origem, Posicao destino) {

		// MOVENDO A PEÇA DA POSIÇÃO INICIAL PARA A POSIÇÃO DESTINO
		tabuleiro[destino.getX()][destino.getY()].setTemPeca(true);
		tabuleiro[destino.getX()][destino.getY()].setPeca(tabuleiro[origem.getX()][origem.getY()].getPeca());

		// EXLUINDO A PEÇA DA POSIÇÃO ORIGINAL
		tabuleiro[origem.getX()][origem.getY()].setPeca(null);
		tabuleiro[origem.getX()][origem.getY()].setTemPeca(false);

		if ((destino.getY() == 0 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.PRETO)
				|| (destino.getY() == 7 && tabuleiro[destino.getX()][destino.getY()].getPeca().getCor() == PieceColor.BRANCO)) {
			tabuleiro[destino.getX()][destino.getY()].getPeca().setDama(true);
		}

	}

	public void capturePiece(Posicao origem, Posicao destino, List<Posicao> capturedPieces) {
		// MOVENDO A PEÇA DA POSIÇÃO INICIAL PARA A POSIÇÃO DESTINO
		tabuleiro[destino.getX()][destino.getY()].setTemPeca(true);
		tabuleiro[destino.getX()][destino.getY()].setPeca(tabuleiro[origem.getX()][origem.getY()].getPeca());

		// EXLUINDO A PEÇA DA POSIÇÃO ORIGINAL
		tabuleiro[origem.getX()][origem.getY()].setPeca(null);
		tabuleiro[origem.getX()][origem.getY()].setTemPeca(false);

		// EXCLUINDO A PEÇA CAPTURADA
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
