package main;

import java.awt.EventQueue;

import controller.ControllerMainScreen;
import view.ViewMainScreen;

public class Main {

	public static void main(String[] args) {
		
		ControllerMainScreen controllerMainScreen = new ControllerMainScreen();
		
		
		//Abrindo tela inicial
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMainScreen frame = new ViewMainScreen(controllerMainScreen);
					frame.setVisible(true);
					frame.setSize(1024, 576);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
		
		

		
		
		
		
		
		
	}
	
	
}
