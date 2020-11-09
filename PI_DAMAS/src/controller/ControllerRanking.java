package controller;

import java.awt.EventQueue;

import model.Ranking;
import view.ViewRanking;

public class ControllerRanking {
	
	private ViewRanking frame;
	private Ranking ranking;
	
	
	public void init(ControllerRanking controller) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ranking = new Ranking();		
					frame = new ViewRanking(ranking.getTableData());
					frame.setVisible(true);
					frame.setSize(1024, 576);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	
}
