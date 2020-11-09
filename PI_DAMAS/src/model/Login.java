package model;


import java.util.List;


import controller.ControllerLogin;

public class Login {

	private ControllerLogin controller;

	public Login(ControllerLogin ctrlogin) {
		this.controller = ctrlogin;
	}

	public boolean logar(String nickname, String senha) {
		List<Player> playersRegistrados = ConnectionUsersData.readUserData();
		
		
		Player pLoged = controller.getLogedPlayer();
		
		
		if(pLoged != null && pLoged.getNickname().equals(nickname)) {
			controller.usuarioJaLogado(nickname);
			
			return false;
		}
		
		
		for (Player p: playersRegistrados ) {
			if (p.getNickname().equals(nickname) && p.getPassword().equals(senha)){
				controller.logou(p);
				return true;				
			}
		}
		return false;
		
	}

}
