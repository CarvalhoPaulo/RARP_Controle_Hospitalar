package br.com.rarp.view.scnCadastroUsuario;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.PerfilUsuarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroUsuarioController extends Application implements Initializable {

	private static Stage stage;
	
    @FXML private Button btnGravar;
    @FXML private Button btnVoltar;
    @FXML private TextField edtCodigo;
    @FXML private TextField edtNome;
    @FXML private TextField edtUsuario;
    @FXML private ComboBox<PerfilUsuario> cmbPerfilUsuario;
    @FXML private ComboBox<Funcionario> cmbFuncionario;
    @FXML private SwitchButton sbAtivado;
    
	private static UsuarioCtrl usuarioCtrl;
	private static boolean visualizando;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroUsuario.fxml"))));
		stage.setTitle("Cadastro de Usuários");
		setStage(stage);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		CadastroUsuarioController.stage = stage;
	}
	
	@SuppressWarnings("static-access")
	public void alterar(UsuarioCtrl usuarioCtrl) throws Exception {
		this.usuarioCtrl = usuarioCtrl;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.switchOnProperty().set(true);
		edtCodigo.setDisable(true);
		PerfilUsuarioCtrl perfilUsuarioCtrl = new PerfilUsuarioCtrl();
		try {
			cmbPerfilUsuario.setItems(perfilUsuarioCtrl.consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "ativado"));
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
		edtCodigo.setDisable(true);
		edtNome.setDisable(true);
		edtUsuario.setDisable(true);
		cmbFuncionario.setDisable(true);
		cmbPerfilUsuario.setDisable(true);
		sbAtivado.setDisable(true);
		btnGravar.setDisable(true);
	}

	public void inserir() throws Exception {
		usuarioCtrl = null;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();		
	}

	private void preencheTela() {
		edtCodigo.setText(usuarioCtrl.getUsuario().getCodigo() + "");
		edtNome.setText(usuarioCtrl.getUsuario().getNome());
		edtUsuario.setText(usuarioCtrl.getUsuario().getUsuario());
		cmbFuncionario.setValue(usuarioCtrl.getUsuario().getFuncionario());
		cmbPerfilUsuario.setValue(usuarioCtrl.getUsuario().getPerfilUsuario());
		sbAtivado.switchOnProperty().set(usuarioCtrl.getUsuario().isStatus());
	}
	
    @FXML
    private void gravar(ActionEvent event) {
    	preencherObjeto();
		try {
			usuarioCtrl.salvar();
			Utilitarios.message("Usuário salvo com sucesso.");
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
						   + "Descrição: " + e.getMessage());
		}
    }

    private void preencherObjeto() {
		if(usuarioCtrl == null)
			usuarioCtrl = new UsuarioCtrl();
		
		if(usuarioCtrl.getUsuario() == null)
			usuarioCtrl.novoUsuario();
		
		usuarioCtrl.getUsuario().setCodigo(Utilitarios.strToInt(edtCodigo.getText()));
		usuarioCtrl.getUsuario().setNome(edtNome.getText());
		usuarioCtrl.getUsuario().setUsuario(edtUsuario.getText());
		usuarioCtrl.getUsuario().setPerfilUsuario(cmbPerfilUsuario.getValue());
		usuarioCtrl.getUsuario().setFuncionario(cmbFuncionario.getValue());
		usuarioCtrl.getUsuario().setStatus(sbAtivado.switchOnProperty().get());
	}

	@FXML
    private void voltar(ActionEvent event) {
    	stage.hide();
    }

	@SuppressWarnings("static-access")
	public void visualizar(UsuarioCtrl usuarioCtrl) throws Exception {
		visualizando = true;
		this.usuarioCtrl = usuarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

}
