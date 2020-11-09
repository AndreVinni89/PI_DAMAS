package controller;

import java.awt.EventQueue;

import model.Register;

import view.ViewRegister;

public class ControllerRegister {
	private ViewRegister viewRegister;
	private Register register;
	
	
	public void init (ControllerRegister controller) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewRegister = new ViewRegister(controller);
					register = new Register (controller);
					
					viewRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Boolean register(String nickname, String senha) {
		return register.registerPlayer(nickname, senha);
	}

	public void nickExist() {
		viewRegister.nickExist();
	}
	
	
	
}
