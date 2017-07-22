package br.com.rarp.view.scnCadastroCargo;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.CargoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroCargoController extends Application implements Initializable {

	private static boolean visualizando;
	private static Stage stage;

	@FXML
	private Button btnGravar;

	@FXML
	private Button btnVoltar;

	@FXML
	private SwitchButton sbAtivado;
	
    @FXML
    private TextField edtNome;

    @FXML
    private TextField edtFuncao;

    @FXML
    private TextField edtNivel;

    @FXML
    private TextArea edtRequisitos;
	
	@FXML
	private IntegerTextField edtCodigo;
	
	private static CargoCtrl cargoCtrl;

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroCargo.fxml"))));
		stage.setTitle("Cadastro de Funcion�rios");
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
	public void alterar(CargoCtrl cargoCtrl) throws Exception {
		this.cargoCtrl = cargoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.setValue(true);
		edtCodigo.setDisable(true);

		if (cargoCtrl != null && cargoCtrl.getCargo() != null)
			preencherTela();
		
		if (visualizando)
			bloquearTela();
	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	private void limparCampos() {
		edtCodigo.clear();
		edtNome.clear();
		edtFuncao.clear();
		edtNivel.clear();
		edtRequisitos.clear();
		sbAtivado.setValue(true);
	}

	private void bloquearTela() {
		edtCodigo.setDisable(true);
		edtNome.setDisable(true);
		edtFuncao.setDisable(true);
		edtNivel.setDisable(true);
		edtRequisitos.setDisable(true);
		sbAtivado.setDisable(true);
		btnGravar.setDisable(true);
	}

	private void preencherObjeto() {
		if (cargoCtrl == null) {
			cargoCtrl = new CargoCtrl();
		}
		if (cargoCtrl.getCargo() == null) {
			cargoCtrl.novoCargo();
		}
		
		cargoCtrl.getCargo().setCodigo(edtCodigo.getValue());
		cargoCtrl.getCargo().setNome(edtNome.getText());
		cargoCtrl.getCargo().setFuncao(edtFuncao.getText());
		cargoCtrl.getCargo().setNivel(edtNivel.getText());
		cargoCtrl.getCargo().setRequisitos(edtRequisitos.getText());
		cargoCtrl.getCargo().setStatus(sbAtivado.getValue());
	}

	private void preencherTela() {
		edtCodigo.setValue(cargoCtrl.getCargo().getCodigo());
		edtNome.setText(cargoCtrl.getCargo().getNome());
		edtFuncao.setText(cargoCtrl.getCargo().getFuncao());
		edtNivel.setText(cargoCtrl.getCargo().getNivel());
		edtRequisitos.setText(cargoCtrl.getCargo().getRequisitos());
		sbAtivado.setValue(cargoCtrl.getCargo().isStatus());
	}

	@SuppressWarnings("static-access")
	public void visualizar(CargoCtrl cargoCtrl) throws Exception {
		visualizando = true;
		this.cargoCtrl = cargoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@FXML
	private void salvar() {
		preencherObjeto();
		try {
			if(cargoCtrl.salvar()) {
				Utilitarios.message("Cargo salvo com sucesso.");
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