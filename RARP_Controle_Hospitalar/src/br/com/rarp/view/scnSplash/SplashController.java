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

	private static int progress = 0;


	@FXML
	private ProgressBar pgsSplash;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Splash.fxml"))));
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void next() {
		if (progress < 100)
			progress = progress + (100 / count);
		
	//	initProgress();
		
	}

	@SuppressWarnings("static-access")
	public void abrir(Integer count) throws Exception {
		if (count > 0)
			this.count = count;
		start(new Stage());
		stage.setResizable(false);
		
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	initProgress();

	}

	private void initProgress() {
		
			
		Task task = new Task<Void>() {
		    @Override public Void call() {
		         final int max = 1000000;
		        for (int i=1; i<=max; i++) {
		            if (isCancelled()) {
		               break;
		            }
		            updateProgress(i, max);
		        }
		        return null;
		    }
		};
	
		pgsSplash.progressProperty().bind(task.progressProperty());
		
		new Thread(task).start();

	}
	
}
