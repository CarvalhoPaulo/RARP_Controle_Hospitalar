package br.com.rarp.view.main.scnMain;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import br.com.rarp.view.scnManutencao.Entrada.EntradaPacienteController;
import br.com.rarp.view.scnManutencao.usuario.UsuarioController;
import br.com.rarp.view.scnSplash.SplashController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController extends Application implements Initializable {
	
	@FXML private ImageView imgMain;
	@FXML private AnchorPane pnContent;
	@FXML private BorderPane pnMain;
	@FXML private CheckMenuItem mniControleAcesso;
	@FXML private ImageView imgControleAcesso;
	@FXML private Label lblRelogio;

	private ManutencaoController manutencao;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Main.fxml"))));
		stage.setTitle("RARP Controle Hospitalar - Sistema de controle hospitalar");
		stage.setMaximized(true);
		SplashController splash = new SplashController();
		splash.abrir();
		
		//Conecta ao banco de dados caso existe senão cria um novo
		try {
			SistemaCtrl.getInstance().getConexao().getConexao();
		} catch (SQLException e) {
			try {
				SistemaCtrl.getInstance().getConexao().criarDataBase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Cria as tabelas do banco de dados se elas não existirem
		SistemaCtrl.getInstance().criarTabelas();
		
		splash.getStage().close();
		stage.show();		
	}
	
	public static void abrir(String[] args) {
		launch(args);
	}
	
	public void manterEntrada() {
		try {
			manutencao = new EntradaPacienteController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de manutenção de entradas de pacientes");
			e.printStackTrace();
		}
	}
	
	public void ativarDesativarControleAcesso() {
		if(mniControleAcesso.isSelected()) {
			imgControleAcesso.setImage(new Image(getClass().getResource("..\\..\\img\\security-system-ativada-16x16.png").toString()));
			SistemaCtrl.getInstance().getPropriedades().setControleAcesso(true);
		} else {
			imgControleAcesso.setImage(new Image(getClass().getResource("..\\..\\img\\security-system-desativada-16x16.png").toString()));
			SistemaCtrl.getInstance().getPropriedades().setControleAcesso(false);
		}
	}
	
	public void manterUsuario() {
		try {
			manutencao = new UsuarioController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de manutenção de entradas de pacientes");
			e.printStackTrace();
		}
	}
	
	public void manterPerfilUsuario() {
		Utilitarios.atencao("Manutenção de Perfil de Usuário");
	}
	
	public void sair() {
		Platform.exit();
        System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
