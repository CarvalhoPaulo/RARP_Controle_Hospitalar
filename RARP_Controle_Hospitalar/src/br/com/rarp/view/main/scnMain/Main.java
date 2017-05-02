package br.com.rarp.view.main.scnMain;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Main {
	public static void main(String[] args) {
		try {
			MainController.abrir(args);
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Erro ao inicializar a aplicação. "
					+ "\n\tEntre em contato com o grupo RARP telefone: (62)"
					+ " 98526-4619", ButtonType.OK).showAndWait();
			e.printStackTrace();
		}
	}
}
