package br.com.rarp.view.main.scnMain;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.control.Enum.TipoMovimentacao;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnLogin.LoginController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import br.com.rarp.view.scnManutencao.entrada.EntradaPacienteController;
import br.com.rarp.view.scnManutencao.espaco.EspacoController;
import br.com.rarp.view.scnManutencao.funcionario.FuncionarioController;
import br.com.rarp.view.scnManutencao.perfilUsuario.PerfilUsuarioController;
import br.com.rarp.view.scnManutencao.usuario.UsuarioController;
import br.com.rarp.view.scnSplash.SplashController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;

public class MainController extends Application implements Initializable {

	@FXML
	private ImageView imgMain;
	@FXML
	private AnchorPane pnContent;
	@FXML
	private BorderPane pnMain;
	@FXML
	private CheckMenuItem mniControleAcesso;
	@FXML
	private ImageView imgControleAcesso;
	@FXML
	private Label lblRelogio;
	@FXML
	private Label lblUsuarioSessao;

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
			if (SistemaCtrl.getInstance().getPropriedades().getControleAcesso()) {
				UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
				if (!usuarioCtrl.isEmpty()) {
					LoginController login = new LoginController();
					if (!login.logar())
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

			try {
				SistemaCtrl.getInstance().getConexao().getConexao();
			} catch (Exception e) {
				try {
					SistemaCtrl.getInstance().getConexao().criarDataBase();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				stage.show();
			}
			SistemaCtrl.getInstance().criarTabelas();
			splash.getStage().close();
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
			SistemaCtrl.getInstance().liberarManutencaoEntradaPaciente(TipoMovimentacao.acesso);
			manutencao = new EntradaPacienteController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void manterEspacos(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEntradaPaciente(TipoMovimentacao.acesso);
			manutencao = new EspacoController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void ativarDesativarControleAcesso() {
		if (mniControleAcesso.isSelected()) {
			imgControleAcesso.setImage(
					new Image(getClass().getResource("..\\..\\img\\security-system-ativada-16x16.png").toString()));
			if (SistemaCtrl.getInstance().getUsuarioSessao() == null) {
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
			imgControleAcesso.setImage(
					new Image(getClass().getResource("..\\..\\img\\security-system-desativada-16x16.png").toString()));
			SistemaCtrl.getInstance().setUsuarioSessao(null);
		}
	}

	public void manterUsuario() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.acesso);
			manutencao = new UsuarioController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void manterPerfilUsuario() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoPerfilUsuario(TipoMovimentacao.acesso);
			manutencao = new PerfilUsuarioController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void trocarUsuario(ActionEvent event) {
		LoginController login = new LoginController();
		try {
			login.logar();
		} catch (Exception e) {
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
		mniControleAcesso.setSelected(SistemaCtrl.getInstance().getPropriedades().getControleAcesso());
		mniControleAcesso.fire();
		initRelogio();
	}

	private void initRelogio() {
		KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHora());
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	private void atualizaHora() {
		lblRelogio.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date().getTime()));
	}

	@FXML
	public void manterFuncionario(ActionEvent event) {
		try {
			manutencao = new FuncionarioController();
			pnMain.setCenter(manutencao.getNode());
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar a tela de manutenção de cadastro de funcionários");
			e.printStackTrace();
		}
	}
}
