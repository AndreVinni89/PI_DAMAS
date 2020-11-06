package controller;

import java.awt.EventQueue;

import model.Login;
import model.Player;
import view.ViewLogin;

public class ControllerLogin {

	public ViewLogin frame;

	public Login login;

	private ControllerPartida ctrlpartida;

	public ControllerLogin(ControllerPartida ctrl) {
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
			ctrlpartida.iniciarJogo();
		}
	}

	public void cancelarPartida() {
		ctrlpartida.cancelarPartida();
	}

	public void fecharjanela() {
		frame.dispose();
		
	}
}