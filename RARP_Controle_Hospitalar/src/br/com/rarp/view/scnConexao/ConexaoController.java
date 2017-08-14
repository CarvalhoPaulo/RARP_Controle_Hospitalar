package br.com.rarp.view.scnConexao;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConexaoController extends Application implements Initializable {
	@FXML // fx:id="txtHost"
	private TextField txtHost; // Value injected by FXMLLoader

	@FXML // fx:id="txtPorta"
	private TextField txtPorta; // Value injected by FXMLLoader

	@FXML // fx:id="txtUser"
	private TextField txtUser; // Value injected by FXMLLoader

	@FXML // fx:id="txtSenha"
	private PasswordField txtSenha; // Value injected by FXMLLoader

	@FXML // fx:id="btnAplicar"
	private Button btnAplicar; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancelar"
	private Button btnCancelar; // Value injected by FXMLLoader

	private Stage stage;

	private Node node;

	private boolean carregaStage = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtHost.setText(SistemaCtrl.getInstance().getPropriedades().getHost());
		txtPorta.setText(SistemaCtrl.getInstance().getPropriedades().getPorta());
		txtUser.setText(SistemaCtrl.getInstance().getPropriedades().getUser());
		txtSenha.setText(SistemaCtrl.getInstance().getPropriedades().getPassword());
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setNode(FXMLLoader.load(getClass().getResource("Conexao.fxml")));
		if (carregaStage) {
			primaryStage.setScene(new Scene((Parent) getNode()));
			setStage(primaryStage);
		}else {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Conexao.fxml"));
			setNode(loader.load());
		}
	}
	
	public void abrirPorAcesso(boolean carregaStage) throws Exception {
		try {
			this.carregaStage = carregaStage;
			start(SistemaCtrl.getInstance().getStage());	
		} catch (Exception e) {
			throw new Exception("Erro ao carregar painel de configuraçao de servidor " +  e.getMessage());
		}
	}

	public void configurar() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@FXML
	void btnAplicarAction(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().getPropriedades().setHost(txtHost.getText());
			SistemaCtrl.getInstance().getPropriedades().setPorta(txtPorta.getText());
			SistemaCtrl.getInstance().getPropriedades().setUser(txtUser.getText());
			SistemaCtrl.getInstance().getPropriedades().setPassword(txtSenha.getText());

			SistemaCtrl.getInstance().getPropriedades().setPropriedades();
			Utilitarios.message("Configurações do servidor de banco de dados gravadas com sucesso.");
			
			if(stage != null)
				stage.hide();
		} catch (Exception e) {
			Utilitarios.atencao("Falha ao fravar configurações do servidor de banco de dados.");
		}
	}

	@FXML
	void btnCancelarAction(ActionEvent event) {
		stage.hide();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
