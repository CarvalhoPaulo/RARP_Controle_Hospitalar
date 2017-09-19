package br.com.rarp.view.scnCadastroEntrada;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.MedicoCtrl;
import br.com.rarp.control.PacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.Funcao;
import br.com.rarp.model.Atendimento;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.Medico;
import br.com.rarp.model.Paciente;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private SwitchButton sbEmergencia;

    @FXML
    private AutoCompleteComboBox<Paciente> cmbPaciente;

    @FXML
    private AutoCompleteComboBox<Medico> cmbMedico;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbEnfermeira;

    @FXML
    private TextArea txtPreTriagem;

    @FXML
    private TableView<Atendimento> tblAtendimentos;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRemover;

    @FXML
    private DatePicker txtData;

    @FXML
    private CalendarTimeTextField txtHora;

    @FXML
    private AutoCompleteComboBox<Funcionario> cmbAtendente;

    @FXML
    private SwitchButton sbAlta;
    
    @FXML
    private Label lblMedico;
    
    @FXML
    private Label lblPreTriagem;
    
    @FXML
    private Label lblAtendimentos;
    
    @FXML
    private TableColumn<Atendimento, String> cmnResponsavel;

    @FXML
    private TableColumn<Atendimento, String> cmnData;

    @FXML
    private TableColumn<Atendimento, String> cmnHorarioInicial;

    @FXML
    private TableColumn<Atendimento, String> cmnHorarioFinal;
    
    @FXML
    private Label lblPaciente;
    
    @FXML
    void inserirAtendimento(ActionEvent event) {

    }

    @FXML
    void alterarAtendimento(ActionEvent event) {

    }

    @FXML
    void removerAtendimento(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {
		preencherObjeto();
		try {
			if(entradaPacienteCtrl.salvar()) {
				Utilitarios.message("Convênio salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o convênio.\n" + "Descrição: " + e.getMessage());
		}
    }
	
	private void limparCampos() {
		txtCodigo.setText("");
		txtData.setValue(LocalDate.now());
		txtHora.setCalendar(Calendar.getInstance());
		txtPreTriagem.setText("");
		cmbAtendente.getSelectionModel().select(-1);
		cmbEnfermeira.getSelectionModel().select(-1);
		cmbMedico.getSelectionModel().select(-1);
		cmbPaciente.getSelectionModel().select(-1);
		sbAlta.setValue(false);
		sbAtivado.setValue(true);
		tblAtendimentos.getItems().clear();
	}

	private void preencherObjeto() {
		if(entradaPacienteCtrl == null) 
			entradaPacienteCtrl = new EntradaPacienteCtrl();
		entradaPacienteCtrl.novaEntradaPaciente();
		entradaPacienteCtrl.getEntradaPaciente().setCodigo(txtCodigo.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setDtMovimentacao(Utilitarios.localDateToDate(txtData.getValue()));
		entradaPacienteCtrl.getEntradaPaciente().setHrMovimentacao(txtHora.getCalendar().getTime());
		entradaPacienteCtrl.getEntradaPaciente().setPreTriagem(txtPreTriagem.getText());
		entradaPacienteCtrl.getEntradaPaciente().setAtendente(cmbAtendente.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setEnfermeira(cmbEnfermeira.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setMedico(cmbMedico.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setPaciente(cmbPaciente.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setAlta(sbAlta.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setStatus(sbAtivado.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setEmergencia(sbEmergencia.getValue());
		entradaPacienteCtrl.getEntradaPaciente().setAtendimentos(tblAtendimentos.getItems());
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
		stage.setMinWidth(800);
		stage.setMinHeight(600);
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
		txtData.setOnKeyPressed(Utilitarios.getBloquear());
		txtHora.setOnKeyPressed(Utilitarios.getBloquear());
		txtPreTriagem.setOnKeyPressed(Utilitarios.getBloquear());
		cmbAtendente.setEditable(false);
		cmbEnfermeira.setEditable(false);
		cmbMedico.setEditable(false);
		cmbPaciente.setEditable(false);
		btnInserir.setDisable(true);
		btnSalvar.setDisable(true);
		btnAlterar.setDisable(true);
		btnRemover.setDisable(true);
		sbAlta.setEditable(false);
		sbAtivado.setEditable(false);
		sbEmergencia.setEditable(false);
	}

	private void preencherTela() {
		if (entradaPacienteCtrl != null && entradaPacienteCtrl.getEntradaPaciente() != null) {
			txtCodigo.setValue(entradaPacienteCtrl.getEntradaPaciente().getCodigo());
			txtData.setValue(Utilitarios.dateToLocalDate(entradaPacienteCtrl.getEntradaPaciente().getDtMovimentacao()));
			txtHora.getCalendar().setTime(entradaPacienteCtrl.getEntradaPaciente().getHrMovimentacao());
			txtPreTriagem.setText(entradaPacienteCtrl.getEntradaPaciente().getPreTriagem());
			cmbAtendente.getSelectionModel().select(entradaPacienteCtrl.getEntradaPaciente().getAtendente());
			cmbEnfermeira.getSelectionModel().select(entradaPacienteCtrl.getEntradaPaciente().getEnfermeira());
			cmbMedico.getSelectionModel().select(entradaPacienteCtrl.getEntradaPaciente().getMedico());
			cmbPaciente.getSelectionModel().select(entradaPacienteCtrl.getEntradaPaciente().getPaciente());
			sbAlta.setValue(entradaPacienteCtrl.getEntradaPaciente().isAlta());
			sbAtivado.setValue(entradaPacienteCtrl.getEntradaPaciente().isStatus());
			tblAtendimentos.getItems().setAll(FXCollections.observableList(entradaPacienteCtrl.getEntradaPaciente().getAtendimentos()));
			sbEmergencia.setValue(entradaPacienteCtrl.getEntradaPaciente().isEmergencia());
		}
	}

	private void prepararTela() {
		txtCodigo.setOnKeyPressed(Utilitarios.getBloquear());
		
		cmnResponsavel.prefWidthProperty().bind(tblAtendimentos.widthProperty().multiply(0.35));
		cmnData.prefWidthProperty().bind(tblAtendimentos.widthProperty().multiply(0.2));
		cmnHorarioInicial.prefWidthProperty().bind(tblAtendimentos.widthProperty().multiply(0.225));
		cmnHorarioFinal.prefWidthProperty().bind(tblAtendimentos.widthProperty().multiply(0.225));
		
	    cmnResponsavel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atendimento,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Atendimento, String> param) {
				String value = "";
				return new SimpleStringProperty(value);
			}
		});
		cmnData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atendimento,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Atendimento, String> param) {
				String value = "";
				return new SimpleStringProperty(value);
			}
		});
		cmnHorarioInicial.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atendimento,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Atendimento, String> param) {
				String value = "";
				return new SimpleStringProperty(value);
			}
		});
		cmnHorarioFinal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atendimento,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Atendimento, String> param) {
				String value = "";
				return new SimpleStringProperty(value);
			}
		});
		
		try {
			cmbAtendente.setItems(new FuncionarioCtrl().getFuncionarios(Funcao.atendente));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbEnfermeira.setItems(new FuncionarioCtrl().getFuncionarios(Funcao.enfermeira));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbMedico.setItems(new MedicoCtrl().getMedicos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbPaciente.setItems(new PacienteCtrl().getPacientes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtData.setValue(LocalDate.now());
		txtHora.setCalendar(Calendar.getInstance());
		tblAtendimentos.getItems().addListener(new ListChangeListener<Atendimento>() {

			@Override
			public void onChanged(Change<? extends Atendimento> c) {
				setObrigatoriedadeMedico(sbAlta.getValue() || tblAtendimentos.getItems().size() > 0);
			}

		});
		sbAlta.switchOnProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				setObrigatoriedadeAtendimentos(newValue);
				setObrigatoriedadeMedico(newValue || tblAtendimentos.getItems().size() > 0);
			}
		});
		sbEmergencia.switchOnProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				setObrigatoriedadePaciente(!newValue);
				setObrigatoriedadeMedico(newValue);
				setObrigatoriedadeAtendimentos(!newValue);
			}
		});
	}
	
	private void setObrigatoriedadeMedico(boolean obrigatorio) {
		if(obrigatorio) {
			lblMedico.setText("Médico(Obrigatório):");
			lblMedico.getStyleClass().add("obrigatorio");
		} else {
			lblMedico.setText("Médico:");
			lblMedico.getStyleClass().remove("obrigatorio");
		}
	}
	
	private void setObrigatoriedadePaciente(boolean obrigatorio) {
		if(obrigatorio) {
			lblPaciente.setText("Paciente(Obrigatório):");
			lblPaciente.getStyleClass().add("obrigatorio");
		} else {
			lblPaciente.setText("Paciente:");
			lblPaciente.getStyleClass().remove("obrigatorio");
		}
	}
	
	private void setObrigatoriedadeAtendimentos(boolean obrigatorio) {
		if(obrigatorio) {
			lblAtendimentos.setText("Atendimentos(Obrigatório):");
			lblAtendimentos.getStyleClass().add("obrigatorio");
		} else {
			lblAtendimentos.setText("Atendimentos:");
			lblAtendimentos.getStyleClass().remove("obrigatorio");
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
