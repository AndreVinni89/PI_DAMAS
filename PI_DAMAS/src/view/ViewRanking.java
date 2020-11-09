package view;


import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class ViewRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable usersDataTable;


	public ViewRanking(Object[][] tableData) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usersDataTable = new JTable();

		
		usersDataTable.setModel(new DefaultTableModel(
			tableData,
			new String[] {
				"NICK", "VITORIAS", "EMPATES", "DERROTAS", "PONTUCOES"
			}
		));
		usersDataTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		usersDataTable.setBounds(70, 40, 884, 456);
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(70, 40, 884, 456);
		scroll.add(usersDataTable);
		
		contentPane.add(scroll);
		

		

	}





}
