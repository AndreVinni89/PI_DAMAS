package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerMainScreen;
import controller.ControllerRegistro;

import java.awt.Color;
import java.awt.Toolkit;

public class ViewMainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//REFERENCIA DO CONTROLLER DA MAIN SCREEN
	private ControllerMainScreen controller;
	

	
	public ViewMainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DAMAS");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewMainScreen.class.getResource("/img/black_piece_dama.png")));
		// INSTANCIANDO-SE O CONTROLLER DA MAIN SCREEN
		this.controller = new ControllerMainScreen();
		setBounds(100, 100, 601, 623);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("JOGAR");
		btnPlay.setBackground(Color.DARK_GRAY);
		btnPlay.setForeground(Color.WHITE);
		//LISTENER DO BOTÃO DE PLAY
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//DISPARA O METODO PLAY DO CONTROLLER
				controller.play();
				
			}
		});
		btnPlay.setBounds(418, 151, 188, 41);
		contentPane.add(btnPlay);
		
		//LISTENER DO BOTÃO RANKING PARA ACESSAR A TELA DE RANKING
		JButton btnRanking = new JButton("RANKING");
		btnRanking.setForeground(Color.WHITE);
		btnRanking.setBackground(Color.DARK_GRAY);
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO NAVEGAÇÃO PARA A TELA DE RANKING
			}
		});
		btnRanking.setBounds(418, 330, 188, 41);
		contentPane.add(btnRanking);
		
		JLabel lblNewLabel = new JLabel("DAMAS");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 99));
		lblNewLabel.setBounds(370, 35, 300, 80);
		contentPane.add(lblNewLabel);
		
		JButton btnCadastrar = new JButton("CADASTRAR-SE");
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(Color.DARK_GRAY);
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO NAVAGAÇÃO PARA A TELA DE CADASTRO
				ControllerRegistro controller = new ControllerRegistro ();
				controller.init(controller);
				
				
				
				
			}
		});
		btnCadastrar.setBounds(418, 240, 188, 41);
		contentPane.add(btnCadastrar);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(ViewMainScreen.class.getResource("/img/background.png")));
		background.setBounds(0, 0, 1024, 576);
		contentPane.add(background);
		
	
	}
}
