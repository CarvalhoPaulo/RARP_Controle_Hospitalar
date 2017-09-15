package br.com.rarp.view.scnCadastroEntrada;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.MedicoCtrl;
import br.com.rarp.control.PacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.Funcao;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.Medico;
import br.com.rarp.model.Paciente;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTimeTextField;

public class CadastroEntradaController extends Application implements Initializable {

	private static Stage stage;
	private static EntradaPacienteCtrl entradaPacienteCtrl;
	private static boolean visualizando;
	
    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private IntegerTextField txtCodigo;

    @FXML
    private SwitchButton sbAtivado;

    @FXML
    private AutoCompleteComboBox<Paciente> cmbPaciente;

    @FXML
    private AutoCompleteComboBox<Medico> cmbMedico;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbEnfermeira;

    @FXML
    private TextArea txtPreTriagem;

    @FXML
    private TableView<?> tblAtendimentos;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRemover;

    @FXML
    private DatePicker txtDate;

    @FXML
    private CalendarTimeTextField txtHora;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbAtendente;

    @FXML
    private SwitchButton sbAlta;

    @FXML
    void alterarAtendimento(ActionEvent event) {

    }

    @FXML
    void inserirAtendimento(ActionEvent event) {

    }

    @FXML
    void removerAtendimento(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {

    }
	
	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene((Parent) FXMLLoader.load(getClass().getResource("CadastroEntrada.fxml"))));
		stage.setTitle("Cadastro de Entrada de Pacientes");
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
	
	@FXML
	private void voltar(ActionEvent actionEvent) {
		entradaPacienteCtrl = null;
		stage.hide();
		stage = null;
		setVisualizando(false);
	}

	public Stage getStage() {
		return stage;
	}

	@SuppressWarnings("static-access")
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}
	
	@SuppressWarnings("static-access")
	public void alterar(EntradaPacienteCtrl entradaPacienteCtrl) throws Exception {
		this.entradaPacienteCtrl = entradaPacienteCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		if (entradaPacienteCtrl != null && entradaPacienteCtrl.getEntradaPaciente() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
	}

	private void bloquearTela() {
		// TODO Auto-generated method stub
		
	}

	private void preencherTela() {
		// TODO Auto-generated method stub
		
	}

	private void prepararTela() {
		try {
			cmbAtendente.setItems(new FuncionarioCtrl().getFuncionarios(Funcao.atendente));
			cmbEnfermeira.setItems(new FuncionarioCtrl().getFuncionarios(Funcao.enfermeira));
			cmbMedico.setItems(new MedicoCtrl().getMedicos());
			cmbPaciente.setItems(new PacienteCtrl().getPacientes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isVisualizando() {
		return visualizando;
	}

	public static void setVisualizando(boolean visualizando) {
		CadastroEntradaController.visualizando = visualizando;
	}

	@SuppressWarnings("static-access")
	public void visualizar(EntradaPacienteCtrl entradaPacienteCtrl) throws Exception {
		visualizando = true;
		this.entradaPacienteCtrl = entradaPacienteCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

}
