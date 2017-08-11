package br.com.rarp.view.scnConexao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnAcesso.AcessoController;
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

	private boolean naoCarregaStange;

	public boolean isNaoCarregaStange() {
		return naoCarregaStange;
	}

	public void setNaoCarregaStange(boolean naoCarregaStange) {
		this.naoCarregaStange = naoCarregaStange;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		setNode(FXMLLoader.load(getClass().getResource("Conexao.fxml")));

		if (!naoCarregaStange) {
			primaryStage.setScene(new Scene((Parent) getNode()));
			setStage(primaryStage);
		}else {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Conexao.fxml"));
	  //      loader.setController(this);
			setNode(loader.load());
		}

	}
	
	public void AbrirPorAcesso() throws Exception {
		try {
			start(SistemaCtrl.getInstance().getStage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao carregar painel de configuraçao de servidor " +  e.getMessage());
		}
	}

	public void Configurar() throws Exception {
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
			Utilitarios.message("Configurações de Servidor Gravandas Com Sucesso");
		} catch (Exception e) {
			// TODO: handle exception

			Utilitarios.atencao("Falha ao Gravar Configurações de Servidor");
		}

	}

	@FXML
	void btnCancelarAction(ActionEvent event) {

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
