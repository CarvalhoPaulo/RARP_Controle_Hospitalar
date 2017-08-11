package br.com.rarp.view.scnCadastroEspecialidade;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.EspecialidadeCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroEspecialidadeController extends Application implements Initializable{
	private static boolean visualizando;
	private static Stage stage;
	
	private static EspecialidadeCtrl especialidadeCtrl;
	
    @FXML // fx:id="txtCodigo"
    private IntegerTextField txtCodigo; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtObservacoes"
    private TextArea txtObservacoes; // Value injected by FXMLLoader

    @FXML // fx:id="sbtStatus"
    private SwitchButton sbtStatus; // Value injected by FXMLLoader

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroEspecialidade.fxml"))));
		primaryStage.setTitle("Cadastro de Especialidades");
		this.stage = primaryStage;
	}
	public static boolean isVisualizando() {
		return visualizando;
	}
	public static void setVisualizando(boolean visualizando) {
		CadastroEspecialidadeController.visualizando = visualizando;
	}
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		CadastroEspecialidadeController.stage = stage;
	}
	
	  private void altera(EspecialidadeCtrl ctrl) throws Exception {
		// TODO Auto-generated method stub
		
		this.especialidadeCtrl = ctrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	  private void PreencherObjeto() {
		  if (this.especialidadeCtrl == null ) {
			  this.especialidadeCtrl = new EspecialidadeCtrl(); 
			  this.especialidadeCtrl.novaEspecialidade(); 
		  }else {
			  this.especialidadeCtrl.novaEspecialidade();
		  }
			  
		  
		  
		  this.especialidadeCtrl.getEspecialida().setNome(txtNome.getText());
		  this.especialidadeCtrl.getEspecialida().setObservacoes(txtObservacoes.getText());
		  this.especialidadeCtrl.getEspecialida().setCodigo(txtCodigo.getValue());
		  this.especialidadeCtrl.getEspecialida().setNome(txtNome.getText());
	  }
	
	@FXML
	private void OkAction(Event e) throws Exception {
		try {
			PreencherObjeto();
			especialidadeCtrl.salvar();
			Utilitarios.atencao("Especialidade Inserida Com Sucesso");
		}catch(Exception e2){
			Utilitarios.erro(e2.getMessage());
		}
			
		
	
		
	}

}
