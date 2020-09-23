package main;

import java.awt.EventQueue;

import view.ViewGame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGame frame = new ViewGame();
					frame.setVisible(true);
					frame.setSize(1024, 576);
					frame.posicionarPecas();


					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});

		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
