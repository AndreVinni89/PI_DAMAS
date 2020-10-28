package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerRegistro;

public class ViewRegistro extends JFrame {

	private JPanel contentPane;
	
	private JTextField nickname;
	private JTextField senha;
	private JTextField senhaconfirm;
	private JLabel lblConfirmarSenha;
	private JButton cadastro;

	/**
	 * Launch the application.
	 */
	private ControllerRegistro controller;

	/**
	 * Create the frame.
	 */
	public ViewRegistro(ControllerRegistro controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nickname = new JTextField();
		nickname.setBounds(85, 67, 256, 20);
		contentPane.add(nickname);
		nickname.setColumns(10);
		
		senha = new JTextField();
		senha.setColumns(10);
		senha.setBounds(85, 124, 256, 20);
		contentPane.add(senha);
		
		senhaconfirm = new JTextField();
		senhaconfirm.setColumns(10);
		senhaconfirm.setBounds(85, 180, 256, 20);
		contentPane.add(senhaconfirm);
		
		JLabel lblNewLabel = new JLabel("NICKNAME");
		lblNewLabel.setBounds(85, 42, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(85, 96, 130, 14);
		contentPane.add(lblSenha);
		
		lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");
		lblConfirmarSenha.setBounds(85, 155, 130, 14);
		contentPane.add(lblConfirmarSenha);
		
		cadastro = new JButton("CADASTRAR-SE");
		cadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.registro(nickname.getText(), senha.getText(), senhaconfirm.getText());

			}
		});
		cadastro.setBounds(154, 227, 130, 23);
		contentPane.add(cadastro);
		
	}

}
