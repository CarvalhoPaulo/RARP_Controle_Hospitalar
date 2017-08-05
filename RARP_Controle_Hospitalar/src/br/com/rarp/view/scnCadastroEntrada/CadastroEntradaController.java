package br.com.rarp.view.scnCadastroEntrada;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroEntradaController extends Application implements Initializable {

	private static Stage stage;
	private static EntradaPacienteCtrl entradaPacienteCtrl;
	private static boolean visualizando;
	
	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("CadastroEntrada.fxml"))));
		stage.setTitle("Cadastro de Entrada de Pacientes");
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getTarget() instanceof Button && event.getCode() == KeyCode.ENTER)
					((Button) event.getTarget()).arm();
				
				if(event.getCode() == KeyCode.ESCAPE)
					voltar(new ActionEvent());
			}
		});
	}
	
	@FXML
	private void voltar(ActionEvent actionEvent) {
		entradaPacienteCtrl = null;
		stage.hide();
		stage = null;
		setVisualizando(false);
	}

	public Stage getStage() {
		return stage;
	}

	@SuppressWarnings("static-access")
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}
	
	@SuppressWarnings("static-access")
	public void alterar(EntradaPacienteCtrl entradaPacienteCtrl) throws Exception {
		this.entradaPacienteCtrl = entradaPacienteCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(entradaPacienteCtrl != null) {
			
		}
		
	}

	public static boolean isVisualizando() {
		return visualizando;
	}

	public static void setVisualizando(boolean visualizando) {
		CadastroEntradaController.visualizando = visualizando;
	}

}
