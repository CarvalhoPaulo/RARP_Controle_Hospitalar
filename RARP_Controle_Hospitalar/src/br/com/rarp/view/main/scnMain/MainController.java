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
import br.com.rarp.view.scnAcesso.AcessoController;
import br.com.rarp.view.scnLogin.LoginController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import br.com.rarp.view.scnManutencao.cargo.CargoController;
import br.com.rarp.view.scnManutencao.cidade.CidadeController;
import br.com.rarp.view.scnManutencao.entrada.EntradaPacienteController;
import br.com.rarp.view.scnManutencao.espaco.EspacoController;
import br.com.rarp.view.scnManutencao.especialidade.EspecialidadeController;
import br.com.rarp.view.scnManutencao.funcionario.FuncionarioController;
import br.com.rarp.view.scnManutencao.paciente.PacienteController;
import br.com.rarp.view.scnManutencao.perfilUsuario.PerfilUsuarioController;
import br.com.rarp.view.scnManutencao.usuario.UsuarioController;
import br.com.rarp.view.scnSplash.SplashController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainController extends Application implements Initializable {

    @FXML
    private BorderPane pnMain;

    @FXML
    private MenuBar mnbMenu;

    @FXML
    private Menu mnArquivo;

    @FXML
    private MenuItem mniOpcoes;

    @FXML
    private MenuItem mniSair;

    @FXML
    private Menu mnCadastros;

    @FXML
    private MenuItem mniFuncionarios;

    @FXML
    private MenuItem mniPacientes;

    @FXML
    private MenuItem mniMedicos;

    @FXML
    private MenuItem mniLeitos;

    @FXML
    private MenuItem mniCidades;

    @FXML
    private MenuItem mniEspecialidade;

    @FXML
    private Menu mnMovimentacoes;

    @FXML
    private Menu mnRelatorios;

    @FXML
    private Menu mnSeguranca;

    @FXML
    private CheckMenuItem mniControleAcesso;

    @FXML
    private MenuItem mniUsuario;

    @FXML
    private MenuItem mniPerfilUsuario;

    @FXML
    private MenuItem mniTrocarUsuario;

    @FXML
    private Menu mnSobre;

    @FXML
    private ToolBar tlbBarraFerramentas;

    @FXML
    private Button btnFuncionarios;

    @FXML
    private Button btnPacientes;

    @FXML
    private Button btnMedicos;

    @FXML
    private Button btnLeitos;

    @FXML
    private Button btnEntrada;

    @FXML
    private Button btnSaida;

    @FXML
    private Button btnAjuda;

    @FXML
    private Button btnSair;

    @FXML
    private ImageView imgControleAcesso;

    @FXML
    private Label lblUsuarioSessao;

    @FXML
    private Label lblRelogio;

    @FXML
    private AnchorPane pnContent;

    @FXML
    private ImageView imgMain;
    
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
					
					AcessoController acessoController = new AcessoController();
					if (!acessoController.logar())
						System.exit(0);
				
				} else {
					SistemaCtrl.getInstance().getPropriedades().setControleAcesso(false);
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
			stage.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getTarget() instanceof Button && event.getCharacter().equals("\r"))
						((Button) event.getTarget()).fire();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void abrir(String[] args) {
		launch(args);
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
		
		pnContent.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				imgMain.setFitHeight(pnContent.getHeight());
			}
		});
		
		pnContent.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				imgMain.setFitWidth(pnContent.getWidth());
			}
		});
		
		pnMain.centerProperty().addListener(new ChangeListener<Node>() {

			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				if(newValue == null) {
					pnMain.setCenter(imgMain);
					focarToolBar(true);
					tlbBarraFerramentas.requestFocus();
					btnFuncionarios.requestFocus();
				}
			}
		});
	}
	
	private void focarToolBar(boolean value) {
		btnAjuda.setFocusTraversable(value);
		btnEntrada.setFocusTraversable(value);
		btnFuncionarios.setFocusTraversable(value);
		btnLeitos.setFocusTraversable(value);
		btnMedicos.setFocusTraversable(value);
		btnPacientes.setFocusTraversable(value);
		btnSaida.setFocusTraversable(value);
		btnSair.setFocusTraversable(value);
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
	private void manterUsuario(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.acesso);
			manutencao = new UsuarioController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void manterPerfilUsuario() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoPerfilUsuario(TipoMovimentacao.acesso);
			manutencao = new PerfilUsuarioController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
	
    @FXML
    private void manterCargos(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.acesso);
			manutencao = new CargoController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
	private void manterEntrada(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEntradaPaciente(TipoMovimentacao.acesso);
			manutencao = new EntradaPacienteController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void manterEspacos(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEspaco(TipoMovimentacao.acesso);
			manutencao = new EspacoController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

    @FXML
    private void manterMedicos(ActionEvent event) {

    }

    @FXML
    private void manterPacientes(ActionEvent event) {
    	try {
			SistemaCtrl.getInstance().liberarManutencaoPaciente(TipoMovimentacao.acesso);
			manutencao = new PacienteController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
    }

	@FXML
	private void manterFuncionario(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoFuncionario(TipoMovimentacao.acesso);
			manutencao = new FuncionarioController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@FXML
	private void manterEspecialidade(ActionEvent event){
		try {
			manutencao = new EspecialidadeController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
	
    @FXML
    private void manterCidades(ActionEvent event) {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCidade(TipoMovimentacao.acesso);
			manutencao = new CidadeController();
			pnMain.setCenter(manutencao.getNode());
			focarToolBar(false);
			manutencao.getNode().requestFocus();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
    }
}
