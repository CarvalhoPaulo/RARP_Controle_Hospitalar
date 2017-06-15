package br.com.rarp.view.main.scnMain;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnLogin.LoginController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import br.com.rarp.view.scnManutencao.entrada.EntradaPacienteController;
import br.com.rarp.view.scnManutencao.perfilUsuario.PerfilUsuarioController;
import br.com.rarp.view.scnManutencao.usuario.UsuarioController;
import br.com.rarp.view.scnSplash.SplashController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

public class MainController extends Application implements Initializable {
	
	@FXML private ImageView imgMain;
	@FXML private AnchorPane pnContent;
	@FXML private BorderPane pnMain;
	@FXML private CheckMenuItem mniControleAcesso;
	@FXML private ImageView imgControleAcesso;
	@FXML private Label lblRelogio;
    @FXML private Label lblUsuarioSessao;

	private ManutencaoController manutencao;
	
	@Override
	public void start(Stage stage) throws Exception {		
		try {
			SplashController splash = new SplashController();
			splash.abrir(2);	
			SistemaCtrl.getInstance().configuraConexao();
			splash.next();
			SistemaCtrl.getInstance().criarTabelas();
			splash.next();
			splash.getStage().close();
			if(SistemaCtrl.getInstance().getPropriedades().getControleAcesso()) {
				UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
				if(!usuarioCtrl.isEmpty()) {
					LoginController login = new LoginController();
					if(!login.logar())
						System.exit(0);
				}
			}
			
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Main.fxml"))));
			stage.setTitle("RARP Controle Hospitalar - Sistema de controle hospitalar");
			stage.setMaximized(true);

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					SistemaCtrl.getInstance().getPropriedades().setPropriedades();
				}
			});
			stage.show();	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
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
			if(SistemaCtrl.getInstance().getUsuarioSessao() == null) {
				LoginController login = new LoginController();
				try {
					login.logar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mniControleAcesso.setSelected(SistemaCtrl.getInstance().getPropriedades().getControleAcesso());
			lblUsuarioSessao.setText(SistemaCtrl.getInstance().getUsuarioSessao().getNome());
		} else {
			imgControleAcesso.setImage(new Image(getClass().getResource("..\\..\\img\\security-system-desativada-16x16.png").toString()));
			SistemaCtrl.getInstance().setUsuarioSessao(null);
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
		try {
			manutencao = new PerfilUsuarioController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de manutenção de entradas de pacientes");
			e.printStackTrace();
		}		
	}
	
	public void sair() {
		SistemaCtrl.getInstance().getPropriedades().setPropriedades();
		Platform.exit();
        System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initRelogio();
		mniControleAcesso.setSelected(SistemaCtrl.getInstance().getPropriedades().getControleAcesso());
		mniControleAcesso.fire();
	}

	private void initRelogio() {
		Task<Void> relogio = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while(true) {
					lblRelogio.setText(new SimpleDateFormat("dd/MM/yyyy HH:MM:SS").format(Calendar.getInstance()));
				}
			}
		};
		new Thread(relogio).run();
	}

}
