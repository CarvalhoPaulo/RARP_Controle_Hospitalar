package br.com.rarp.view.scnCadastroEntrada;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroEntradaController extends Application implements Initializable {

	private Stage stage;
	private EntradaPacienteCtrl entradaPacienteCtrl;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("CadastroEntrada.fxml"))));
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}
	
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

}
