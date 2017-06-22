package br.com.rarp.view.scnCadastroFuncionario;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroFuncionarioController extends Application implements Initializable {

	private Stage stage;
	@FXML
	private TextField edtCodigo;
	@FXML
	private TextField edtNome;

	private FuncionarioCtrl funcionarioCtrl;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void alterar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		this.funcionarioCtrl = funcionarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (funcionarioCtrl != null)
			preencheTela();
	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void preencheTela() {
		edtCodigo.setText(funcionarioCtrl.getFuncinario().getCodigo() + "");
		edtNome.setText(funcionarioCtrl.getFuncinario().getNome());
	}

}
