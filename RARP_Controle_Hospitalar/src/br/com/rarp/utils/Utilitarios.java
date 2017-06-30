package br.com.rarp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Utilitarios {
	static boolean resultPergunta = false;
	
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
	
	public static boolean pergunta(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		alert.getButtonTypes().addAll(btnSim, btnNao);
		alert.showAndWait().ifPresent(b -> {
			if(b == btnSim) {
				resultPergunta = true;
			} else if(b == btnNao) {
				resultPergunta = false;
			}
		});
		return resultPergunta;
	}

	public static Double strToDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static double strToDouble(String value, double defaultValue) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int strToInt(String val, int valDefault) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return valDefault;
		}
	}

	public static int strToInt(String val) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return 0;
		}
	}

	public static Node getNodeById(Parent parent, String id) {
		if (parent != null)
			for (Node node : parent.getChildrenUnmodifiable()) {
				if (node != null && node.getId() != null && node.getId().equals(id))
					return node;
			}
		return null;
	}
}
