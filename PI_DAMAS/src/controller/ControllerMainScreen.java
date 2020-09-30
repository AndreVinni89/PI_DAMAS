package controller;




public class ControllerMainScreen {

	
	//METODO DISPARADO QUANDO O USUARIO CLICA NO BOTAO JOGAR
	public void play() {
		//INSTANCIA-SE O CONTROLLER PARTIDA
		ControllerPartida ctrlGame = new ControllerPartida();
		
		//EXECUTA O METODO INIT DO CONTROLLER PARTIDA PASSANDO A REFERENCIA DO PROPRIO CONTROLLER PARTIDA COMO PARAMETRO
		ctrlGame.init(ctrlGame);
		
	}
	
}
