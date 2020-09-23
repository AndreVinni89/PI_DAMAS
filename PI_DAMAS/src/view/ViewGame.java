package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ViewGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGame frame = new ViewGame();
					frame.setVisible(true);
					frame.setSize(1024, 576);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewGame() {

		JButton[][] listPos = new JButton[8][8];
		int posIniX = 300;
		int posIniY = 65;
		
		int x = posIniX;
		int y = posIniY;
		int tamBt = 50;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
						listPos[contX][contY].setIcon(new ImageIcon(
								"src\\img\\white_field.jpg"));

							

						
					} else {
						listPos[contX][contY].setIcon(new ImageIcon(
								"src\\img\\black_field.jpg"));
					}

				}

				else {

					if (contY % 2 != 0) {
						if (contX % 2 == 0) {
							listPos[contX][contY].setIcon(new ImageIcon(
									"src\\img\\black_field.jpg"));
						} else {
							listPos[contX][contY].setIcon(new ImageIcon(
									"src\\img\\white_field.jpg"));
						}
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
		
		
		listPos[5][5].setIcon(new ImageIcon(
				"src\\img\\white_piece.jpg"));



	}
}