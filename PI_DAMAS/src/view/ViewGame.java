package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ViewGame extends JFrame {

	JButton[][] listPos;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	

	public void posicionarPecas() {

		for (int contX = 0; contX < 8; contX++) {
			for (int contY = 0; contY < 8; contY++) {
				
				
				
				
				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {
								
					
					if (contX >= 0 && contX <= 2){
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\white_piece.png"));
						
					}
					
					else if (contX >= 5 && contX <= 7) {
						
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
					}
					
					
					
				}
				
				
				
				
			}
		}
	}

	public ViewGame() {

		listPos = new JButton[8][8];
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
				listPos[contX][contY] = new JButton();
				listPos[contX][contY].setBounds(x, y, tamBt, tamBt);

				// CODIGO DE SELEÇÂO DA COR
				if (contY % 2 == 0) {
					if (contX % 2 == 0) {
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\white_field.png"));

					} else {
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\black_field.png"));
					}

				}

				else {

					if (contX % 2 == 0) {
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\black_field.png"));
					} else {
						listPos[contX][contY].setIcon(new ImageIcon("src\\img\\white_field.png"));
					}

				}

				contentPane.add(listPos[contX][contY]);
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