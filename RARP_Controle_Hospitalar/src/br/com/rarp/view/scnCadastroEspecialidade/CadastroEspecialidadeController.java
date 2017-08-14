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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroEspecialidadeController extends Application implements Initializable {
	private static boolean visualizando;
	
	private static Stage stage;

	private static EspecialidadeCtrl especialidadeCtrl;

	
    @FXML
    private IntegerTextField edtCodigo;

	@FXML // fx:id="txtCodigo"
	private IntegerTextField txtCodigo; // Value injected by FXMLLoader

    @FXML
    private TextField edtNome;
    
    @FXML 
    private TextArea edtObservacoes;

    @FXML
    private SwitchButton sbStatus;


	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		if (especialidadeCtrl != null && especialidadeCtrl.getEspecialidade() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();

	}

	
	private void preencherTela() {
		edtCodigo.setValue(especialidadeCtrl.getEspecialidade().getCodigo());
		edtNome.setText(especialidadeCtrl.getEspecialidade().getNome());
		edtObservacoes.setText(especialidadeCtrl.getEspecialidade().getObservacoes());
		sbStatus.setValue(especialidadeCtrl.getEspecialidade().isStatus());
	}

	private void bloquearTela() {
		edtNome.setDisable(true);
		edtObservacoes.setDisable(true);
		sbStatus.setDisable(true);
	}

	private void prepararTela() {
		edtCodigo.setDisable(true);
	}


	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEspecialidade.fxml"))));
		primaryStage.setTitle("Cadastro de Especialidades");
		this.stage = primaryStage;
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
		  if (especialidadeCtrl == null ) {
			  especialidadeCtrl = new EspecialidadeCtrl(); 
			  especialidadeCtrl.novaEspecialidade(); 
		  }else {
			  especialidadeCtrl.novaEspecialidade();
		  }
		  
		  especialidadeCtrl.getEspecialidade().setNome(edtNome.getText());
		  especialidadeCtrl.getEspecialidade().setObservacoes(edtObservacoes.getText());
		  especialidadeCtrl.getEspecialidade().setCodigo(edtCodigo.getValue());
	  }
	

	@FXML
	private void salvar(Event event) throws Exception {
		try {
			preencherObjeto();
			if(especialidadeCtrl.salvar()) {
				Utilitarios.message("Cargo salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o cargo.\n" + "Descrição: " + e.getMessage());
		}
	}
	
	private void limparCampos() {
		edtCodigo.clear();
		edtNome.clear();
		edtObservacoes.clear();
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
