package view;

import java.awt.EventQueue;

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
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField nickname;
	private JTextField senha;

	private ControllerLogin ctrlogin;

	public ViewLogin(ControllerLogin ctrlogin) {
		setResizable(false);
		this.ctrlogin = ctrlogin;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nickname = new JTextField();
		nickname.setBounds(123, 88, 193, 20);
		contentPane.add(nickname);
		nickname.setColumns(10);

		senha = new JTextField();
		senha.setColumns(10);
		senha.setBounds(123, 152, 193, 20);
		contentPane.add(senha);

		JButton Logar = new JButton("LOGAR");
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
		Logar.setBounds(172, 196, 89, 23);
		contentPane.add(Logar);

		JLabel lblNewLabel = new JLabel("NICKNAME");
		lblNewLabel.setBounds(124, 63, 96, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(123, 127, 96, 14);
		contentPane.add(lblSenha);
		
		JButton cancelar = new JButton("Cancelar Partida");
		cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ctrlogin.cancelarPartida();
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelar.setBounds(156, 237, 126, 23);
		contentPane.add(cancelar);
	}

	public void usuarioJaLogado(String nickname2) {
		JOptionPane.showMessageDialog(null, "O USUÁRIO JA LOGOU NESSA PARTIDA");
		
	}

	
	
}
