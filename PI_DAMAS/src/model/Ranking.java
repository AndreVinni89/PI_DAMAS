package model;

import java.util.Collections;
import java.util.List;

import model.dao.PlayerDao;

public class Ranking {


	public Object[][] getTableData() {
		
		
		List<Player> players = PlayerDao.readUserData();

		
		Collections.sort(players);
		
		int size = players.size() + 1;
		
		Object[][] tableData = new Object[size][5];
		

			tableData[0][0] = "NICK";
			tableData[0][1] = "VITORIAS";
			tableData[0][2] = "EMPATES";
			tableData[0][3] = "DERROTAS";
			tableData[0][4] = "PONTUACOES";

			for (int cont = 0; cont < players.size(); cont++) {
				tableData[cont + 1][0] = players.get(cont).getNickname();
				tableData[cont + 1][1] = players.get(cont).getNumVitorias();
				tableData[cont + 1][2] = players.get(cont).getNumEmpates();
				tableData[cont + 1][3] = players.get(cont).getNumDerrotas();
				tableData[cont + 1][4] = players.get(cont).getPontuation();
			}

			return tableData;
		
	}
}
