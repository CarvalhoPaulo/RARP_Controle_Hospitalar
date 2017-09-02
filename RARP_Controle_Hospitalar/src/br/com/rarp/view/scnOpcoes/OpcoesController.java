package br.com.rarp.view.scnOpcoes;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpcoesController extends Application implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Opcoes.fxml"))));
		//stage.setResizable(false);
		stage.showAndWait();
	}

}
