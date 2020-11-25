package view;


import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.awt.Font;


public class ViewRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable usersDataTable;


	public ViewRanking(Object[][] tableData) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewRanking.class.getResource("/img/black_piece_dama.png")));
		setTitle("RANKING");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1012, 781);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usersDataTable = new JTable();
		usersDataTable.setFont(new Font("Impact", Font.PLAIN, 18));
		usersDataTable.setForeground(Color.WHITE);
		usersDataTable.setBackground(Color.GRAY);

		
		usersDataTable.setModel(new DefaultTableModel(
			tableData,
			new String[] {
				"NICK", "VITORIAS", "EMPATES", "DERROTAS", "PONTUCOES"
			}
		)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		}	
				);
		usersDataTable.setBorder(null);
		usersDataTable.setBounds(70, 40, 884, 456);
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(70, 40, 884, 456);
		scroll.add(usersDataTable);
		
		contentPane.add(scroll);
		

		

	}





}
