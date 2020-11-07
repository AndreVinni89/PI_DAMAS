package view;


import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerRanking;

public class ViewRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ControllerRanking controller;


	public ViewRanking(ControllerRanking controller, Object[][] tableData) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();

		
		table.setModel(new DefaultTableModel(
			tableData,
			new String[] {
				"NICK", "VITORIAS", "EMPATES", "DERROTAS", "PONTUCOES"
			}
		));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(70, 40, 884, 456);
		
		
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(70, 40, 884, 456);
		scroll.add(table);
		
		contentPane.add(scroll);
		

		

	}
}
