package br.com.rarp.view.scnConsulta;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.com.rarp.interfaces.Consulta;
import org.com.rarp.interfaces.Exception_Exception;
import org.com.rarp.interfaces.Pessoa;
import org.com.rarp.interfaces.PessoaFisica;
import org.com.rarp.interfaces.Requisicao;
import org.com.rarp.interfaces.Resposta;
import org.com.rarp.soap.ConsultaSOAP;
import org.w3c.dom.ls.LSInput;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.AutoCompleteComboBox;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.TextFieldFormatter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ConsultaController extends Application implements Initializable {


    @FXML // fx:id="txtPesquisa"
    private TextFieldFormatter  txtPesquisa; // Value injected by FXMLLoader

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
    
    @FXML
    private AutoCompleteComboBox<String> cmbTipoDocumento;
    
    @FXML // fx:id="txtSerie"
    private IntegerTextField txtSerie; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumerecao"
    private IntegerTextField txtNumerecao; // Value injected by FXMLLoader
     
    private static Stage stage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		
		list.add("CPF");
		list.add("RG");
		list.add("SUS");
		
		cmbTipoDocumento.setItems(FXCollections.observableList(list));
		tipoDocumentoChange();
		
	
	}

	@Override
	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage = SistemaCtrl.getInstance().getStage();
																	 
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ConsultaOnline.fxml"))));
		stage = primaryStage;
		primaryStage.setMaximized(true);
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
	private void consultar() {
		
		try {
			ConsultaSOAP consultaSOAP = new ConsultaSOAP();
			Consulta consulta = consultaSOAP.getConsultaSOAPPort();
			consulta.sevidorOn(SistemaCtrl.getInstance().getConfiguracoes().getUsuarioRARP());
			PessoaFisica pessoaFisica = new PessoaFisica();
			if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 0) {
				pessoaFisica.setCep(txtPesquisa.getText());
			}
			if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 1) {
				pessoaFisica.setRg(txtPesquisa.getText());
			}
			if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 2) {
				pessoaFisica.setSUS(txtPesquisa.getText());
			}
			
			Requisicao requisicao = new Requisicao();
			requisicao.setPessoaFisica(pessoaFisica);
			requisicao.setSerie(txtSerie.getValue());
			requisicao.setNumeracao(txtNumerecao.getValue());
			List<Resposta>  list = consulta.consultar(requisicao);
			
			if ((list != null) && (list.size() > 0)) {
				Utilitarios.message("soufoda");
			}
			
			
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			Utilitarios.atencao("Falha ao Consultar "+e.getMessage());
		}
		
		
	}
	
	@FXML
	private void tipoDocumentoChange() {
		 cmbTipoDocumento.valueProperty().addListener(new ChangeListener<String>() {
		        public void changed(ObservableValue ov, String t, String t1) {
		        	txtPesquisa.setText("");
		    		if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 0) {
		    			txtPesquisa.setMask("###.###.###-##");
		    		}
		    		if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 1) {
		    			txtPesquisa.setMask("AAAAAAAAAAAAAAA");
		    		}
		    		if ( cmbTipoDocumento.getSelectionModel().getSelectedIndex() == 2) {
		    			txtPesquisa.setMask("############/####-#");
		    		}
		        }    
		    });
		
		
	}
	
	@FXML
	private void volta() {
		try {
			if (stage !=  null)
					stage.hide();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}


	
}
