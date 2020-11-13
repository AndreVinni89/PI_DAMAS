package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerRegister;
import java.awt.Color;
import java.awt.Toolkit;

public class ViewRegister extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField nickname;
	private JTextField senha;
	private JTextField senhaconfirm;
	private JLabel lblConfirmarSenha;
	
	
	private JButton btnRegister;
	private boolean nickExist = false;


	public ViewRegister(ControllerRegister controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewRegister.class.getResource("/img/black_piece_dama.png")));
		setResizable(false);
		setTitle("REGISTRO ");
		setBackground(Color.DARK_GRAY);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nickname = new JTextField();
		nickname.setBackground(Color.LIGHT_GRAY);
		nickname.setBounds(85, 67, 256, 20);
		contentPane.add(nickname);
		nickname.setColumns(10);

		senha = new JTextField();
		senha.setBackground(Color.LIGHT_GRAY);
		senha.setColumns(10);
		senha.setBounds(85, 124, 256, 20);
		contentPane.add(senha);

		senhaconfirm = new JTextField();
		senhaconfirm.setBackground(Color.LIGHT_GRAY);
		senhaconfirm.setColumns(10);
		senhaconfirm.setBounds(85, 180, 256, 20);
		contentPane.add(senhaconfirm);

		JLabel lblNewLabel = new JLabel("NICKNAME");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(85, 42, 130, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(85, 96, 130, 14);
		contentPane.add(lblSenha);

		lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");
		lblConfirmarSenha.setForeground(Color.WHITE);
		lblConfirmarSenha.setBounds(85, 155, 130, 14);
		contentPane.add(lblConfirmarSenha);

		btnRegister = new JButton("CADASTRAR-SE");
		btnRegister.setBackground(Color.DARK_GRAY);
		btnRegister.setForeground(Color.WHITE);
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (nickname.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "O NOME NÃO PODE CONTER O CARACTERE ; ");
					nickname.setText("");
					nickname.requestFocus();
				} else if (senha.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "A SENHA NÃO PODE CONTER O CARACTERE ; ");
					senha.setText("");
					senha.requestFocus();
				} else if (nickname.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "PREENCHA O CAMPO NICKNAME");
					nickname.requestFocus();
				} else if (senha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "PREENCHA O CAMPO DE SENHA");
					senha.requestFocus();
				} else if (senhaconfirm.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "PREENCHA O CAMPO DE CONFIRMAÇÃO DE SENHA");
					senhaconfirm.requestFocus();
				} else if(nickname.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "O NOME NÃO PODE CONTER ESPAÇOES EM BRANCO ");
					nickname.setText(nickname.getText().replaceAll(" ", ""));
					nickname.requestFocus();
				} else if(senha.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "A SENHA NÃO PODE CONTER ESPAÇOES EM BRANCO ");
					senha.setText(senha.getText().replaceAll(" ", ""));
					senha.requestFocus();
				} else if (!senhaconfirm.getText().equals(senha.getText())) {
					JOptionPane.showMessageDialog(null, "AS SENHAS NAO SÃO IGUAIS!");
					senhaconfirm.setText("");
					senhaconfirm.requestFocus();
				} else {
					Boolean result = controller.register(nickname.getText().trim(), senha.getText().trim());
					if (result) {
						JOptionPane.showMessageDialog(null, "REGISTRO REALIZADO COM SUCESSO!!!");
						dispose();
					} else if(!nickExist){
						JOptionPane.showMessageDialog(null, "FALHA NO REGISTRO TENTE NOVAMENTE");
					}
					nickExist = true;
					
				}

			}
		});
		btnRegister.setBounds(154, 227, 130, 23);
		contentPane.add(btnRegister);

	}

	public void nickExist() {
		nickExist  = true;
		JOptionPane.showMessageDialog(null, "O NICK JÁ EXISTE TENTE NOVAMENTE");
		nickname.setText("");
		nickname.requestFocus();
	}
}
