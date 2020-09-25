package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerMainScreen;

public class ViewMainScreen extends JFrame {

	private JPanel contentPane;
	private ControllerMainScreen controller;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	
	
	public ViewMainScreen(ControllerMainScreen controller) {
		this.controller = controller;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("JOGAR");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.play();
			}
		});
		btnPlay.setBounds(168, 122, 89, 23);
		contentPane.add(btnPlay);
		
		JButton btnRanking = new JButton("RANKING");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRanking.setBounds(168, 175, 89, 23);
		contentPane.add(btnRanking);
		
		JLabel lblNewLabel = new JLabel("DAMAS");
		lblNewLabel.setFont(new Font("Vladimir Script", Font.PLAIN, 22));
		lblNewLabel.setBounds(157, 25, 162, 69);
		contentPane.add(lblNewLabel);
	}
}
