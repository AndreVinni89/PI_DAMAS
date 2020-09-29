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
	//REFERENCIA DO CONTROLLER DA MAIN SCREEN
	private ControllerMainScreen controller;
	

	
	public ViewMainScreen(ControllerMainScreen controller) {
		// ATRIBUINDO AO ATRIBUTO CONTROLLER A REFERENCIA AO CONTROLLER DA MAINSCREEN
		this.controller = controller;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("JOGAR");
		//LISTENER DO BOTÃO DE PLAY
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//DISPARA O METODO PLAY DO CONTROLLER
				controller.play();
			}
		});
		btnPlay.setBounds(168, 122, 89, 23);
		contentPane.add(btnPlay);
		
		//LISTENER DO BOTÃO RANKING PARA ACESSAR A TELA DE RANKING
		JButton btnRanking = new JButton("RANKING");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO NAVEGAÇÃO PARA A TELA DE RANKING
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
