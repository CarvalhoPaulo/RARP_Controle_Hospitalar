package br.com.rarp.view.scnCadastroEspecialidade;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroEspecialidadeController extends Application implements Initializable{
	private static boolean visualizando;
	
	private static Stage stage;
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
		this.stage = stage;
	}
	public static boolean isVisualizando() {
		return visualizando;
	}
	public static void setVisualizando(boolean visualizando) {
		CadastroEspecialidadeController.visualizando = visualizando;
	}
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		CadastroEspecialidadeController.stage = stage;
	}

}
