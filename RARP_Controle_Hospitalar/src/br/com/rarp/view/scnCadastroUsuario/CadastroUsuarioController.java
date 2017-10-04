package br.com.rarp.view.scnCadastroUsuario;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.PerfilUsuarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.enums.TipoCampo;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroUsuarioController extends Application implements Initializable {

	private static Stage stage;
	
    @FXML private Button btnSalvar;
    @FXML private Button btnVoltar;
    @FXML private IntegerTextField txtCodigo;
    @FXML private TextField txtNome;
    @FXML private TextField txtUsuario;
    @FXML private ComboBox<PerfilUsuario> cmbPerfilUsuario;
    @FXML private ComboBox<Funcionario> cmbFuncionario;
    @FXML private SwitchButton sbAtivado;
    
	private static UsuarioCtrl usuarioCtrl;
	private static boolean visualizando;

	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroUsuario.fxml"))));
		stage.setTitle("Cadastro de Usuários");
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getTarget() instanceof Button && event.getCode() == KeyCode.ENTER)
					((Button) event.getTarget()).arm();
				
				if(event.getCode() == KeyCode.ESCAPE)
					voltar(new ActionEvent());
			}
		});
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		CadastroUsuarioController.stage = stage;
	}
	
	private void limparCampos() {
		txtCodigo.clear();
		txtNome.clear();
		txtUsuario.clear();
		
		cmbFuncionario.getSelectionModel().select(-1);
		cmbPerfilUsuario.getSelectionModel().select(-1);
		
		sbAtivado.switchOnProperty().set(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.switchOnProperty().set(true);
		txtCodigo.setDisable(true);
		try {
			cmbPerfilUsuario.setItems(new PerfilUsuarioCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "ativado"));
			cmbFuncionario.setItems(new FuncionarioCtrl().consultar(new Campo("func.status", "", TipoCampo.booleano), new Ativado(), "ativado"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(usuarioCtrl != null && usuarioCtrl.getClass() != null)
			preencheTela();
		if(visualizando)
			bloquearTela();
	}
	
	private void bloquearTela() {
		txtCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtUsuario.setDisable(true);
		cmbFuncionario.setDisable(true);
		cmbPerfilUsuario.setDisable(true);
		sbAtivado.setDisable(true);
		btnSalvar.setDisable(true);
	}

	public void inserir() throws Exception {
		usuarioCtrl = null;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();		
	}
	
	@SuppressWarnings("static-access")
	public void alterar(UsuarioCtrl usuarioCtrl) throws Exception {
		this.usuarioCtrl = usuarioCtrl;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}
	
	@SuppressWarnings("static-access")
	public void visualizar(UsuarioCtrl usuarioCtrl) throws Exception {
		visualizando = true;
		this.usuarioCtrl = usuarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}
	
    @FXML
    private void salvar(ActionEvent event) {
    	preencherObjeto();
		try {
			if(usuarioCtrl.salvar()) {
				Utilitarios.message("Usuário salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
						   + "Descrição: " + e.getMessage());
		}
    }
    
	@FXML
    private void voltar(ActionEvent event) {
		usuarioCtrl = null;
    	stage.hide();
    	visualizando = false;
    }

    private void preencherObjeto() {
		if(usuarioCtrl == null)
			usuarioCtrl = new UsuarioCtrl();
		
		if(usuarioCtrl.getUsuario() == null)
			usuarioCtrl.novoUsuario();
		
		usuarioCtrl.getUsuario().setCodigo(Utilitarios.strToInt(txtCodigo.getText()));
		usuarioCtrl.getUsuario().setNome(txtNome.getText());
		usuarioCtrl.getUsuario().setUsuario(txtUsuario.getText());
		usuarioCtrl.getUsuario().setPerfilUsuario(cmbPerfilUsuario.getValue());
		usuarioCtrl.getUsuario().setFuncionario(cmbFuncionario.getValue());
		usuarioCtrl.getUsuario().setStatus(sbAtivado.switchOnProperty().get());
	}
    
	private void preencheTela() {
		txtCodigo.setText(usuarioCtrl.getUsuario().getCodigo() + "");
		txtNome.setText(usuarioCtrl.getUsuario().getNome());
		txtUsuario.setText(usuarioCtrl.getUsuario().getUsuario());
		cmbFuncionario.setValue(usuarioCtrl.getUsuario().getFuncionario());
		cmbPerfilUsuario.setValue(usuarioCtrl.getUsuario().getPerfilUsuario());
		sbAtivado.switchOnProperty().set(usuarioCtrl.getUsuario().isStatus());
	}

}