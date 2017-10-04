package br.com.rarp.view.scnConsulta;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
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


    @FXML // fx:id="txtPesquisa"
    private TextField txtPesquisa; // Value injected by FXMLLoader

    @FXML // fx:id="btnPesquisar"
    private Button btnPesquisar; // Value injected by FXMLLoader

    @FXML // fx:id="tbvResultado"
    private TableView<?> tbvResultado; // Value injected by FXMLLoader

    @FXML // fx:id="tbcData"
    private TableColumn<?, ?> tbcData; // Value injected by FXMLLoader

    @FXML // fx:id="tbcDescricao"
    private TableColumn<?, ?> tbcDescricao; // Value injected by FXMLLoader
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnImprimir;
    
    private static Stage stage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage = SistemaCtrl.getInstance().getStage();
																	 
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ConsultaOnline.fxml"))));
		stage = primaryStage;
		primaryStage.showAndWait();
		
		
	}
	
	public void abrir() {
		try {
			start(this.stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void volta() {
		try {
			if (stage !=  null)
					stage.hide();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
