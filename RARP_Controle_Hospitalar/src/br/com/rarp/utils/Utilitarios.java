package br.com.rarp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utilitarios {
	public static void atencao(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(message);
		alert.setHeaderText("Atenção");
		alert.showAndWait();
	}
	
	public static void erro(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.setHeaderText("Erro");
		alert.showAndWait();
	}
	
	public static void message(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.setHeaderText("Sucesso");
		alert.showAndWait();
	}
	
	public static String dateToStr(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
}
