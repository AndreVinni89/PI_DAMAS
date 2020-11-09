package controller;

import java.awt.EventQueue;

import model.Login;
import model.Player;
import view.ViewLogin;

public class ControllerLogin {

	public ViewLogin frame;

	public Login login;

	private ControllerGame ctrlpartida;

	public ControllerLogin(ControllerGame ctrl) {
		this.ctrlpartida = ctrl;

	}

	public void init(ControllerLogin ctrlogin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewLogin(ctrlogin);
					login = new Login(ctrlogin);
					frame.setVisible(true);
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
		if (ctrlpartida.getPlayer1() == null) {
			ctrlpartida.setPlayer1(p);
		} else if (ctrlpartida.getPlayer2() == null) {
			ctrlpartida.setPlayer2(p);
		}
		if (ctrlpartida.getPlayer1() != null && ctrlpartida.getPlayer2() != null) {
			ctrlpartida.initializeGame();
		}
	}

	public void cancelarPartida() {
		ctrlpartida.cancelGame();
	}

	public void closeWindow() {
		frame.dispose();
	}

	public Player getLogedPlayer() {
		return ctrlpartida.getPlayer1();
		
	}

	public void usuarioJaLogado(String nickname) {
		frame.usuarioJaLogado(nickname);
		
	}
	
	
	
}