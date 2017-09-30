package br.com.rarp.view.main.scnMain;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Main {
	
	public static void main(String[] args) {
		try {		
//			SistemaCtrl.getInstance().getConfiguracoes().setUsuario("teste");
//			
//			for (Field field : SistemaCtrl.getInstance().getConfiguracoes().getClass().getDeclaredFields()	) {
//				field.setAccessible(true);
//				System.out.println(field.get(SistemaCtrl.getInstance().getConfiguracoes()));
//			}
			
			MainController.abrir();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Erro ao inicializar a aplica��o. "
					+ "\n\tEntre em contato com o grupo RARP telefone: (62)"
					+ " 98526-4619\\ (62) 98548-3271", ButtonType.OK).show();
			e.printStackTrace();
		}
	}
	

}
