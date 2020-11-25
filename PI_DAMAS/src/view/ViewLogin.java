package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerLogin;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class ViewLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField nickname;
	private JTextField senha;

	private ControllerLogin ctrlogin;

	public ViewLogin(ControllerLogin ctrlogin, String player) {
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/img/black_piece_dama.png")));
		setBackground(Color.GRAY);
		setResizable(false);
		this.ctrlogin = ctrlogin;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nickname = new JTextField();
		nickname.setFont(new Font("Impact", Font.PLAIN, 14));
		nickname.setBackground(Color.LIGHT_GRAY);
		nickname.setBounds(123, 88, 193, 20);
		contentPane.add(nickname);
		nickname.setColumns(10);

		senha = new JTextField();
		senha.setFont(new Font("Impact", Font.PLAIN, 14));
		senha.setBackground(Color.LIGHT_GRAY);
		senha.setColumns(10);
		senha.setBounds(123, 152, 193, 20);
		contentPane.add(senha);

		JButton Logar = new JButton("LOGAR");
		Logar.setFont(new Font("Impact", Font.PLAIN, 18));
		Logar.setForeground(Color.WHITE);
		Logar.setBackground(Color.DARK_GRAY);
		Logar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (ctrlogin.logar(nickname.getText(), senha.getText())) {
					JOptionPane.showMessageDialog(null, "Êxito no Login :)");
					dispose();
				}

				else {
					JOptionPane.showMessageDialog(null, "Falha no login >:(");
					nickname.setText("");
					senha.setText("");
				}
			}
		});
		Logar.setBounds(172, 196, 89, 30);
		contentPane.add(Logar);

		JLabel lblNewLabel = new JLabel("NICKNAME");
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(124, 63, 96, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setFont(new Font("Impact", Font.PLAIN, 14));
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(123, 127, 96, 14);
		contentPane.add(lblSenha);
		
		JButton cancelar = new JButton("Cancelar Partida");
		cancelar.setFont(new Font("Impact", Font.PLAIN, 17));
		cancelar.setForeground(Color.WHITE);
		cancelar.setBackground(Color.DARK_GRAY);
		cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ctrlogin.cancelGame();
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelar.setBounds(139, 237, 160, 23);
		contentPane.add(cancelar);
		
		JLabel Player = new JLabel("");
		Player.setFont(new Font("Impact", Font.PLAIN, 20));
		Player.setForeground(Color.WHITE);
		Player.setBounds(166, 11, 165, 30);
		Player.setText(player);
		contentPane.add(Player);
	}

	public void usuarioJaLogado(String nickname2) {
		JOptionPane.showMessageDialog(null, "O USUÁRIO JA LOGOU NESSA PARTIDA");
		
	}


}
