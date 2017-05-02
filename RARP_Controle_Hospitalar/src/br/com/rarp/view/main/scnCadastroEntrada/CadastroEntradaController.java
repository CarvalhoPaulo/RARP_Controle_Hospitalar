package br.com.rarp.view.main.scnCadastroEntrada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroEntradaController extends Application {

	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("CadastroEntrada.fxml"))));
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public static void abrir() throws Exception {
		CadastroEntradaController cadastroEntrada = new CadastroEntradaController();
		cadastroEntrada.start(new Stage());
		cadastroEntrada.getStage().showAndWait();
	}

}
