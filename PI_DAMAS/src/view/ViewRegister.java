package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewRegister extends JFrame {

	private JPanel contentPane;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblConfirmarSenha;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRegister frame = new ViewRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(85, 67, 256, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(85, 124, 256, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(85, 180, 256, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("NICKNAME");
		lblNewLabel.setBounds(85, 42, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(85, 96, 130, 14);
		contentPane.add(lblSenha);
		
		lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");
		lblConfirmarSenha.setBounds(85, 155, 130, 14);
		contentPane.add(lblConfirmarSenha);
		
		btnNewButton = new JButton("CADASTRAR-SE");
		btnNewButton.setBounds(154, 227, 130, 23);
		contentPane.add(btnNewButton);
		
	}

}
