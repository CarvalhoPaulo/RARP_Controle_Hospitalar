package br.com.rarp.view.scnCadastroEspaco;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.control.EspacoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Leito;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.ImageCard;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CadastroEspacoController extends Application implements Initializable {

	private static Stage stage;
	
    @FXML private Button btnGravar;
    @FXML private Button btnVoltar;
    @FXML private TextField edtCodigo;
    @FXML private SwitchButton sbAtivado;
	@FXML private TextField edtNumeroLeito;
	@FXML private Button btnAdicionar;
	@FXML private Button btnRemover;
    @FXML private FlowPane pnlLeitos;
    @FXML private ScrollPane sclLeitos;
    
	private static EspacoCtrl espacoCtrl;
	private static boolean visualizando;

	@FXML
	private void adicionar(ActionEvent event) {
		ImageCard imageCard = new ImageCard();
		imageCard.getLabel().set(edtNumeroLeito.getText());
		imageCard.getPathImage().set(getClass().getResource("../img/patient128x128.png").toString());
		imageCard.setLeito(new Leito(Utilitarios.strToInt(edtNumeroLeito.getText())));
		boolean existe = false;
		for(Node n: pnlLeitos.getChildren()) {
			if(n.equals(imageCard)) {
				Utilitarios.atencao("Este leito ja existe");
				existe = true;
			}
		}
		if(!existe)
			pnlLeitos.getChildren().add(imageCard);
	}

	@FXML
	private void remover(ActionEvent event) {

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEspaco.fxml"))));
		stage.setTitle("Cadastro de Usuários");
		setStage(stage);
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
		sbAtivado.switchOnProperty().set(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sbAtivado.switchOnProperty().set(true);
		edtCodigo.setDisable(true);
		if(espacoCtrl != null && espacoCtrl.getClass() != null)
			preencheTela();
		if(visualizando)
			bloquearTela();
		pnlLeitos.prefWidthProperty().bind(sclLeitos.prefWidthProperty());
		pnlLeitos.prefHeightProperty().bind(sclLeitos.prefHeightProperty());
	}
	
	private void bloquearTela() {
		edtCodigo.setDisable(true);
		sbAtivado.setDisable(true);
		btnGravar.setDisable(true);
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
    private void gravar(ActionEvent event) {
    	preencherObjeto();
		try {
			espacoCtrl.salvar();
			Utilitarios.message("Usuário salvo com sucesso.");
			limparCampos();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
						   + "Descrição: " + e.getMessage());
		}
    }
    
	@FXML
    private void voltar(ActionEvent event) {
    	stage.hide();
    }

    private void preencherObjeto() {
		if(espacoCtrl == null)
			espacoCtrl = new EspacoCtrl();
		
		if(espacoCtrl.getEspaco() == null)
			espacoCtrl.novoEspaco();
		
		espacoCtrl.getEspaco().setCodigo(Utilitarios.strToInt(edtCodigo.getText()));
		espacoCtrl.getEspaco().setStatus(sbAtivado.switchOnProperty().get());
	}
    
	private void preencheTela() {
		edtCodigo.setText(espacoCtrl.getEspaco().getCodigo() + "");
		sbAtivado.switchOnProperty().set(espacoCtrl.getEspaco().isStatus());
	}

}
