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
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.MaskTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;

public class CadastroFuncionarioController extends Application implements Initializable {

	private static boolean visualizando;
	private static Stage stage;

	@FXML
	private Button btnGravar;

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
	private TextField edtCodigo;

	private static FuncionarioCtrl funcionarioCtrl;

	@FXML
	private SwitchButton sbAtivado;

	@FXML
	private ListView<Telefone> lsTelefones;

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	@SuppressWarnings("static-access")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("static-access")
	public void alterar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		this.funcionarioCtrl = funcionarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.setValue(true);
		edtCodigo.setDisable(true);
		edtDataAdmissao.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
		edtDataNasc.setDateFormat(edtDataAdmissao.getDateFormat());
		edtDataAdmissao.setCalendar(Calendar.getInstance());
		CidadeCtrl cidadeCtrl = new CidadeCtrl();
		try {
			cmbCidade.setItems(cidadeCtrl.consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cmbCargo.setItems(new CargoCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (funcionarioCtrl != null && funcionarioCtrl.getFuncionario() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
		
		ToggleGroup tgPossuiNecessidades = new ToggleGroup();
		tgPossuiNecessidades.getToggles().add(rbSim);
		tgPossuiNecessidades.getToggles().add(rbNao);
		tgPossuiNecessidades.selectToggle(rbNao);
		
		ToggleGroup tgSexo = new ToggleGroup();
		tgSexo.getToggles().add(rbMasculino);
		tgSexo.getToggles().add(rbFeminimo);
		tgSexo.selectToggle(rbMasculino);

	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
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
		edtDataNasc.setCalendar(new GregorianCalendar());
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
		btnGravar.setDisable(true);
		cmbCidade.setDisable(true);
		rbFeminimo.setDisable(true);
		rbMasculino.setDisable(true);
		rbSim.setDisable(true);
		rbNao.setDisable(true);
	}

	private void preencherObjeto() {
		if (funcionarioCtrl == null) {
			funcionarioCtrl = new FuncionarioCtrl();
		}
		if (funcionarioCtrl.getFuncionario() == null) {
			funcionarioCtrl.novoFuncionario();
		}
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
		funcionarioCtrl.getFuncionario().setCodigo(Utilitarios.strToInt(edtCodigo.getText()));
		funcionarioCtrl.getFuncionario().setLogradouro(edtLogradouro.getText());
		funcionarioCtrl.getFuncionario().setStatus(sbAtivado.getValue());
		funcionarioCtrl.getFuncionario().setNome(edtNome.getText());
		if (edtDataAdmissao.getCalendar() != null)
			funcionarioCtrl.getFuncionario().setDtAdmissao(edtDataAdmissao.getCalendar().getTime());
		funcionarioCtrl.getFuncionario().setPossuiNecessidades(rbSim.isSelected());
		funcionarioCtrl.getFuncionario().setSexo(rbMasculino.isSelected() ? "M" : "F");
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
	}

	@SuppressWarnings("static-access")
	public void visualizar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		visualizando = true;
		this.funcionarioCtrl = funcionarioCtrl;
		rbNao.setSelected(true);
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
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
    	stage.hide();
    }
}