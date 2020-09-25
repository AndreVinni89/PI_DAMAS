package view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerPartida;


public class ViewPartida extends JFrame {

	private JButton[][] viewTabuleiroBts;

	private JPanel contentPane;
	
	private ControllerPartida controller;
	

	public JButton[][] getListPos(){
		return viewTabuleiroBts;
	}
	
	public void posicionarPecas() {

		for (int contX = 0; contX < 8; contX++) {
			for (int contY = 0; contY < 8; contY++) {

				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {

					if (contX >= 0 && contX <= 2) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_piece.png"));

					}

					else if (contX >= 5 && contX <= 7) {

						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
					}

				}

			}
		}
	}
	
	public ViewPartida( ControllerPartida ctrl) {
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


		
		
		for (int contX = 0; contX < 8; contX++) {
			for (int contY = 0; contY < 8; contY++) {
				int posX = contX;
				int posY = contY;
				viewTabuleiroBts[contX][contY] = new JButton();
				viewTabuleiroBts[contX][contY].setBounds(x, y, tamBt, tamBt);

				// CODIGO DE SELEÇÂO DA COR
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
				
				viewTabuleiroBts[contX][contY].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						controller.selectPiece(posX, posY);
					}
				});
				

				contentPane.add(viewTabuleiroBts[contX][contY]);
				// LOGICA DE POSICIONAMENTO
				x += 50;

				if (x >= posIniX + (tamBt * 8)) {
					x = posIniX;
					y += tamBt;
				}

			}
		}

	}
}