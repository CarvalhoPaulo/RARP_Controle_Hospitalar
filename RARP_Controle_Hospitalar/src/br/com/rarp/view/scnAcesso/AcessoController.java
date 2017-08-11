package br.com.rarp.view.scnAcesso;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.view.scnConexao.ConexaoController;
import br.com.rarp.view.scnLogin.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AcessoController extends Application implements Initializable{

    @FXML
    private  BorderPane login;

    @FXML
    private  BorderPane Serve;
    
    private static Stage stage;
    
    
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		AcessoController.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			carregarPaneis();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AcessoController.fxml"))));
		setStage(primaryStage);
		
	}
	
	private void carregarPaneis() throws Exception {
		LoginController loginController  = new LoginController();
		loginController.abrirPorAcesso(false);
		login.setCenter(loginController.getNode());

		ConexaoController conexaoController = new ConexaoController();
		conexaoController.abrirPorAcesso(false);
		Serve.setCenter(conexaoController.getNode());
	}

	public boolean logar() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
		LoginController.setStage(stage);
		return SistemaCtrl.getInstance().getUsuarioSessao() != null;
	}

}
