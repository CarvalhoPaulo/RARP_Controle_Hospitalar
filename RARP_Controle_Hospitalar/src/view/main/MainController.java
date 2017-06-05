package view.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	
	@FXML private ImageView imgMain;
	@FXML private AnchorPane pnMain;
	@FXML private Button btnFuncionarios;
	@FXML private Button btnPacientes;
	@FXML private Button btnMedicos;
	@FXML private Button btnLeitos;
	@FXML private Button btnEncaminhar;
	@FXML private Button btnAgendar;
	@FXML private Button btnAjuda;
	@FXML private Button btnSair;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imgMain.setPreserveRatio(true);
		imgMain.setFitWidth(pnMain.getWidth());
		imgMain.setFitHeight(pnMain.getHeight());
		
		Image imgFuncionarios = new Image("view/img/funcionarios.png");
		btnFuncionarios.setGraphic(new ImageView(imgFuncionarios));
		btnFuncionarios.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgPacientes= new Image("view/img/pacientes.png");
		btnPacientes.setGraphic(new ImageView(imgPacientes));
		btnPacientes.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgMedicos = new Image("view/img/medico.png");
		btnMedicos.setGraphic(new ImageView(imgMedicos));
		btnMedicos.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgLeitos = new Image("view/img/leitos.png");
		btnLeitos.setGraphic(new ImageView(imgLeitos));
		btnLeitos.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgEncaminhar = new Image("view/img/encaminhar.png");
		btnEncaminhar.setGraphic(new ImageView(imgEncaminhar));
		btnEncaminhar.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgAgendar = new Image("view/img/agendar.png");
		btnAgendar.setGraphic(new ImageView(imgAgendar));
		btnAgendar.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgAjuda = new Image("view/img/Ajuda.png");
		btnAjuda.setGraphic(new ImageView(imgAjuda));
		btnAjuda.setAlignment(Pos.BOTTOM_CENTER);
		
		Image imgSair = new Image("view/img/sair.png");
		btnSair.setGraphic(new ImageView(imgSair));
		btnSair.setAlignment(Pos.BOTTOM_CENTER);
	}

}
