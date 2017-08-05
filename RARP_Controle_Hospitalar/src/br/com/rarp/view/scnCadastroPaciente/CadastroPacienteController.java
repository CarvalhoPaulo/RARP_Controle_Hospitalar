package br.com.rarp.view.scnCadastroPaciente;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import br.com.rarp.control.CidadeCtrl;
import br.com.rarp.control.ConvenioCtrl;
import br.com.rarp.control.PacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Convenio;
import br.com.rarp.model.Paciente;
import br.com.rarp.model.Telefone;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.MaskTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;

public class CadastroPacienteController extends Application implements Initializable {

	private static boolean visualizando;
	
	private static Stage stage;
	
    @FXML
    private Label lblResponsavel;
	
	@FXML
	private TabPane tbPane;
	
    @FXML
    private AnchorPane pnlPrincipal;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField edtNome;

	@FXML
	private MaskTextField edtCPF;

	@FXML
	private TextField edtRG;

	@FXML
	private RadioButton rbFeminimo;

	@FXML
	private RadioButton rbMasculino;

	@FXML
	private CalendarTextField edtDataNasc;

	@FXML
	private TextField edtLogradouro;

	@FXML
	private TextField edtComplemento;

	@FXML
	private TextField edtNumero;

	@FXML
	private TextField edtBairro;

	@FXML
	private ComboBox<Cidade> cmbCidade;
	
	@FXML
	private ComboBox<Paciente> cmbResponsavel;

	@FXML
	private MaskTextField edtCEP;

	@FXML
	private MaskTextField edtTelefone;

	@FXML
	private RadioButton rbSim;

	@FXML
	private RadioButton rbNao;

	@FXML
	private ComboBox<Convenio> cmbConvenio;

	@FXML
	private IntegerTextField edtCodigo;

	private static PacienteCtrl pacienteCtrl;

	@FXML
	private SwitchButton sbAtivado;

	@FXML
	private ListView<Telefone> lsTelefones;

	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroPaciente.fxml"))));
		stage.setTitle("Cadastro de Pacientes");
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

