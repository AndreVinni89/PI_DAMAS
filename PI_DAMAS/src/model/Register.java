package model;


import java.util.List;
import controller.ControllerRegister;
import model.dao.PlayerDao;

public class Register {

	private ControllerRegister controller;

	public Register(ControllerRegister controller) {
		this.controller = controller;
	}

	public Boolean registerPlayer(String nickname, String senha) {
		List<Player> registeredPlayers = PlayerDao.readUserData();
		
		
		//VERIFICANDO SE O NICKNAME JA ESTA REGISTRADO
		for (Player p : registeredPlayers) {
			if (p.getNickname().equals(nickname)) {
				controller.nickExist();
				return false;
			}
		}

		return PlayerDao.registerUser(nickname, senha);
		
	}

}
