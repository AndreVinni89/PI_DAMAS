package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import controller.ControllerRegistro;

public class Registro {
	
	private ControllerRegistro controller;
	

	public Registro(ControllerRegistro controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	public void registrar(String nickname, String senha, String senhaconfirm) {
		// TODO Auto-generated method stub
		String[] lines = new String[] {nickname, ";", senha, "\n"}; 
		new File("C:\\" + "PI_Damas").mkdir();
		String path = "C:\\PI_Damas\\users";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {

			for (String line : lines) {
				bw.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Conteudo inserido no arquivo");

	}

}
