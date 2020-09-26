package model;



public class Tabuleiro {

	private Posicao[][] tabuleiro = new Posicao[8][8];

	public Tabuleiro() {

		for (int contX = 0; contX < 8; contX++) {
			for (int contY = 0; contY < 8; contY++) {


				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contX >= 0 && contX <= 2) {
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(1));
					}

					else if (contX >= 5 && contX <= 7) {
						tabuleiro[contX][contY] = new Posicao(contX, contY, new Peca(0));

					}
					else {
						tabuleiro[contX][contY] = new Posicao(contX, contY);
					}

				}
				else {
					tabuleiro[contX][contY] = new Posicao(contX, contY);
				}

			}
		}

	}

	public void print() {
		for (int contX = 0; contX < 8; contX++) {
			for (int contY = 0; contY < 8; contY++) {

				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contX >= 0 && contX <= 2) {
						
						System.out.printf(tabuleiro[contX][contY].getPeca().getCor() + "  ");
					}

					else if (contX >= 5 && contX <= 7) {
						
						System.out.printf(tabuleiro[contX][contY].getPeca().getCor() + "  ");

					}

				}
				System.out.printf("-  ");



			}
			System.out.printf("\n");
		}
	}

	public Posicao[][] getTabuleiro() {
		return tabuleiro;
	}

}
