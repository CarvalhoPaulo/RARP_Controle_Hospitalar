package br.com.rarp.view.scnCadastroCidade;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.CidadeCtrl;
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

public class CadastroCidadeController extends Application implements Initializable {

	private static boolean visualizando;
	
	private static Stage stage;

	@FXML
	private IntegerTextField edtCodigo;

	private static CidadeCtrl cidadeCtrl;

	@FXML
	private SwitchButton sbAtivado;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroFuncionario.fxml"))));
		stage.setTitle("Cadastro de Funcionários");
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
		sbAtivado.setValue(true);
	}

	private void bloquearTela() {
		edtCodigo.setDisable(true);
		sbAtivado.setDisable(true);
	}

	private void preencherObjeto() {
		if (cidadeCtrl == null) {
			cidadeCtrl = new CidadeCtrl();
		}
		if (cidadeCtrl.getCidade() == null) {
			cidadeCtrl.novaCidade();
		}
		cidadeCtrl.getCidade().setCodigo(edtCodigo.getValue());
		cidadeCtrl.getCidade().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		edtCodigo.setText(cidadeCtrl.getCidade().getCodigo() + "");
		sbAtivado.setValue(cidadeCtrl.getCidade().isStatus());
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