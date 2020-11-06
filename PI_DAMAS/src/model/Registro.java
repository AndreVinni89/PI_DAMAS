package model;


import java.util.List;
import controller.ControllerRegistro;

public class Registro {

	private ControllerRegistro controller;

	public Registro(ControllerRegistro controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	public Boolean registrar(String nickname, String senha) {
		List<Player> playersRegistrados = ConnectionUsersData.readUserData();
		
		
		//VERIFICANDO SE O NICKNAME JA ESTA REGISTRADO
		for (Player p : playersRegistrados) {
			if (p.getNickname().equals(nickname)) {
				controller.nickExist();
				return false;
			}
		}

		return ConnectionUsersData.registerUser(playersRegistrados, nickname, senha);
		
	}

}
