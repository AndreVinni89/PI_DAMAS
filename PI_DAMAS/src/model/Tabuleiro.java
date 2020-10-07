package model;



public class Tabuleiro {

	//ATRIBUTO DO TABULEIRO QUE CONTEM A MATRIZ DE POSIÇÕES
	private Posicao[][] tabuleiro = new Posicao[8][8];

	public Tabuleiro() {
		/*LOGICA DE CRIAÇÃO DA MATRIZ DO TABULEIRO SEGUE A MESMA LOGICA UTILIZADA NA VIEW PARA REALIZAR O
		POSICIONAMENTO DAS PEÇAS PARA ELES FICAREM SINCRONIZADOS NO INICIO DE JOGO*/
		
		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {


				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contY >= 0 && contY <= 2) {
						/*GUARDA NA POSIÇÃO DA MATRIZ UM OBJETO POSIÇÃO QUE CONTEM AS COORDENADAS E UM OBJETO PECA IDENTIFICANDO-SE
						SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO*/
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(1));
					}

					else if (contY >= 5 && contY <= 7) {
						/*GUARDA NA POSIÇÃO DA MATRIZ UM OBJETO POSIÇÃO QUE CONTEM AS COORDENADAS E UM OBJETO PECA IDENTIFICANDO-SE
						SUA COR SENDO 1 PARA BRANCO E 0 PARA PRETO*/
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(0));

					}
					else {
						//CASO SEJA UMA POSIÇÃO QUE NAO CONTENHA UMA PEÇA É INSTANCIADO UMA POSIÇÃO SEM O ATRIBUTO PEÇA
						tabuleiro[contX][contY] = new Posicao(contX, contY);
					}

				}
				else {
					//CASO SEJA UMA POSIÇÃO QUE NAO CONTENHA UMA PEÇA É INSTANCIADO UMA POSIÇÃO SEM O ATRIBUTO PEÇA
					tabuleiro[contX][contY] = new Posicao(contX, contY);
				}

			}
		}

	}

	public void movePiece(int x, int y, int destinyX, int destinyY) {
		tabuleiro[x][y].setTemPeca(false);
		tabuleiro[destinyX][destinyY].setTemPeca(true);
		tabuleiro[destinyX][destinyY].setPeca(tabuleiro[x][y].getPeca());
		tabuleiro[x][y].setPeca(null);
	}
	
	public Posicao[][] getTabuleiro() {
		return tabuleiro;
	}

}
