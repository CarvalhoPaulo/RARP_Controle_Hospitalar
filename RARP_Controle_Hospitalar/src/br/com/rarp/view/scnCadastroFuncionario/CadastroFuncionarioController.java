package br.com.rarp.view.scnCadastroFuncionario;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import br.com.rarp.control.CargoCtrl;
import br.com.rarp.control.CidadeCtrl;
import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Cargo;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Telefone;
import br.com.rarp.utils.AutoCompleteComboBoxListener;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.MaskTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
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

public class CadastroFuncionarioController extends Application implements Initializable {

	private static boolean visualizando;
	private static Stage stage;
	
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
	private MaskTextField edtCEP;

	@FXML
	private MaskTextField edtTelefone;

	@FXML
	private RadioButton rbSim;

	@FXML
	private RadioButton rbNao;

	@FXML
	private TextField edtCTPS;

	@FXML
	private CalendarTextField edtDataAdmissao;

	@FXML
	private TextField edtSalarioContratual;

	@FXML
	private ComboBox<Cargo> cmbCargo;

	@FXML
	private IntegerTextField edtCodigo;

	private static FuncionarioCtrl funcionarioCtrl;

	@FXML
	private SwitchButton sbAtivado;

	@FXML
	private ListView<Telefone> lsTelefones;

	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
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
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	@SuppressWarnings("static-access")
	public void visualizar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		visualizando = true;
		this.funcionarioCtrl = funcionarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		visualizando = false;
		this.funcionarioCtrl = funcionarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		if (funcionarioCtrl != null && funcionarioCtrl.getFuncionario() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
		
		new AutoCompleteComboBoxListener<>(cmbCargo);
		new AutoCompleteComboBoxListener<>(cmbCidade);
	}

