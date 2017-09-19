package br.com.rarp.view.scnCadastroAtendimento;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.AtendimentoCtrl;
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

public class CadastroAtendimentoController extends Application implements Initializable {

	private static Stage stage;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVoltar;

	@FXML
	private IntegerTextField txtCodigo;
	
	@FXML
	private SwitchButton sbAtivado;

	private static AtendimentoCtrl atendimentoCtrl;
	private static boolean visualizando;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroAtendimento.fxml"))));
		stage.setTitle("Cadastro de Atendimentos");
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
		if (atendimentoCtrl != null && atendimentoCtrl.getAtendimento() != null)
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
		atendimentoCtrl = null;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(AtendimentoCtrl atendimentoCtrl) throws Exception {
		this.atendimentoCtrl = atendimentoCtrl;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void visualizar(AtendimentoCtrl atendimentoCtrl) throws Exception {
		visualizando = true;
		this.atendimentoCtrl = atendimentoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@FXML
	private void salvar(ActionEvent event) {
		preencherObjeto();
		try {
			if(atendimentoCtrl.salvar()) {
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
		atendimentoCtrl = null;
		stage.hide();
		visualizando = false;
	}

	private void preencherObjeto() {
		if (atendimentoCtrl == null)
			atendimentoCtrl = new AtendimentoCtrl();

		if (atendimentoCtrl.getAtendimento() == null)
			atendimentoCtrl.novoAtendimento();

		atendimentoCtrl.getAtendimento().setCodigo(txtCodigo.getValue());
		atendimentoCtrl.getAtendimento().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		txtCodigo.setText(atendimentoCtrl.getAtendimento().getCodigo() + "");

		sbAtivado.setValue(atendimentoCtrl.getAtendimento().isStatus());
	}
}
