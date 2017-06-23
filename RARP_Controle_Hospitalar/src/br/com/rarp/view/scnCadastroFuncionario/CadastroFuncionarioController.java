package br.com.rarp.view.scnCadastroFuncionario;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cargo;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Estado;
import br.com.rarp.model.Telefone;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;

public class CadastroFuncionarioController extends Application implements Initializable {

	private Stage stage;

	@FXML
	private Button btnGravar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField edtNome;

	@FXML
	private TextField edtCPF;

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
	private ComboBox<Estado> cmbEstado;

	@FXML
	private TextField edtCEP;

	@FXML
	private TextField edtTelefone;

	@FXML
	private ComboBox<String> cmbEstadoCivil;

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

	private FuncionarioCtrl funcionarioCtrl;

	@FXML
	private SwitchButton sbAtivado;

	@FXML
	private ListView<Telefone> lsTelefones;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void alterar(FuncionarioCtrl funcionarioCtrl) throws Exception {
		this.funcionarioCtrl = funcionarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (funcionarioCtrl != null)
			preencheTela();
	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void preencheTela() {
		edtCodigo.setText(funcionarioCtrl.getFuncinario().getCodigo() + "");
		edtNome.setText(funcionarioCtrl.getFuncinario().getNome());
	}

	private void limparCampos() {
		edtBairro.clear();
		cmbCargo.getSelectionModel().select(-1);
		edtCEP.clear();
		edtCodigo.clear();
		edtComplemento.clear();
		edtCPF.clear();
		edtCTPS.clear();
		edtDataAdmissao.setText("");
		edtDataNasc.setText("");
		edtLogradouro.clear();
		edtNome.clear();
		edtNumero.clear();
		edtRG.clear();
		edtSalarioContratual.clear();
		edtTelefone.clear();
		cmbCidade.getSelectionModel().select(-1);
		cmbEstado.getSelectionModel().select(-1);
		cmbEstadoCivil.getSelectionModel().select(-1);
		rbFeminimo.setSelected(false);
		rbMasculino.setSelected(false);
		rbSim.setSelected(false);
		rbNao.setSelected(false);
		sbAtivado.switchOnProperty().set(true);
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
		cmbEstado.setDisable(true);
		cmbEstadoCivil.setDisable(true);
		rbFeminimo.setDisable(true);
		rbMasculino.setDisable(true);
		rbSim.setDisable(true);
		rbNao.setDisable(true);
	}

	private void preencherObjeto() {
		if (funcionarioCtrl == null) {
			funcionarioCtrl = new FuncionarioCtrl();
		}
		if (funcionarioCtrl.getFuncinario() == null) {
			funcionarioCtrl.novoFuncionario();
		}
		funcionarioCtrl.getFuncinario().setBairro(edtBairro.getText());
		funcionarioCtrl.getFuncinario().setCargo(cmbCargo.getSelectionModel().getSelectedItem());
		funcionarioCtrl.getFuncinario().setCep(edtCEP.getText());
		funcionarioCtrl.getFuncinario().setCpf(edtCPF.getText());
		funcionarioCtrl.getFuncinario().setCTPS(edtCTPS.getText());
		funcionarioCtrl.getFuncinario().setDtNascimento(edtDataNasc.getCalendar().getTime());
		funcionarioCtrl.getFuncinario().setCidade(cmbCidade.getSelectionModel().getSelectedItem());
		funcionarioCtrl.getFuncinario().setNumero(edtNumero.getText());
		funcionarioCtrl.getFuncinario().setRg(edtRG.getText());
		funcionarioCtrl.getFuncinario().setSalarioContratual(Double.parseDouble(edtSalarioContratual.getText()));
		funcionarioCtrl.getFuncinario().setComplemento(edtComplemento.getText());
		funcionarioCtrl.getFuncinario().setCodigo(Integer.parseInt(edtCodigo.getText()));
		funcionarioCtrl.getFuncinario().setLogradouro(edtLogradouro.getText());
		funcionarioCtrl.getFuncinario().setStatus(sbAtivado.switchOnProperty().get());
		funcionarioCtrl.getFuncinario().setEstadoCivil(cmbEstadoCivil.getSelectionModel().getSelectedItem());
		funcionarioCtrl.getFuncinario().setNome(edtNome.getText());
		funcionarioCtrl.getFuncinario().setDtAdmissao(edtDataAdmissao.getCalendar().getTime());
		funcionarioCtrl.getFuncinario().setPossuiNecessidades(rbSim.isSelected());
		funcionarioCtrl.getFuncinario().setSexo(rbMasculino.isSelected() ? "M" : "F");
	}

	private void preencherTela() {
		edtBairro.setText(funcionarioCtrl.getFuncinario().getBairro());
		edtCEP.setText(funcionarioCtrl.getFuncinario().getCep());
		edtCodigo.setText(funcionarioCtrl.getFuncinario().getCodigo() + "");
		edtComplemento.setText(funcionarioCtrl.getFuncinario().getComplemento());
		edtCPF.setText(funcionarioCtrl.getFuncinario().getCpf());
		edtCTPS.setText(funcionarioCtrl.getFuncinario().getCTPS());
		edtDataAdmissao.getCalendar().setTime((funcionarioCtrl.getFuncinario().getDtAdmissao()));
		edtDataNasc.getCalendar().setTime(funcionarioCtrl.getFuncinario().getDtNascimento());
		edtLogradouro.setText(funcionarioCtrl.getFuncinario().getLogradouro());
		edtNome.setText(funcionarioCtrl.getFuncinario().getNome());
		edtNumero.setText(funcionarioCtrl.getFuncinario().getNumero());
		edtRG.setText(funcionarioCtrl.getFuncinario().getRg());
		edtSalarioContratual.setText(funcionarioCtrl.getFuncinario().getSalarioContratual() + "");
		cmbCargo.getSelectionModel().select(funcionarioCtrl.getFuncinario().getCargo());
		cmbCidade.getSelectionModel().select(funcionarioCtrl.getFuncinario().getCidade());
		rbSim.setSelected(funcionarioCtrl.getFuncinario().isPossuiNecessidades());
		rbMasculino.setSelected(funcionarioCtrl.getFuncinario().getSexo() == "M");
	}

	@FXML
	private void adicionarTelefone(MouseEvent event) {
		Telefone telefone = new Telefone();
		telefone.setNumero(edtTelefone.getText());
		lsTelefones.getItems().add(telefone);
	}

	@FXML
	private void removerTelefone(MouseEvent event) {
		lsTelefones.getItems().remove(lsTelefones.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void salvar(MouseEvent event) {
		preencherObjeto();
		try {
			funcionarioCtrl.salvar();
			Utilitarios.message("Funcionário salvo com sucesso.");
			limparCampos();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o funcionário.\n" + "Descrição: " + e.getMessage());
		}
	}
}