package br.com.rarp.view.scnCadastroEncaminhamento;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.EncaminhamentoCtrl;
import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.EspacoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.Leito;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
import br.com.rarp.view.scnComponents.ImageCard;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SelectionNode;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroEncaminhamentoController extends Application implements Initializable {
	
	private static Stage stage;

    @FXML
    private IntegerTextField txtCodigo;

    @FXML
    private SwitchButton sbAtivado;
    
    @FXML
    private AutoCompleteComboBox<Espaco> cmbOrigem;

    @FXML
    private AutoCompleteComboBox<Espaco> cmbDestino;
    
    @FXML
    private AutoCompleteComboBox<EntradaPaciente> cmbEntradaPaciente;
    
    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private SelectionNode<ImageCard> pnlOrigem;

    @FXML
    private SelectionNode<ImageCard> pnlDestino;

	private static EncaminhamentoCtrl encaminhamentoCtrl;
	private static boolean visualizando;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEncaminhamento.fxml"))));
		stage.setTitle("Cadastro de Encaminhamentos");
		setStage(stage);
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getTarget() instanceof Button && event.getCode() == KeyCode.ENTER)
					((Button) event.getTarget()).arm();
				
				if(event.getCode() == KeyCode.ESCAPE)
					voltar(new ActionEvent());
			}
		});
		stage.setMinWidth(600);
		stage.setMinHeight(450);
	}

	public Stage getStage() {
		return stage;
	}

	@SuppressWarnings("static-access")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void limparCampos() {
		txtCodigo.clear();
		cmbDestino.getSelectionModel().select(-1);
		cmbOrigem.getSelectionModel().select(-1);
		cmbEntradaPaciente.getSelectionModel().select(-1);
		pnlDestino.getSelectionModel().select(-1);
		pnlOrigem.getSelectionModel().select(-1);
		sbAtivado.switchOnProperty().set(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		prepararTela();
		if (encaminhamentoCtrl != null && encaminhamentoCtrl.getEncaminhamento() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
	}

	private void prepararTela() {
		sbAtivado.switchOnProperty().set(true);
		txtCodigo.setDisable(true);
		try {
			cmbOrigem.getItems().setAll(new EspacoCtrl().getEspacos());
			cmbDestino.getItems().setAll(cmbOrigem.getItems());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cmbEntradaPaciente.getItems().setAll(new EntradaPacienteCtrl().getEntradasPaciente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cmbOrigem.setOnAction(onChange);
		cmbDestino.setOnAction(onChange);
	}
	
	EventHandler<ActionEvent> onChange = (event) -> {
		if(event.getSource() == cmbOrigem && cmbOrigem.getValue() != null) {
			for (Leito l: cmbOrigem.getValue().getLeitos()) {
				ImageCard img = new ImageCard();
				img.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
				img.setLeito(l);
				pnlOrigem.getItems().add(img);
			}
		}
		
		if(event.getSource() == cmbDestino && cmbOrigem.getValue() != null) {
			for (Leito l: cmbOrigem.getValue().getLeitos()) {
				ImageCard img = new ImageCard();
				img.getPathImage().set(getClass().getResource("../img/leitos.png").toString());
				img.setLeito(l);
				pnlDestino.getItems().add(img);
			}
		}
	};

	private void bloquearTela() {
		txtCodigo.setDisable(true);
		sbAtivado.setEditable(true);
		cmbOrigem.setEditable(false);
		cmbDestino.setEditable(false);
		cmbDestino.setEditable(false);
		pnlOrigem.setEditable(false);
		pnlDestino.setEditable(false);
		btnSalvar.setDisable(true);
	}

	public void inserir() throws Exception {
		encaminhamentoCtrl = null;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(EncaminhamentoCtrl encaminhamentoCtrl) throws Exception {
		this.encaminhamentoCtrl = encaminhamentoCtrl;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void visualizar(EncaminhamentoCtrl encaminhamentoCtrl) throws Exception {
		visualizando = true;
		this.encaminhamentoCtrl = encaminhamentoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@FXML
	private void salvar(ActionEvent event) {
		preencherObjeto();
		try {
			if(encaminhamentoCtrl.salvar()) {
				Utilitarios.message("Espaço salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar espaço.\n"
						   + "Descrição: " + e.getMessage());
		}
	}

	@FXML
	private void voltar(ActionEvent event) {
		encaminhamentoCtrl = null;
		stage.hide();
		visualizando = false;
	}

	private void preencherObjeto() {
		if (encaminhamentoCtrl == null)
			encaminhamentoCtrl = new EncaminhamentoCtrl();

		if (encaminhamentoCtrl.getEncaminhamento() == null)
			encaminhamentoCtrl.novoEncaminhamento();
		
		encaminhamentoCtrl.getEncaminhamento().setEntradaPaciente(cmbEntradaPaciente.getValue());
		encaminhamentoCtrl.getEncaminhamento().setCodigo(txtCodigo.getValue());
		encaminhamentoCtrl.getEncaminhamento().setDestino(pnlDestino.getValue().getLeito());
		encaminhamentoCtrl.getEncaminhamento().setOrigem(pnlOrigem.getValue().getLeito());
		encaminhamentoCtrl.getEncaminhamento().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		txtCodigo.setText(encaminhamentoCtrl.getEncaminhamento().getCodigo() + "");
		cmbEntradaPaciente.setValue(encaminhamentoCtrl.getEncaminhamento().getEntradaPaciente());
		cmbOrigem.getSelectionModel().select(encaminhamentoCtrl.getEncaminhamento().getOrigem().getEspaco());
		cmbDestino.getSelectionModel().select(encaminhamentoCtrl.getEncaminhamento().getDestino().getEspaco());
		
		ImageCard origem = new ImageCard();
		origem.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
		origem.setLeito(encaminhamentoCtrl.getEncaminhamento().getOrigem());
		pnlOrigem.getItems().add(origem);
		
		ImageCard destino = new ImageCard();
		destino.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
		destino.setLeito(encaminhamentoCtrl.getEncaminhamento().getDestino());
		pnlOrigem.getItems().add(destino);
		
		pnlOrigem.getSelectionModel().select(origem);
		pnlDestino.getSelectionModel().select(destino);
		sbAtivado.setValue(encaminhamentoCtrl.getEncaminhamento().isStatus());
	}

}
