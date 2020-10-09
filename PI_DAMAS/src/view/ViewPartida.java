package view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPartida;
import model.Player;


public class ViewPartida extends JFrame {

	//MATRIZ COM OS BOTÕES DO TABULEIRO
	private JButton[][] viewTabuleiroBts;

	private JPanel contentPane;
	
	private JLabel indicadorCorAtual;
	
	//INSTANCIA DO CONTROLLER
	private ControllerPartida controller;

	public void posicionarPecas() {
		//LOGICA DE POSICIONAR AS PEÇAS
		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {
				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {
					if (contY >= 0 && contY <= 2) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_piece.png"));
					}
					else if (contY >= 5 && contY <= 7) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
					}
				}
			}
		}
	}
	
	public ViewPartida( ControllerPartida ctrl, Player p1, Player p2) {

		this.controller = ctrl;
		viewTabuleiroBts = new JButton[8][8];
		int posIniX = 300;
		int posIniY = 65;

		int x = posIniX;
		int y = posIniY;
		int tamBt = 50;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel player1 = new JLabel(p1.toString());
		player1.setBounds(800, 380, 200, 42);
		contentPane.add(player1);
		
		JLabel player2 = new JLabel(p2.toString());
		player2.setBounds(65, 40, 200, 42);
		contentPane.add(player2);
		
		indicadorCorAtual = new JLabel("VEZ DAS PEÇAS BRANCAS");
		indicadorCorAtual.setBounds(378, 11, 300, 14);
		contentPane.add(indicadorCorAtual);


		
		
		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {
				int posX = contX;
				int posY = contY;
				viewTabuleiroBts[contX][contY] = new JButton();
				viewTabuleiroBts[contX][contY].setBounds(x, y, tamBt, tamBt);

				// CODIGO DE SELEÇÂO DA COR DO CAMPO
				if (contY % 2 == 0) {
					if (contX % 2 == 0) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_field.png"));

					} else {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_field.png"));
					}

				}

				else {

					if (contX % 2 == 0) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_field.png"));
					} else {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_field.png"));
					}

				}
				
				//ADICIONANDO O EVENT LISTENER A TODOS OS BOTOES PARA ACIONAR O CONTROLLER QUANDO ELES FOREM DISPARADO
				viewTabuleiroBts[contX][contY].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						controller.selectPiece(posX, posY);
					}
				});
				

				contentPane.add(viewTabuleiroBts[contX][contY]);
				// LOGICA DE POSICIONAMENTO
				x += tamBt;

				if (x >= posIniX + (tamBt * 8)) {
					x = posIniX;
					y += tamBt;
				}
			}
		}
	}

	public void movePiece(int x, int y, int destinyX, int destinyY, int cor) {
		viewTabuleiroBts[x][y].setIcon(new ImageIcon("src\\img\\black_field.png"));
		if(cor == 0) {
			viewTabuleiroBts[destinyX][destinyY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
			indicadorCorAtual.setText("VEZ DAS PEÇAS BRANCAS");
		}
		else if (cor == 1) {
			viewTabuleiroBts[destinyX][destinyY].setIcon(new ImageIcon("src\\img\\white_piece.png"));
			indicadorCorAtual.setText("VEZ DAS PEÇAS PRETAS");
		}


	}
	public void capturePiece(int x, int y, int destinyX, int destinyY, int capturedPieceX, int capturedPieceY, int cor) {
		viewTabuleiroBts[capturedPieceX][capturedPieceY].setIcon(new ImageIcon("src\\img\\black_field.png"));
		viewTabuleiroBts[x][y].setIcon(new ImageIcon("src\\img\\black_field.png"));
		
		if(cor == 0) {
			viewTabuleiroBts[destinyX][destinyY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
			indicadorCorAtual.setText("VEZ DAS PEÇAS BRANCAS");
		}
		else if (cor == 1) {
			viewTabuleiroBts[destinyX][destinyY].setIcon(new ImageIcon("src\\img\\white_piece.png"));
			indicadorCorAtual.setText("VEZ DAS PEÇAS PRETAS");
		}
	}
}