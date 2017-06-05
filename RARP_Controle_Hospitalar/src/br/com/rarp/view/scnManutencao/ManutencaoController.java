package br.com.rarp.view.scnManutencao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.interfaces.Manutencao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public abstract class ManutencaoController implements Initializable, Manutencao {
	
	private Node node;

	@SuppressWarnings("rawtypes")
	@FXML private TableView tvManutencao;
	
	@FXML private Button btnPesquisar;
	@FXML private Button btnInserir;
	@FXML private Button btnAlterar;
	@FXML private Button btnVisualizar;
	@FXML private Label lblTitle;

	public ManutencaoController() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Manutencao.fxml"));
		loader.setController(this);
		try {
			node = loader.load();
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
			e.printStackTrace();
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

	public TableView getTvManutencao() {
		return tvManutencao;
	}

	public void setTvManutencao(TableView tvManutencao) {
		this.tvManutencao = tvManutencao;
	}

}
