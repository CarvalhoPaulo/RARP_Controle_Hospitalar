package br.com.rarp.view.scnCadastroConvenio;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import br.com.rarp.control.CidadeCtrl;
import br.com.rarp.control.ConvenioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Telefone;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.ImageViewButton;

public class CadastroConvenioController extends Application implements Initializable {
	
    @FXML
    private AnchorPane pnlPrincipal;

    @FXML
    private TabPane tbPane;

    @FXML
    private TextField edtNome;

    @FXML
    private TextField edtLogradouro;

    @FXML
    private TextField edtNumero;

    @FXML
    private TextField edtComplemento;

    @FXML
    private AutoCompleteComboBox<Cidade> cmbCidade;

    @FXML
    private MaskTextField edtCEP;

    @FXML
    private CalendarTextField edtDataNasc;

    @FXML
    private MaskTextField edtCNPJ;

    @FXML
    private TextField edtANS;

    @FXML
    private ComboBox<String> cmbTipo;

    @FXML
    private TextField edtBairro;

    @FXML
    private TextField edtRazaoSocial;

    @FXML
    private MaskTextField edtTelefone;

    @FXML
    private ListView<Telefone> lsTelefones;

    @FXML
    private IntegerTextField edtCodigo;

    @FXML
    private SwitchButton sbAtivado;
    
    @FXML
    private ImageViewButton btnAdd;

    @FXML
    private ImageViewButton btnRemove;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;
	
	private static ConvenioCtrl convenioCtrl;
	
	private static boolean visualizando;
	
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroConvenio.fxml"))));
		stage.setTitle("Cadastro de Convênios");
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
	public void visualizar(ConvenioCtrl convenioCtrl) throws Exception {
		visualizando = true;
		this.convenioCtrl = convenioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(ConvenioCtrl convenioCtrl) throws Exception {
		this.convenioCtrl = convenioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		bloquearTela();
		if (convenioCtrl != null && convenioCtrl.getConvenio() != null)
			preencherTela();
	}

	@SuppressWarnings("unchecked")
	private void prepararTela() {
		try {
			sbAtivado.setValue(true);
			edtCodigo.setDisable(true);
			edtCodigo.setFocusTraversable(true);
			cmbTipo.getItems().add("Particular");
			cmbTipo.getItems().add("Público");
			
			tbPane.requestFocus();
			edtNome.requestFocus();
			edtDataNasc.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			cmbCidade.setItems(new CidadeCtrl().consultar(new Campo("status", "", TipoCampo.booleano), new Ativado(), "Ativado"));
			
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
								lsTelefones.requestFocus();
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
							
							if(id.equals("edtTelefon")) {
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
		edtANS.clear();
		edtRazaoSocial.clear();
		cmbTipo.getSelectionModel().select(-1);
		edtBairro.clear();
		edtCEP.clear();
		edtCodigo.clear();
		edtComplemento.clear();
		edtCNPJ.clear();
		edtDataNasc.setText("");
		edtLogradouro.clear();
		edtNome.clear();
		edtNumero.clear();
		edtTelefone.clear();
		cmbCidade.getSelectionModel().select(-1);
		sbAtivado.setValue(true);
		lsTelefones.getItems().clear();
	}

	private void bloquearTela() {
		edtANS.setDisable(visualizando);
		edtRazaoSocial.setDisable(visualizando);
		cmbTipo.setDisable(visualizando);
		edtBairro.setDisable(visualizando);
		edtCEP.setDisable(visualizando);
		edtCodigo.setDisable(visualizando);
		edtComplemento.setDisable(visualizando);
		edtCNPJ.setDisable(visualizando);
		edtDataNasc.setDisable(visualizando);
		edtLogradouro.setDisable(visualizando);
		edtNome.setDisable(visualizando);
		edtNumero.setDisable(visualizando);
		edtTelefone.setDisable(visualizando);
		sbAtivado.setDisable(visualizando);
		btnSalvar.setDisable(visualizando);
		cmbCidade.setDisable(visualizando);
		lsTelefones.setDisable(visualizando);
		btnAdd.setDisable(visualizando);
		btnRemove.setDisable(visualizando);
	}

	private void preencherObjeto() {
		if (convenioCtrl == null) {
			convenioCtrl = new ConvenioCtrl();
		}
		if (convenioCtrl.getConvenio() == null) {
			convenioCtrl.novoConvenio();
		}
		convenioCtrl.getConvenio().setCodigo(edtCodigo.getValue());
		convenioCtrl.getConvenio().setANS(edtANS.getText());
		convenioCtrl.getConvenio().setRazaoSocial(edtRazaoSocial.getText());
		convenioCtrl.getConvenio().setTipo(cmbTipo.getSelectionModel().getSelectedIndex() + 1);
		convenioCtrl.getConvenio().setBairro(edtBairro.getText());
		convenioCtrl.getConvenio().setCep(edtCEP.getText());
		convenioCtrl.getConvenio().setCnpj(edtCNPJ.getText());
		if (edtDataNasc.getCalendar() != null)
			convenioCtrl.getConvenio().setDtNascimento(edtDataNasc.getCalendar().getTime());
		convenioCtrl.getConvenio().setCidade(cmbCidade.getSelectionModel().getSelectedItem());
		convenioCtrl.getConvenio().setNumero(edtNumero.getText());
		convenioCtrl.getConvenio().setComplemento(edtComplemento.getText());
		convenioCtrl.getConvenio().setLogradouro(edtLogradouro.getText());
		convenioCtrl.getConvenio().setStatus(sbAtivado.getValue());
		convenioCtrl.getConvenio().setNome(edtNome.getText());
		convenioCtrl.getConvenio().setTelefones(lsTelefones.getItems());
	}

	private void preencherTela() {
		edtBairro.setText(convenioCtrl.getConvenio().getBairro());
		edtCEP.setText(convenioCtrl.getConvenio().getCep());
		edtCodigo.setText(convenioCtrl.getConvenio().getCodigo() + "");
		edtComplemento.setText(convenioCtrl.getConvenio().getComplemento());
		edtCNPJ.setText(convenioCtrl.getConvenio().getCnpj());
		edtANS.setText(convenioCtrl.getConvenio().getANS());
		edtRazaoSocial.setText(convenioCtrl.getConvenio().getRazaoSocial());
		cmbTipo.getSelectionModel().select(convenioCtrl.getConvenio().getTipo()-1);
		
		if(convenioCtrl.getConvenio().getDtNascimento() != null) {
			edtDataNasc.setCalendar(new GregorianCalendar());
			edtDataNasc.getCalendar().setTime(convenioCtrl.getConvenio().getDtNascimento());
		}
		
		edtLogradouro.setText(convenioCtrl.getConvenio().getLogradouro());
		edtNome.setText(convenioCtrl.getConvenio().getNome());
		edtNumero.setText(convenioCtrl.getConvenio().getNumero());
		cmbCidade.getSelectionModel().select(convenioCtrl.getConvenio().getCidade());
		lsTelefones.setItems(FXCollections.observableList(convenioCtrl.getConvenio().getTelefones()));
		sbAtivado.setValue(convenioCtrl.getConvenio().isStatus());
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
			if(convenioCtrl.salvar()) {
				Utilitarios.message("Convênio salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o convênio.\n" + "Descrição: " + e.getMessage());
		}
	}
	
    @FXML
    private void voltar(ActionEvent event) {
    	convenioCtrl = null;
    	stage.hide();
    	visualizando = false;
    }
}