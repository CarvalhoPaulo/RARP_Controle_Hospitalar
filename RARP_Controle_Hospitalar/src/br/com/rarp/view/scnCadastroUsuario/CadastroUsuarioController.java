package br.com.rarp.view.scnCadastroUsuario;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.midi.ControllerEventListener;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.view.scnCadastroEntrada.CadastroEntradaController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadastroUsuarioController extends Application implements Initializable {

	private Stage stage;
	@FXML private TextField edtCodigo;
	@FXML private TextField edtNome;
	@FXML private TextField edtUsuario;
	@FXML private ComboBox<PerfilUsuario> cmbPerfilUsuario;
	@FXML private ComboBox<Funcionario> cmbfuncionario;
	
	private UsuarioCtrl usuarioCtrl;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroUsuario.fxml"))));
		stage.setTitle("Cadastro de Usuários");
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	

	public void alterar(UsuarioCtrl usuarioCtrl) throws Exception {
		this.usuarioCtrl = usuarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(usuarioCtrl != null)
			preencheTela();
	}
	
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();		
	}

	private void preencheTela() {
		edtCodigo.setText(usuarioCtrl.getUsuario().getCodigo() + "");
		edtNome.setText(usuarioCtrl.getUsuario().getNome());
		edtUsuario.setText(usuarioCtrl.getUsuario().getUsuario());
		cmbfuncionario.setValue(usuarioCtrl.getUsuario().getFuncionario());
		cmbPerfilUsuario.setValue(usuarioCtrl.getUsuario().getPerfilUsuario());
	}

}
