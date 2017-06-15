package br.com.rarp.view.scnSplash;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class SplashController extends Application implements Initializable {

	private Stage stage;
	private static Integer count = 1;
	private static double progress = 0.0;

	@FXML
	private ProgressBar pgsSplash;

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

	public void next() {
		if (progress < 1.0)
			progress = progress + (1.0 / count);
	}

	@SuppressWarnings("static-access")
	public void abrir(Integer count) throws Exception {
		if (count > 0)
			this.count = count;
		start(SistemaCtrl.getInstance().getStage());
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initProgress();
	}

	private void initProgress() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				while (progress < 1.0) {
					updateProgress(progress, 1);
				}
				return null;
			}
		};
		pgsSplash.progressProperty().bind(task.progressProperty());
		new Thread(task).start();
	}

}
