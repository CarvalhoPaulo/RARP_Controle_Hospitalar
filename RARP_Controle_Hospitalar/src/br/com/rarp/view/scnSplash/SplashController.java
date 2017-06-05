package br.com.rarp.view.scnSplash;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class SplashController  extends Application implements Initializable{

	private Stage stage;
	@FXML private ProgressBar pgsSplash;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("Splash.fxml"))));
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ProgressBar getPgsSplash() {
		return pgsSplash;
	}

	public void setPgsSplash(ProgressBar pgsSplash) {
		this.pgsSplash = pgsSplash;
	}
	
	public void abrir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pgsSplash = new ProgressBar(0);
	}

}
