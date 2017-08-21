package br.com.rarp.view.scnCadastroMedico;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.MedicoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Estado;
import br.com.rarp.model.Funcionario;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CadastroMedicoController extends Application implements Initializable {
	private static boolean visualizando;

	private static Stage stage;

	private static MedicoCtrl medicoCtrl;

	@FXML // fx:id="txtCodig"
	private IntegerTextField txtCodigo; // Value injected by FXMLLoader

	@FXML // fx:id="cmbFuncionario"
	private ComboBox<Funcionario> cmbFuncionario; // Value injected by FXMLLoader

	@FXML // fx:id="cmbEspecialidades"
	private ComboBox<Especialidade> cmbEspecialidades; // Value injected by FXMLLoader

	@FXML // fx:id="tbvEspecialidades"
	private TableView<Especialidade> tbvEspecialidades; // Value injected by FXMLLoader

	@FXML // fx:id="tbcNome"
	private TableColumn<Especialidade, Especialidade> tbcNome; // Value injected by FXMLLoader

	@FXML // fx:id="cmbEstado"
	private ComboBox<Estado> cmbEstado; // Value injected by FXMLLoader

	@FXML // fx:id="txtCRM"
	private IntegerTextField txtCRM;

	@FXML 
	private SwitchButton swStatus;
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
		
		
		
		if ((medicoCtrl != null) && (medicoCtrl.getMedico() != null)) {
			cmbFuncionario.getSelectionModel().select(medicoCtrl.getMedico());
			cmbEspecialidades.getSelectionModel().select(-1);
			tbvEspecialidades.getItems().clear();
			cmbEstado.getSelectionModel().select(-1);
			txtCRM.clear();
			;
		}
	}

	private void bloquearTela() {
		cmbFuncionario.setEditable(false);
		cmbEspecialidades.setEditable(false);
		tbvEspecialidades.setEditable(false);
		cmbEstado.setEditable(false);
		txtCRM.setEditable(false);
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroMedico.fxml"))));
		primaryStage.setTitle("Cadastro de Especialidades");
		this.stage = primaryStage;
	}

	public static boolean isVisualizando() {
		return visualizando;
	}

	public static void setVisualizando(boolean visualizando) {
		CadastroMedicoController.visualizando = visualizando;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		CadastroMedicoController.stage = stage;
	}

	@SuppressWarnings("static-access")
	public void alterar(MedicoCtrl medicoCtrl) throws Exception {
		this.medicoCtrl = medicoCtrl;

		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void preencherObjeto() {
		if (medicoCtrl == null) {
			medicoCtrl = new MedicoCtrl();
			medicoCtrl.novoMedico();
		} else {
			medicoCtrl.novoMedico();
		}

	}

	@FXML
	private void salvar(Event event) throws Exception {
		try {
			if (!visualizando) {
				preencherObjeto();
				if (medicoCtrl.salvar()) {
					Utilitarios.message("Especialidade salvo com sucesso.");
					limparCampos();
				}
			} else {
				Utilitarios.atencao("Este cadastro esta aberto apenas para visualização");
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o Especialidade.\n" + "Descrição: " + e.getMessage());
		}
	}

	private void limparCampos() {
		// txtCodigo.clear();

	}

	@FXML
	private void voltar(ActionEvent event) {
		medicoCtrl = null;
		stage.hide();
		visualizando = false;
	}

	@SuppressWarnings("static-access")
	public void visualizar(MedicoCtrl medicoCtrl) throws Exception {
		visualizando = true;
		this.medicoCtrl = medicoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

}
