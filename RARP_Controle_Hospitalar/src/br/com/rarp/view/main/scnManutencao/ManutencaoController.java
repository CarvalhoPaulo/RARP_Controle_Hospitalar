package br.com.rarp.view.main.scnManutencao;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.interfaces.Manutencao;
import br.com.rarp.model.EntradaPaciente;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public abstract class ManutencaoController extends Application implements Initializable, Manutencao {

	private FXMLLoader loader;
	private Stage stage;
	@FXML private TableView<EntradaPaciente> tvManutencao;
	
	@FXML private Button btnPesquisar;
	@FXML private Button btnInserir;
	@FXML private Button btnAlterar;
	@FXML private Button btnVisualizar;
	
	public ManutencaoController() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Manutencao.fxml"));
		loader.setController(this);
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
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(loader.load()));
		this.setStage(stage);
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

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public TableView<EntradaPaciente> getTvManutencao() {
		return tvManutencao;
	}

	public void setTvManutencao(TableView<EntradaPaciente> tvManutencao) {
		this.tvManutencao = tvManutencao;
	}

}