	@SuppressWarnings("unchecked")
	private void prepararTela() {
		try {
			sbAtivado.setValue(true);
			edtCodigo.setDisable(true);
			edtCodigo.setFocusTraversable(true);
			
			tbPane.requestFocus();
			edtNome.requestFocus();
			
			edtDataAdmissao.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			edtDataNasc.setDateFormat(edtDataAdmissao.getDateFormat());
			edtDataAdmissao.setCalendar(Calendar.getInstance());
			cmbCidade.setItems(new CidadeCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
			cmbCargo.setItems(new CargoCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
			
			ToggleGroup tgPossuiNecessidades = new ToggleGroup();
			tgPossuiNecessidades.getToggles().add(rbSim);
			tgPossuiNecessidades.getToggles().add(rbNao);
			tgPossuiNecessidades.selectToggle(rbNao);
			
			ToggleGroup tgSexo = new ToggleGroup();
			tgSexo.getToggles().add(rbMasculino);
			tgSexo.getToggles().add(rbFeminimo);
			tgSexo.selectToggle(rbMasculino);
			
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
								edtCTPS.requestFocus();
								event.consume();
							}
							
							if(id.equals("lsTelefones")) {
								btnSalvar.requestFocus();
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
							
							if(id.equals("edtCTPS")) {
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
		cmbCargo.getSelectionModel().select(-1);
		edtCEP.clear();
		edtCodigo.clear();
		edtComplemento.clear();
		edtCPF.clear();
		edtCTPS.clear();
		edtDataAdmissao.setCalendar(new GregorianCalendar());
		edtDataNasc.setText("");
		edtLogradouro.clear();
		edtNome.clear();
		edtNumero.clear();
		edtRG.clear();
		edtSalarioContratual.clear();
		edtTelefone.clear();
		cmbCidade.getSelectionModel().select(-1);
		rbMasculino.setSelected(true);
		rbNao.setSelected(true);
		sbAtivado.setValue(true);
		lsTelefones.getItems().clear();
	}

	private void bloquearTela() {
		edtBairro.setDisable(true);
		cmbCargo.setDisable(true);
		edtCEP.setDisable(true);
		edtCodigo.setDisable(true);
		edtComplemento.setDisable(true);
		edtCPF.setDisable(true);
		edtCTPS.setDisable(true);
		edtDataAdmissao.setDisable(true);
		edtDataNasc.setDisable(true);
		edtLogradouro.setDisable(true);
		edtNome.setDisable(true);
		edtNumero.setDisable(true);
		edtRG.setDisable(true);
		edtSalarioContratual.setDisable(true);
		edtTelefone.setDisable(true);
		sbAtivado.setDisable(true);
		btnSalvar.setDisable(true);
		cmbCidade.setDisable(true);
		rbFeminimo.setDisable(true);
		rbMasculino.setDisable(true);
		rbSim.setDisable(true);
		rbNao.setDisable(true);
		lsTelefones.setDisable(true);
	}

	private void preencherObjeto() {
		if (funcionarioCtrl == null) {
			funcionarioCtrl = new FuncionarioCtrl();
		}
		if (funcionarioCtrl.getFuncionario() == null) {
			funcionarioCtrl.novoFuncionario();
		}
		funcionarioCtrl.getFuncionario().setCodigo(edtCodigo.getValue());
		funcionarioCtrl.getFuncionario().setBairro(edtBairro.getText());
		funcionarioCtrl.getFuncionario().setCargo(cmbCargo.getSelectionModel().getSelectedItem());
		funcionarioCtrl.getFuncionario().setCep(edtCEP.getText());
		funcionarioCtrl.getFuncionario().setCpf(edtCPF.getText());
		funcionarioCtrl.getFuncionario().setCTPS(edtCTPS.getText());
		if (edtDataNasc.getCalendar() != null)
			funcionarioCtrl.getFuncionario().setDtNascimento(edtDataNasc.getCalendar().getTime());
		funcionarioCtrl.getFuncionario().setCidade(cmbCidade.getSelectionModel().getSelectedItem());
		funcionarioCtrl.getFuncionario().setNumero(edtNumero.getText());
		funcionarioCtrl.getFuncionario().setRg(edtRG.getText());
		funcionarioCtrl.getFuncionario().setSalarioContratual(Utilitarios.strToDouble(edtSalarioContratual.getText()));
		funcionarioCtrl.getFuncionario().setComplemento(edtComplemento.getText());
		funcionarioCtrl.getFuncionario().setLogradouro(edtLogradouro.getText());
		funcionarioCtrl.getFuncionario().setStatus(sbAtivado.getValue());
		funcionarioCtrl.getFuncionario().setNome(edtNome.getText());
		if (edtDataAdmissao.getCalendar() != null)
			funcionarioCtrl.getFuncionario().setDtAdmissao(edtDataAdmissao.getCalendar().getTime());
		funcionarioCtrl.getFuncionario().setPossuiNecessidades(rbSim.isSelected());
		funcionarioCtrl.getFuncionario().setSexo(rbMasculino.isSelected() ? "M" : "F");
		funcionarioCtrl.getFuncionario().setTelefones(lsTelefones.getItems());
	}

	private void preencherTela() {
		edtBairro.setText(funcionarioCtrl.getFuncionario().getBairro());
		edtCEP.setText(funcionarioCtrl.getFuncionario().getCep());
		edtCodigo.setText(funcionarioCtrl.getFuncionario().getCodigo() + "");
		edtComplemento.setText(funcionarioCtrl.getFuncionario().getComplemento());
		edtCPF.setText(funcionarioCtrl.getFuncionario().getCpf());
		edtCTPS.setText(funcionarioCtrl.getFuncionario().getCTPS());
		
		if(funcionarioCtrl.getFuncionario().getDtAdmissao() != null)
			edtDataAdmissao.getCalendar().setTime((funcionarioCtrl.getFuncionario().getDtAdmissao()));
		if(funcionarioCtrl.getFuncionario().getDtNascimento() != null)
			edtDataNasc.getCalendar().setTime(funcionarioCtrl.getFuncionario().getDtNascimento());	
		
		edtLogradouro.setText(funcionarioCtrl.getFuncionario().getLogradouro());
		edtNome.setText(funcionarioCtrl.getFuncionario().getNome());
		edtNumero.setText(funcionarioCtrl.getFuncionario().getNumero());
		edtRG.setText(funcionarioCtrl.getFuncionario().getRg());
		edtSalarioContratual.setText(funcionarioCtrl.getFuncionario().getSalarioContratual() + "");
		cmbCargo.getSelectionModel().select(funcionarioCtrl.getFuncionario().getCargo());
		cmbCidade.getSelectionModel().select(funcionarioCtrl.getFuncionario().getCidade());
		rbSim.setSelected(funcionarioCtrl.getFuncionario().isPossuiNecessidades());
		rbMasculino.setSelected(funcionarioCtrl.getFuncionario().getSexo() == "M");
		lsTelefones.setItems(FXCollections.observableList(funcionarioCtrl.getFuncionario().getTelefones()));
		sbAtivado.setValue(funcionarioCtrl.getFuncionario().isStatus());
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
			if(funcionarioCtrl.salvar()) {
				Utilitarios.message("Funcionário salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o funcionário.\n" + "Descrição: " + e.getMessage());
		}
	}
	
    @FXML
    private void voltar(ActionEvent event) {
    	funcionarioCtrl = null;
    	stage.hide();
    	visualizando = false;
    }
}