package controller;




public class ControllerMainScreen {

	
	//METODO DISPARADO QUANDO O USUARIO CLICA NO BOTAO JOGAR
	public void play() {
		//INSTANCIA-SE O CONTROLLER PARTIDA
		ControllerGame ctrlGame = new ControllerGame();
		//EXECUTA O METODO INIT DO CONTROLLER PARTIDA PASSANDO A REFERENCIA DO PROPRIO CONTROLLER PARTIDA COMO PARAMETRO
		ctrlGame.init(ctrlGame);
		
	}

	public void register() {
		ControllerRegister controller = new ControllerRegister();
		controller.init(controller);
	
		
	}

	public void ranking() {
		ControllerRanking controller = new ControllerRanking();
		controller.init(controller);
		
		
	}
	
}
