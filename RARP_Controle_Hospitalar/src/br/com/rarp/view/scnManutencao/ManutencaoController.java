package br.com.rarp.view.scnManutencao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.interfaces.Manutencao;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Ativado;
import br.com.rarp.utils.comparacao.Contem;
import br.com.rarp.utils.comparacao.Igual;
import br.com.rarp.utils.comparacao.Iniciado;
import br.com.rarp.utils.comparacao.Maior;
import br.com.rarp.utils.comparacao.Menor;
import br.com.rarp.utils.comparacao.Terminado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public abstract class ManutencaoController implements Initializable, Manutencao {
	
	private Node node;

	@SuppressWarnings("rawtypes")
	@FXML protected TableView tvManutencao;
	
	@FXML private Button btnPesquisar;
	@FXML private Button btnInserir;
	@FXML private Button btnAlterar;
	@FXML private Button btnVisualizar;
	@FXML private Label lblTitle;
    @FXML protected ComboBox<Campo> cmbCampo;
    @FXML protected ComboBox<Comparacao> cmbComparacao;
    @FXML protected TextField edtTermo;
    @FXML protected ComboBox<String> cmbTermo;
	

	public ManutencaoController() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Manutencao.fxml"));
		loader.setController(this);
		try {
			node = loader.load();
			
			node.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(newValue) {
						cmbCampo.requestFocus();
					}
				}
			});
			
			node.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.ESCAPE)
						voltar();
					}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			prepararTela();
		} catch (Exception e) {
			Utilitarios.atencao(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML 
	private void cmbCampoChange(ActionEvent event) {
		cmbTermo = (ComboBox<String>) Utilitarios.getNodeById(cmbCampo.getParent(), "cmbTermo");
		edtTermo = (TextField) Utilitarios.getNodeById(cmbCampo.getParent(), "edtTermo");
		cmbComparacao = (ComboBox<Comparacao>) Utilitarios.getNodeById(cmbCampo.getParent(), "cmbComparacao");
		
		if(cmbCampo.getSelectionModel().getSelectedItem() != null
				&& cmbTermo != null && edtTermo != null 
				&& cmbComparacao != null) {
			switch (cmbCampo.getSelectionModel().getSelectedItem().getTipo()) {
			case texto: 
				cmbTermo.setVisible(false);
				edtTermo.setVisible(true);
				cmbComparacao.setItems(FXCollections.observableArrayList(
						new Igual(), 
						new Contem(), 
						new Iniciado(), 
						new Terminado()));
				cmbComparacao.getSelectionModel().select(0);
				break;
			case numerico:
				cmbTermo.setVisible(false);
				edtTermo.setVisible(true);
				cmbComparacao.setItems(FXCollections.observableArrayList(
						new Igual(), 
						new Contem(), 
						new Iniciado(), 
						new Terminado(),
						new Menor(),
						new Maior()));
				cmbComparacao.getSelectionModel().select(0);
				break;
			case booleano: 
				cmbComparacao.getItems().clear();
				cmbComparacao.getItems().add(new Ativado());
				cmbComparacao.getSelectionModel().select(0);
				cmbTermo.setVisible(true);
				edtTermo.setVisible(false);
				cmbTermo.setItems(FXCollections.observableArrayList("Ativado", "Desativado"));
				break;
			}
		}
	}

	@Override
	public void voltar() {
		((BorderPane) node.getParent()).setCenter(null);
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Button getBtnPesquisar() {
		return btnPesquisar;
	}

	public void setBtnPesquisar(Button btnPesquisar) {
		this.btnPesquisar = btnPesquisar;
	}

	public Button getBtnInserir() {
		return btnInserir;
	}

	public void setBtnInserir(Button btnInserir) {
		this.btnInserir = btnInserir;
	}

	public Button getBtnAlterar() {
		return btnAlterar;
	}

	public void setBtnAlterar(Button btnAlterar) {
		this.btnAlterar = btnAlterar;
	}

	public Button getBtnVisualizar() {
		return btnVisualizar;
	}

	public void setBtnVisualizar(Button btnVisualizar) {
		this.btnVisualizar = btnVisualizar;
	}
	
	public Label getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(Label lblTitle) {
		this.lblTitle = lblTitle;
	}
}
