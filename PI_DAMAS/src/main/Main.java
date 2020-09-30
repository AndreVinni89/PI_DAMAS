package main;

import java.awt.EventQueue;

import controller.ControllerMainScreen;
import view.ViewMainScreen;

public class Main {

	public static void main(String[] args) {
		
		//INSTANCIANDO-SE A TELA PRINCIPAL
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMainScreen frame = new ViewMainScreen();
					frame.setVisible(true);
					//DEFININDO TAMANHO DA JANELA
					frame.setSize(1024, 576);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
		

		
		
		
		
		
		
	}
	
	
}
