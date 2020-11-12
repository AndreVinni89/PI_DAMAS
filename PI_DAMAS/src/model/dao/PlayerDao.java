package model.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Player;

public class PlayerDao {

	public static List<Player> readUserData() {
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
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (obj != null) {
				obj.close();
			}
		}

		return playersRegistrados;
	}

	public static Boolean registerUser(String nickname, String senha) {

		try {
			String[] lines = new String[] { nickname, ";", senha, ";", "0", ";", "0", ";", "0", ";", "0", ";", "\n" };
			new File("C:\\" + "PI_Damas").mkdir();
			String path = "C:\\PI_Damas\\users";
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
				for (String line : lines) {
					bw.write(line);
				}
			} catch (IOException e) {
				e.printStackTrace();

			}

		} catch (Exception e) {
			return false;
		}
		System.out.println("Conteudo inserido no arquivo");
		return true;

	}

	public static Boolean attUserData(Player player) {
		List<Player> playersRegistrados = readUserData();

		
		//APAGANDO O ARQUIVO ANTERIOR
		File file = new File("C:\\PI_Damas\\users");
		file.delete();

		for (Player p : playersRegistrados) {
			if (p.getNickname().equals(player.getNickname())) {
				p.attData(player.getNumVitorias(), player.getNumEmpates(), player.getNumDerrotas(), player.getPontuation());
			}
		}
		for(Player p: playersRegistrados) {	
			String[] lines = new String[] { p.getNickname(), ";", p.getPassword(), ";",
					Integer.toString(p.getNumVitorias()), ";", Integer.toString(p.getNumEmpates()), ";",
					Integer.toString(p.getNumDerrotas()), ";", Integer.toString(p.getPontuation()), ";", "\n" };
			

			
			new File("C:\\" + "PI_Damas").mkdir();
			String path = "C:\\PI_Damas\\users";
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
				for (String line : lines) {
					bw.write(line);
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		playersRegistrados.clear();
		return true;
	}


}