	@SuppressWarnings("static-access")
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void visualizar(PacienteCtrl pacienteCtrl) throws Exception {
		visualizando = true;
		this.pacienteCtrl = pacienteCtrl;
		rbNao.setSelected(true);
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(PacienteCtrl pacienteCtrl) throws Exception {
		this.pacienteCtrl = pacienteCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		if (pacienteCtrl != null && pacienteCtrl.getPaciente() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
	}

	@SuppressWarnings("unchecked")
	private void prepararTela() {
		try {
			sbAtivado.setValue(true);
			edtCodigo.setDisable(true);
			edtCodigo.setFocusTraversable(true);
			
			tbPane.requestFocus();
			edtNome.requestFocus();
			edtDataNasc.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			cmbCidade.setItems(new CidadeCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
			cmbConvenio.setItems(new ConvenioCtrl().consultar(new Campo("CONV.status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
			
			ToggleGroup tgPossuiNecessidades = new ToggleGroup();
			tgPossuiNecessidades.getToggles().add(rbSim);
			tgPossuiNecessidades.getToggles().add(rbNao);
			tgPossuiNecessidades.selectToggle(rbNao);
			
			ToggleGroup tgSexo = new ToggleGroup();	
			tgSexo.getToggles().add(rbMasculino);
			tgSexo.getToggles().add(rbFeminimo);
			tgSexo.selectToggle(rbMasculino);
			
			edtDataNasc.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(!newValue) {
						if (edtDataNasc.getCalendar() != null && Utilitarios.isMaiorIdade(edtDataNasc.getCalendar().getTime())) {
							if(lblResponsavel.getStyleClass().indexOf("obrigatorio") == -1)
								lblResponsavel.getStyleClass().add("obrigatorio");	
						} else {
							if(lblResponsavel.getStyleClass().indexOf("obrigatorio") != -1)
								lblResponsavel.getStyleClass().remove("obrigatorio");	
						}
					}
				}
			});
			
			pnlPrincipal.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if (event.getTarget() != null 
							&& event.getTarget() instanceof Node 
							&& ((Node) event.getTarget()).getId() != null
							&& event.getCode() == KeyCode.TAB) {
						String id = ((Node) event.getTarget()).getId();
						if (!event.isShiftDown()) {
							
							if(id.equals("edtBairro")) {
								tbPane.getSelectionModel().select(1);
								cmbConvenio.requestFocus();
								event.consume();
							}
							
							if(id.equals("lsTelefones")) {
								btnSalvar.requestFocus();
								event.consume();
							}
							
							if(id.equals("cmbConvenio")) {
								edtTelefone.requestFocus();
								event.consume();
							}
							
							if(id.equals("btnSalvar")) {
								tbPane.getSelectionModel().select(0);
								edtNome.requestFocus();
								event.consume();
							}
						}
						if (event.isShiftDown()) {	
							if(id.equals("edtNome")) {
								btnSalvar.requestFocus();
								event.consume();
							}
							
							if(id.equals("edtTelefone")) {
								cmbConvenio.requestFocus();
								event.consume();
							}
							
							if(id.equals("cmbConvenio")) {
								tbPane.getSelectionModel().select(0);
								edtBairro.requestFocus();
								event.consume();
							}
							
							if(id.equals("btnSalvar")) {
								tbPane.getSelectionModel().select(1);
								lsTelefones.requestFocus();
								event.consume();
							}
						} 
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void limparCampos() {
		edtBairro.clear();
		cmbConvenio.getSelectionModel().select(-1);
		edtCEP.clear();
		edtCodigo.clear();
		edtComplemento.clear();
		edtCPF.clear();
		edtDataNasc.setText("");
		edtLogradouro.clear();
		edtNome.clear();
		edtNumero.clear();
		edtRG.clear();
		edtTelefone.clear();
		cmbCidade.getSelectionModel().select(-1);
		rbMasculino.setSelected(true);
		rbNao.setSelected(true);
		sbAtivado.setValue(true);
		lsTelefones.getItems().clear();
	}

	private void bloquearTela() {
		edtBairro.setDisable(true);
		cmbConvenio.setDisable(true);
		edtCEP.setDisable(true);
		edtCodigo.setDisable(true);
		edtComplemento.setDisable(true);
		edtCPF.setDisable(true);
		edtDataNasc.setDisable(true);
		edtLogradouro.setDisable(true);
		edtNome.setDisable(true);
		edtNumero.setDisable(true);
		edtRG.setDisable(true);
		edtTelefone.setDisable(true);
		sbAtivado.setDisable(true);
		btnSalvar.setDisable(true);
		cmbCidade.setDisable(true);
		rbFeminimo.setDisable(true);
		rbMasculino.setDisable(true);
		rbSim.setDisable(true);
		rbNao.setDisable(true);
	}

	private void preencherObjeto() {
		if (pacienteCtrl == null) {
			pacienteCtrl = new PacienteCtrl();
		}
		if (pacienteCtrl.getPaciente() == null) {
			pacienteCtrl.novoPaciente();
		}
		pacienteCtrl.getPaciente().setCodigo(edtCodigo.getValue());
		pacienteCtrl.getPaciente().setBairro(edtBairro.getText());
		pacienteCtrl.getPaciente().setConvenio(cmbConvenio.getSelectionModel().getSelectedItem());
		pacienteCtrl.getPaciente().setCep(edtCEP.getText());
		pacienteCtrl.getPaciente().setCpf(edtCPF.getText());
		if (edtDataNasc.getCalendar() != null)
			pacienteCtrl.getPaciente().setDtNascimento(edtDataNasc.getCalendar().getTime());
		pacienteCtrl.getPaciente().setCidade(cmbCidade.getSelectionModel().getSelectedItem());
		pacienteCtrl.getPaciente().setNumero(edtNumero.getText());
		pacienteCtrl.getPaciente().setRg(edtRG.getText());
		pacienteCtrl.getPaciente().setComplemento(edtComplemento.getText());
		pacienteCtrl.getPaciente().setLogradouro(edtLogradouro.getText());
		pacienteCtrl.getPaciente().setStatus(sbAtivado.getValue());
		pacienteCtrl.getPaciente().setNome(edtNome.getText());
		pacienteCtrl.getPaciente().setPossuiNecessidades(rbSim.isSelected());
		pacienteCtrl.getPaciente().setSexo(rbMasculino.isSelected() ? "M" : "F");
		pacienteCtrl.getPaciente().setTelefones(lsTelefones.getItems());
	}

	private void preencherTela() {
		edtBairro.setText(pacienteCtrl.getPaciente().getBairro());
		edtCEP.setText(pacienteCtrl.getPaciente().getCep());
		edtCodigo.setText(pacienteCtrl.getPaciente().getCodigo() + "");
		edtComplemento.setText(pacienteCtrl.getPaciente().getComplemento());
		edtCPF.setText(pacienteCtrl.getPaciente().getCpf());
		
		if(pacienteCtrl.getPaciente().getDtNascimento() != null) {
			edtDataNasc.setCalendar(new GregorianCalendar());
			edtDataNasc.getCalendar().setTime(pacienteCtrl.getPaciente().getDtNascimento());
		}
		
		edtLogradouro.setText(pacienteCtrl.getPaciente().getLogradouro());
		edtNome.setText(pacienteCtrl.getPaciente().getNome());
		edtNumero.setText(pacienteCtrl.getPaciente().getNumero());
		edtRG.setText(pacienteCtrl.getPaciente().getRg());
		cmbConvenio.getSelectionModel().select(pacienteCtrl.getPaciente().getConvenio());
		cmbCidade.getSelectionModel().select(pacienteCtrl.getPaciente().getCidade());
		rbSim.setSelected(pacienteCtrl.getPaciente().isPossuiNecessidades());
		if(pacienteCtrl.getPaciente().getSexo() == "M")
			rbMasculino.setSelected(true);
		else
			rbFeminimo.setSelected(true);
		lsTelefones.setItems(FXCollections.observableList(pacienteCtrl.getPaciente().getTelefones()));
		sbAtivado.setValue(pacienteCtrl.getPaciente().isStatus());
	}
	
	@FXML
	private void adicionarTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero(edtTelefone.getText());
		if (!telefone.getNumero().isEmpty())
			lsTelefones.getItems().add(telefone);
		edtTelefone.setText("");
	}

	@FXML
	private void removerTelefone() {
		lsTelefones.getItems().remove(lsTelefones.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void salvar() {
		preencherObjeto();
		try {
			if(pacienteCtrl.salvar()) {
				Utilitarios.message("Paciente salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o paciente.\n" + "Descri��o: " + e.getMessage());
		}
	}
	
    @FXML
    private void voltar(ActionEvent event) {
    	pacienteCtrl = null;
    	stage.hide();
    	visualizando = false;
    }
}