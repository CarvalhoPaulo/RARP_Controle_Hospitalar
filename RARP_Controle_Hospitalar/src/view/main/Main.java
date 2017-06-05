package view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Main.fxml"))));
		stage.setTitle("RARP Controle Hospitalar - Sistema de controle hospitalar");
		stage.setMaximized(true);
		stage.show();
	}
}
