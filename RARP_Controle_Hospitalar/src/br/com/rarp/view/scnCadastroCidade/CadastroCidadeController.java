package br.com.rarp.view.scnCadastroCidade;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.CidadeCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Estado;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroCidadeController extends Application implements Initializable {

	private static boolean visualizando;
	
	private static CidadeCtrl cidadeCtrl;
	
	private static Stage stage;

	@FXML
	private IntegerTextField edtCodigo;
	
    @FXML
    private TextField edtNome;

    @FXML
    private IntegerTextField edtIBGE;

    @FXML
    private ComboBox<Estado> cmbEstado;

	@FXML
	private SwitchButton sbAtivado;
	
	@FXML
    private Button btnGravar;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcion�rios");
		stage.getScene().setOnKeyTyped(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println("");
			}
		});
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

	@SuppressWarnings("static-access")
	public void alterar(CidadeCtrl cidadeCtrl) throws Exception {
		this.cidadeCtrl = cidadeCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepararTela();
		if (cidadeCtrl != null && cidadeCtrl.getCidade() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
	}

	private void prepararTela() {
		try {
			sbAtivado.setValue(true);
			edtCodigo.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void limparCampos() {
		edtCodigo.clear();
		edtNome.clear();
		edtIBGE.clear();
		cmbEstado.getSelectionModel().select(-1);
		sbAtivado.setValue(true);
	}

	private void bloquearTela() {
		edtCodigo.setDisable(true);
		sbAtivado.setDisable(true);
		btnGravar.setDisable(true);
		edtNome.setDisable(true);
		edtIBGE.setDisable(true);
		cmbEstado.setDisable(true);
	}

	private void preencherObjeto() {
		if (cidadeCtrl == null) {
			cidadeCtrl = new CidadeCtrl();
		}
		if (cidadeCtrl.getCidade() == null) {
			cidadeCtrl.novaCidade();
		}
		cidadeCtrl.getCidade().setCodigo(edtCodigo.getValue());
		cidadeCtrl.getCidade().setNome(edtNome.getText());
		cidadeCtrl.getCidade().setCodigoIBGE(edtIBGE.getValue());
		cidadeCtrl.getCidade().setEstado(cmbEstado.getSelectionModel().getSelectedItem());
		cidadeCtrl.getCidade().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		if (cidadeCtrl != null && cidadeCtrl.getCidade() != null) {
			edtCodigo.setText(cidadeCtrl.getCidade().getCodigo() + "");
			edtNome.setText(cidadeCtrl.getCidade().getNome());
			edtIBGE.setValue(cidadeCtrl.getCidade().getCodigoIBGE());
			if(cidadeCtrl.getCidade().getEstado() != null)
				cmbEstado.getSelectionModel().select(cidadeCtrl.getCidade().getEstado());
			sbAtivado.setValue(cidadeCtrl.getCidade().isStatus());
		}
	}

	@SuppressWarnings("static-access")
	public void visualizar(CidadeCtrl cidadeCtrl) throws Exception {
		visualizando = true;
		this.cidadeCtrl = cidadeCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@FXML
	private void salvar() {
		preencherObjeto();
		try {
			if(cidadeCtrl.salvar()) {
				Utilitarios.message("Funcion�rio salvo com sucesso.");
				limparCampos();
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar o funcion�rio.\n" + "Descri��o: " + e.getMessage());
		}
	}
	
    @FXML
    private void voltar(ActionEvent event) {
    	stage.hide();
    }
}