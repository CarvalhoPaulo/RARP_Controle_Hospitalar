package br.com.rarp.view.main.scnMain;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.main.scnManutencao.ManutencaoController;
import br.com.rarp.view.main.scnManutencao.Entrada.EntradaPacienteController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class MainController extends Application implements Initializable {
	
	@FXML private ImageView imgMain;
	@FXML private AnchorPane pnContent;
	@FXML private BorderPane pnMain;

	private ManutencaoController manutencao;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Main.fxml"))));
		stage.setTitle("RARP Controle Hospitalar - Sistema de controle hospitalar");
		stage.setMaximized(true);
		stage.show();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		imgMain.setPreserveRatio(true);
		imgMain.setFitWidth(pnContent.getWidth());
		imgMain.setFitHeight(pnContent.getHeight());
	}
	
	public static void abrir(String[] args) {
		launch(args);
	}
	
	public void darEntrada() {
		try {
			manutencao = new EntradaPacienteController();
			pnMain.setCenter(manutencao.getLoader().load());
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de manutenção de entradas de pacientes");
			e.printStackTrace();
		}
	}
	
	public void sair() {
		Platform.exit();
        System.exit(0);
	}

}
