package br.com.rarp.view.scnCadastroEncaminhamento;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.EncaminhamentoCtrl;
import br.com.rarp.control.SistemaCtrl;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CadastroEncaminhamentoController extends Application implements Initializable {
	
	private static Stage stage;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVoltar;

	@FXML
	private IntegerTextField txtCodigo;
	
	@FXML
	private SwitchButton sbAtivado;

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
		stage.setResizable(false);
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

		sbAtivado.switchOnProperty().set(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.switchOnProperty().set(true);
		txtCodigo.setDisable(true);
		if (encaminhamentoCtrl != null && encaminhamentoCtrl.getEncaminhamento() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
	}

	private void bloquearTela() {
		txtCodigo.setDisable(true);
		sbAtivado.setDisable(true);
		btnSalvar.setDisable(true);
		sbAtivado.setDisable(true);
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

		encaminhamentoCtrl.getEncaminhamento().setCodigo(txtCodigo.getValue());
		encaminhamentoCtrl.getEncaminhamento().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		txtCodigo.setText(encaminhamentoCtrl.getEncaminhamento().getCodigo() + "");

		sbAtivado.setValue(encaminhamentoCtrl.getEncaminhamento().isStatus());
	}

}
