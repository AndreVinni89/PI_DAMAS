package controller;

import java.awt.EventQueue;

import model.Registro;

import view.ViewRegistro;

public class ControllerRegistro {
	private ViewRegistro frame;
	private Registro registro;
	public void init (ControllerRegistro controller) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewRegistro(controller);
					registro = new Registro (controller);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Boolean registro(String nickname, String senha) {
		// TODO Auto-generated method stub
		return registro.registrar(nickname, senha);
	}

	public void nickExist() {
		frame.nickExist();
		
	}
}
