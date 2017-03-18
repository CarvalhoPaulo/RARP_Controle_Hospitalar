package view.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	
	@FXML private ImageView imgMain;
	@FXML private AnchorPane pnMain;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imgMain.setPreserveRatio(true);
		imgMain.setFitWidth(pnMain.getWidth());
		imgMain.setFitHeight(pnMain.getHeight());
	}

}
