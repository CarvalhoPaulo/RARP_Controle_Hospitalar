package br.com.rarp.view.scnCadastroEspecialidade;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EspecialidadeCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CadastroEspecialidadeController extends Application implements Initializable {
	private static boolean visualizando;

	private static Stage stage;

	private static EspecialidadeCtrl especialidadeCtrl;

	@FXML
	private IntegerTextField txtCodigo;

    @FXML
	private TextField txtNome;
    
    @FXML 
    private TextArea txtObservacoes;

    @FXML
    private SwitchButton sbStatus;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnVoltar;

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherTela();
		if (visualizando)
			bloquearTela();
	}

	private void preencherTela() {
		if((especialidadeCtrl != null) && (especialidadeCtrl.getEspecialidade() != null)){
			txtCodigo.setText(Integer.toString(especialidadeCtrl.getEspecialidade().getCodigo()));
			txtNome.setText(especialidadeCtrl.getEspecialidade().getNome());
			txtObservacoes.setText(especialidadeCtrl.getEspecialidade().getObservacoes());
			sbStatus.setValue(especialidadeCtrl.getEspecialidade().isStatus());
		} 
	}

	private void bloquearTela() {
		txtNome.setEditable(false);
		txtObservacoes.setEditable(false);
		sbStatus.setDisable(false);
		btnSalvar.setDisable(true);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEspecialidade.fxml"))));
		primaryStage.setTitle("Cadastro de Especialidades");
		this.stage = primaryStage;
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				 voltar(null); 
			}
		});
		this.stage.setMinWidth(420);
		this.stage.setMinHeight(270);
	}

	public static boolean isVisualizando() {
		return visualizando;
	}

	public static void setVisualizando(boolean visualizando) {
		CadastroEspecialidadeController.visualizando = visualizando;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		CadastroEspecialidadeController.stage = stage;
	}

	@SuppressWarnings("static-access")
	public void alterar(EspecialidadeCtrl especialidadeCtrl) throws Exception {
		this.especialidadeCtrl = especialidadeCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void preencherObjeto() {
		if (especialidadeCtrl == null) {
			especialidadeCtrl = new EspecialidadeCtrl();
			especialidadeCtrl.novaEspecialidade();
		} else {
			especialidadeCtrl.novaEspecialidade();
		}
		especialidadeCtrl.getEspecialidade().setNome(txtNome.getText());
		especialidadeCtrl.getEspecialidade().setObservacoes(txtObservacoes.getText());
		especialidadeCtrl.getEspecialidade().setCodigo(txtCodigo.getValue());
		especialidadeCtrl.getEspecialidade().setStatus(sbStatus.getValue());
	}

	  
	
	@FXML
	private void salvar(Event event) throws Exception {
		try {
			preencherObjeto();
			if (especialidadeCtrl.salvar()) {
				Utilitarios.message("Especialidade salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o Especialidade.\n" + "Descrição: " + e.getMessage());
		}
	}

	private void limparCampos() {
		txtCodigo.clear();
		txtNome.clear();
		txtObservacoes.clear();
	}

	@FXML
	private void voltar(ActionEvent event) {
		especialidadeCtrl = null;
		stage.hide();
		visualizando = false;
	}

	@SuppressWarnings("static-access")
	public void visualizar(EspecialidadeCtrl especialidadeCtrl) throws Exception {
		visualizando = true;
		this.especialidadeCtrl = especialidadeCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

}
