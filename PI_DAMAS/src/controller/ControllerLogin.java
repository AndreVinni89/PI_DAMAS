package controller;

import java.awt.EventQueue;

import model.Login;
import model.Player;
import view.ViewLogin;

public class ControllerLogin {

	public ViewLogin viewLogin;
	public Login login;

	private ControllerGame ctrlGame;

	public ControllerLogin(ControllerGame ctrl) {
		this.ctrlGame = ctrl;

	}

	public void init(ControllerLogin ctrlogin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewLogin = new ViewLogin(ctrlogin);
					login = new Login(ctrlogin);
					viewLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public boolean logar(String nickname, String senha) {
		return login.logar(nickname, senha);

	}

	public void logou(Player p) {
		if (ctrlGame.getPlayer1() == null) {
			ctrlGame.setPlayer1(p);
		} else if (ctrlGame.getPlayer2() == null) {
			ctrlGame.setPlayer2(p);
		}
		if (ctrlGame.getPlayer1() != null && ctrlGame.getPlayer2() != null) {
			ctrlGame.initializeGame();
		}
	}

	public void cancelGame() {
		ctrlGame.cancelGame();
	}

	public void closeWindow() {
		viewLogin.dispose();
	}

	public Player getLoggedPlayer() {
		return ctrlGame.getPlayer1();
	}

	public void usuarioJaLogado(String nickname) {
		viewLogin.usuarioJaLogado(nickname);
	}
	
	
	
}