package br.com.rarp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.postgresql.core.SqlCommand;

import javafx.scene.Node;
import javafx.scene.Parent;
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
	
	public static String formatStringSQL(String sql) {
		sql = sql.replaceAll("\"", "");
		sql = sql.replaceAll("'", "");
		return sql;
	}
	
	public static Node getNodeById(Parent parent, String id) {
		if(parent != null)
			for(Node node : parent.getChildrenUnmodifiable()) {
				if (node != null && node.getId() != null && node.getId().equals(id))
					return node;
			}
		return null;
	}
}
