package br.com.rarp.view.scnCadastroEspaco;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EspacoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Leito;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.ImageCard;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CadastroEspacoController extends Application implements Initializable {

	private static Stage stage;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVoltar;

	@FXML
	private IntegerTextField edtCodigo;


    @FXML
    private TextField edtNome;

	@FXML
	private SwitchButton sbAtivado;

	@FXML
	private IntegerTextField edtNumero;

	@FXML
	private TextField edtBloco;

	@FXML
	private TextField edtAndar;

	@FXML
	private IntegerTextField edtNumeroLeito;

	@FXML
	private Button btnAdicionar;

	@FXML
	private Button btnRemover;

	@FXML
	private ScrollPane sclLeitos;

	@FXML
	private FlowPane pnlLeitos;

	private static EspacoCtrl espacoCtrl;
	private static boolean visualizando;

	@FXML
	private void adicionar(ActionEvent event) {
		ImageCard imageCard = new ImageCard();
		imageCard.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
		imageCard.setLeito(new Leito(Utilitarios.strToInt(edtNumeroLeito.getText())));
		boolean existe = false;
		for (Node n : pnlLeitos.getChildren()) {
			if (n.equals(imageCard)) {
				Utilitarios.atencao("Este leito ja existe");
				existe = true;
			}
		}
		if (!existe)
			pnlLeitos.getChildren().add(imageCard);
		edtNumeroLeito.setValue(edtNumeroLeito.getValue() + 1);
	}

	@FXML
	private void remover(ActionEvent event) {
		ImageCard imageCard = new ImageCard();
		imageCard.setLeito(new Leito(Utilitarios.strToInt(edtNumeroLeito.getText())));
		if(pnlLeitos.getChildren().contains(imageCard))
			pnlLeitos.getChildren().remove(pnlLeitos.getChildren().indexOf(imageCard));
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEspaco.fxml"))));
		stage.setTitle("Cadastro de Usu�rios");
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

	public void setStage(Stage stage) {
		CadastroEspacoController.stage = stage;
	}

	private void limparCampos() {
		edtCodigo.clear();
		edtNumeroLeito.clear();
		edtNome.clear();
		edtAndar.clear();
		edtBloco.clear();
		pnlLeitos.getChildren().clear();
		sbAtivado.switchOnProperty().set(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.switchOnProperty().set(true);
		edtCodigo.setDisable(true);
		if (espacoCtrl != null && espacoCtrl.getClass() != null)
			preencherTela();
		if (visualizando)
			bloquearTela();
		pnlLeitos.prefWidthProperty().bind(sclLeitos.prefWidthProperty());
		pnlLeitos.prefHeightProperty().bind(sclLeitos.prefHeightProperty());
	}

	private void bloquearTela() {
		edtCodigo.setDisable(true);
		edtAndar.setDisable(true);
		edtNome.setDisable(true);
		edtBloco.setDisable(true);
		edtNumeroLeito.setDisable(true);
		sbAtivado.setDisable(true);
		btnSalvar.setDisable(true);
		sbAtivado.setDisable(true);
		btnAdicionar.setDisable(true);
		btnRemover.setDisable(true);
	}

	public void inserir() throws Exception {
		espacoCtrl = null;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void alterar(EspacoCtrl espacoCtrl) throws Exception {
		this.espacoCtrl = espacoCtrl;
		visualizando = false;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@SuppressWarnings("static-access")
	public void visualizar(EspacoCtrl espacoCtrl) throws Exception {
		visualizando = true;
		this.espacoCtrl = espacoCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.showAndWait();
	}

	@FXML
	private void salvar(ActionEvent event) {
		preencherObjeto();
		try {
			espacoCtrl.salvar();
			Utilitarios.message("Espa�o salvo com sucesso.");
			limparCampos();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar espa�o.\n"
						   + "Descri��o: " + e.getMessage());
		}
	}

	@FXML
	private void voltar(ActionEvent event) {
		stage.hide();
	}

	private void preencherObjeto() {
		if (espacoCtrl == null)
			espacoCtrl = new EspacoCtrl();

		if (espacoCtrl.getEspaco() == null)
			espacoCtrl.novoEspaco();

		espacoCtrl.getEspaco().setCodigo(edtCodigo.getValue());
		espacoCtrl.getEspaco().setAndar(edtAndar.getText());
		espacoCtrl.getEspaco().setNome(edtNome.getText());
		espacoCtrl.getEspaco().setBloco(edtBloco.getText());
		for(Leito l: espacoCtrl.getEspaco().getLeitos())
			l.setStatus(false);
		
		for(Node n: pnlLeitos.getChildren()) {
			if(espacoCtrl.getEspaco().getLeitos().contains(((ImageCard) n).getLeito()))
				espacoCtrl.getEspaco().getLeitos().get(espacoCtrl.getEspaco().getLeitos().indexOf(((ImageCard) n).getLeito())).setStatus(true);
			else
				espacoCtrl.getEspaco().getLeitos().add(((ImageCard) n).getLeito());
		}
		espacoCtrl.getEspaco().setStatus(sbAtivado.switchOnProperty().get());
	}

	private void preencherTela() {
		edtCodigo.setText(espacoCtrl.getEspaco().getCodigo() + "");
		edtNome.setText(espacoCtrl.getEspaco().getNome());
		edtBloco.setText(espacoCtrl.getEspaco().getBloco());
		edtAndar.setText(espacoCtrl.getEspaco().getAndar());

		for (Leito l : espacoCtrl.getEspaco().getLeitos()) {
			ImageCard img = new ImageCard();
			img.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
			if(l.isStatus()) {
				img.setLeito(l);
				pnlLeitos.getChildren().add(img);
			}
		}

		sbAtivado.switchOnProperty().set(espacoCtrl.getEspaco().isStatus());
	}

}
