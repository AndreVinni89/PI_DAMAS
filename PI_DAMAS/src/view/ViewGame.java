package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerGame;
import model.PieceColor;
import model.Player;
import model.Posicao;

public class ViewGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// MATRIZ COM OS BOTÕES DO TABULEIRO
	private JButton[][] viewTabuleiroBts;

	private JPanel contentPane;

	private JLabel indicadorCorAtual;

	private JLabel player1;

	private JLabel player2;

	private Player p1;
	private Player p2;

	private List<Posicao> SelectedFields = new ArrayList<>();

	// INSTANCIA DO CONTROLLER
	private ControllerGame controller;

	private List<Posicao> movimentos = new ArrayList<>();

	private JLabel moldura_vertical2;

	public ViewGame(ControllerGame ctrl, Player p1, Player p2) {
		setTitle("DAMAS");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewGame.class.getResource("/img/black_piece_dama.png")));
		setBackground(Color.GRAY);

		this.p1 = p1;
		this.p2 = p2;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		player1 = new JLabel(p1.toString());
		player1.setFont(new Font("Impact", Font.PLAIN, 18));
		player1.setForeground(Color.WHITE);
		player1.setBounds(750, 380, 200, 42);
		contentPane.add(player1);

		player2 = new JLabel(p2.toString());
		player2.setForeground(Color.GREEN);
		player2.setFont(new Font("Impact", Font.PLAIN, 18));
		player2.setBounds(65, 40, 200, 42);
		contentPane.add(player2);

		indicadorCorAtual = new JLabel("VEZ DAS PEÇAS BRANCAS");
		indicadorCorAtual.setFont(new Font("Impact", Font.PLAIN, 18));
		indicadorCorAtual.setForeground(Color.WHITE);
		indicadorCorAtual.setBounds(420, -3, 300, 50);
		contentPane.add(indicadorCorAtual);

		moldura_vertical2 = new JLabel("");
		moldura_vertical2.setBounds(700, 65, 34, 400);
		moldura_vertical2.setIcon(new ImageIcon("src\\img\\vertical_moldura.png"));
		contentPane.add(moldura_vertical2);

		JLabel moldura_vertical1 = new JLabel("");
		moldura_vertical1.setBounds(266, 65, 34, 400);
		moldura_vertical1.setIcon(new ImageIcon("src\\img\\vertical_moldura.png"));
		contentPane.add(moldura_vertical1);

		JLabel moldura_horizontal1 = new JLabel("");
		moldura_horizontal1.setBounds(266, 34, 468, 31);
		moldura_horizontal1.setIcon(new ImageIcon("src\\img\\horizontal_moldura.png"));
		contentPane.add(moldura_horizontal1);

		JLabel moldura_horizontal2 = new JLabel("");
		moldura_horizontal2.setBounds(266, 465, 468, 31);
		moldura_horizontal2.setIcon(new ImageIcon("src\\img\\horizontal_moldura.png"));
		contentPane.add(moldura_horizontal2);

		// TESTE TODO
		// -------------------------------------------------------------------------------
		JButton desistir = new JButton("DESISTIR");
		desistir.setForeground(Color.WHITE);
		desistir.setBackground(Color.DARK_GRAY);

		desistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				controller.desistir();

			}
		});

		desistir.setBounds(50, 15, 89, 23);
		contentPane.add(desistir);
		// -------------------------------------------------------------------------------;

		this.controller = ctrl;
		viewTabuleiroBts = new JButton[8][8];
		int posIniX = 300;
		int posIniY = 65;

		int x = posIniX;
		int y = posIniY;
		int tamBt = 50;

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
				} else {
					if (contX % 2 == 0) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_field.png"));
					} else {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_field.png"));
					}
				}
				// ADICIONANDO O EVENT LISTENER A TODOS OS BOTOES PARA ACIONAR O CONTROLLER
				// QUANDO ELES FOREM DISPARADO
				viewTabuleiroBts[contX][contY].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// SE JA HAVIAM MOVIMENTOS COM AS POSIÇÕES VISUALMENTE ALTERADAS ZERAM-SE ESSA
						// ALTERAÇÕES
						if (movimentos.size() > 0) {
							if (movimentos.get(0).getPeca().getCor() == PieceColor.PRETO) {
								if (movimentos.get(0).getPeca().getDama() == true) {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece_dama.png"));
								} else {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece.png"));
								}
							} else {
								if (movimentos.get(0).getPeca().getDama() == true) {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece_dama.png"));
								} else {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece.png"));
								}
							}
							for (int cont = 1; cont < movimentos.size(); cont++) {
								viewTabuleiroBts[movimentos.get(cont).getX()][movimentos.get(cont).getY()]
										.setIcon(new ImageIcon("src\\img\\black_field.png"));
							}
						}

						for (Posicao pos : SelectedFields) {
							if (!pos.getPeca().getDama()) {
								if (pos.getPeca().getCor() == PieceColor.PRETO) {
									viewTabuleiroBts[pos.getX()][pos.getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece_capture.png"));

								} else {
									viewTabuleiroBts[pos.getX()][pos.getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece_capture.png"));
								}
							}

							else {
								if (pos.getPeca().getCor() == PieceColor.PRETO) {
									viewTabuleiroBts[pos.getX()][pos.getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece_dama_capture.png"));

								} else {
									viewTabuleiroBts[pos.getX()][pos.getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece_dama_capture.png"));
								}
							}

						}

						// CHAMA O METODO NO CONTROLLER
						movimentos = controller.selectPiece(posX, posY);

						// COM AS ALTERAÇÕES RECEBIDAS ALTERA-SE AS PEÇA VISUALMENTE
						if (movimentos.size() > 0) {
							if (movimentos.get(0).getPeca().getCor() == PieceColor.PRETO) {
								if (movimentos.get(0).getPeca().getDama() == true) {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece_dama_selected.png"));
								} else {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\black_piece_selected.png"));
								}

							} else {
								if (movimentos.get(0).getPeca().getDama() == true) {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece_dama_selected.png"));
								} else {
									viewTabuleiroBts[movimentos.get(0).getX()][movimentos.get(0).getY()]
											.setIcon(new ImageIcon("src\\img\\white_piece_selected.png"));
								}
							}
							for (int cont = 1; cont < movimentos.size(); cont++) {
								viewTabuleiroBts[movimentos.get(cont).getX()][movimentos.get(cont).getY()]
										.setIcon(new ImageIcon("src\\img\\green_field.png"));
							}
						}

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

	public void posicionarPecas() {
		// LOGICA DE POSICIONAR AS PEÇAS
		for (int contY = 0; contY < 8; contY++) {
			for (int contX = 0; contX < 8; contX++) {
				if ((contY % 2 == 0 && contX % 2 != 0) || (contY % 2 != 0 && contX % 2 == 0)) {
					if (contY >= 0 && contY <= 2) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\white_piece.png"));
					} else if (contY >= 5 && contY <= 7) {
						viewTabuleiroBts[contX][contY].setIcon(new ImageIcon("src\\img\\black_piece.png"));
					}
				}
			}
		}
	}

	public void movePiece(Posicao origem, Posicao destino) {
		viewTabuleiroBts[origem.getX()][origem.getY()].setIcon(new ImageIcon("src\\img\\black_field.png"));
		if (origem.getPeca().getCor() == PieceColor.PRETO) {
			if (destino.getY() == 0) {
				viewTabuleiroBts[destino.getX()][destino.getY()]
						.setIcon(new ImageIcon("src\\img\\black_piece_dama.png"));
			} else {
				if (origem.getPeca().getDama() == true) {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece_dama.png"));
				} else {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece.png"));
				}

			}
			indicadorCorAtual.setText("VEZ DAS PEÇAS BRANCAS");
			player2.setForeground(Color.GREEN);
			player1.setForeground(Color.WHITE);
		} else if (origem.getPeca().getCor() == PieceColor.BRANCO) {
			if (destino.getY() == 7) {
				viewTabuleiroBts[destino.getX()][destino.getY()]
						.setIcon(new ImageIcon("src\\img\\white_piece_dama.png"));
			} else {

				if (origem.getPeca().getDama() == true) {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece_dama.png"));
				} else {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece.png"));
				}
			}
			indicadorCorAtual.setText("VEZ DAS PEÇAS PRETAS");
			player1.setForeground(Color.GREEN);
			player2.setForeground(Color.WHITE);
		}
	}

	public void capturePiece(Posicao origem, Posicao destino, List<Posicao> capturedPieces) {

		// ITERA-SE SOBRE AS PEÇAS CAPTURADAS
		for (Posicao capturedPiece : capturedPieces) {
			viewTabuleiroBts[capturedPiece.getX()][capturedPiece.getY()]
					.setIcon(new ImageIcon("src\\img\\black_field.png"));
		}

		if (SelectedFields.size() > 0) {
			for (Posicao pos : SelectedFields) {
				if (origem.getPeca().getCor() == PieceColor.BRANCO) {
					viewTabuleiroBts[pos.getX()][pos.getY()].setIcon(new ImageIcon("src\\img\\white_piece.png"));
				} else {
					viewTabuleiroBts[pos.getX()][pos.getY()].setIcon(new ImageIcon("src\\img\\black_piece.png"));
				}
			}
			SelectedFields.clear();
		}

		viewTabuleiroBts[origem.getX()][origem.getY()].setIcon(new ImageIcon("src\\img\\black_field.png"));

		if (origem.getPeca().getCor() == PieceColor.PRETO) {
			if (destino.getY() == 0) {
				viewTabuleiroBts[destino.getX()][destino.getY()]
						.setIcon(new ImageIcon("src\\img\\black_piece_dama.png"));
			} else {
				if (origem.getPeca().getDama() == true) {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece_dama.png"));
				} else {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece.png"));
				}
			}
			indicadorCorAtual.setText("VEZ DAS PEÇAS BRANCAS");
			player2.setForeground(Color.GREEN);
			player1.setForeground(Color.WHITE);

		} else if (origem.getPeca().getCor() == PieceColor.BRANCO) {
			if (destino.getY() == 7) {
				viewTabuleiroBts[destino.getX()][destino.getY()]
						.setIcon(new ImageIcon("src\\img\\white_piece_dama.png"));
			} else {
				if (origem.getPeca().getDama() == true) {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece_dama.png"));
				} else {
					viewTabuleiroBts[destino.getX()][destino.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece.png"));
				}
			}
			indicadorCorAtual.setText("VEZ DAS PEÇAS PRETAS");
			player1.setForeground(Color.GREEN);
			player2.setForeground(Color.WHITE);
		}

	}

	public void sendObrigatedCaptureInfo(List<Posicao> possibleSelectedFields) {
		SelectedFields.clear();
		for (Posicao pos : possibleSelectedFields) {
			SelectedFields.add(pos);
			if (!pos.getPeca().getDama()) {
				if (pos.getPeca().getCor() == PieceColor.PRETO) {
					viewTabuleiroBts[pos.getX()][pos.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece_capture.png"));

				} else {
					viewTabuleiroBts[pos.getX()][pos.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece_capture.png"));
				}
			}

			else {
				if (pos.getPeca().getCor() == PieceColor.PRETO) {
					viewTabuleiroBts[pos.getX()][pos.getY()]
							.setIcon(new ImageIcon("src\\img\\black_piece_dama_capture.png"));

				} else {
					viewTabuleiroBts[pos.getX()][pos.getY()]
							.setIcon(new ImageIcon("src\\img\\white_piece_dama_capture.png"));
				}
			}

		}

	}

	public void endGame(PieceColor corVitoria, Boolean empate) {
		if (empate) {
			JOptionPane.showMessageDialog(null, "PARTIDA EMPATADA!!!");
		} else {

			if (corVitoria == p1.getPieceColor()) {
				JOptionPane.showMessageDialog(null, p1.getNickname() + " VENCEU!!!");
			} else {
				JOptionPane.showMessageDialog(null, p2.getNickname() + " VENCEU!!!");
			}

		}

		dispose();

	}
}