package br.com.rarp.view.scnConsulta;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultaController extends Application implements Initializable {

	@FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;
    
    @FXML
    private TableView<?> tbvResultado;

    @FXML
    private TableColumn<?, ?> tbcData;

    @FXML
    private TableColumn<?, ?> tbcDescricao;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnImprimir;
    
    private static Stage stage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = SistemaCtrl.getInstance().getStage();
																	 
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ConsultaOnline.fxml"))));
		stage = primaryStage;
		primaryStage.showAndWait();
		
		
	}
	
	@SuppressWarnings("static-access")
	public void abrir() {
		try {
			start(this.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void volta() {
		try {
			if (stage !=  null)
					stage.hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
