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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		btnRegister = new JButton("CADASTRAR-SE");
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
