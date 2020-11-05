package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ControllerLogin;

public class Login {

	private ControllerLogin controller;

	public Login(ControllerLogin ctrlogin) {
		this.controller = ctrlogin;
	}

	public boolean logar(String nickname, String senha) {
		List<Player> playersRegistrados = new ArrayList<>();
		File file = new File("C:\\PI_Damas\\users");
		Scanner obj = null;
		try {
			obj = new Scanner(file);
			while (obj.hasNextLine()) {
				String[] dados = obj.nextLine().split(";");
				playersRegistrados.add(new Player(dados[0], dados[1], Integer.parseInt(dados[2]),
						Integer.parseInt(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[5])));
			}
			System.out.println(playersRegistrados);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (obj != null) {
				obj.close();
			}
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
