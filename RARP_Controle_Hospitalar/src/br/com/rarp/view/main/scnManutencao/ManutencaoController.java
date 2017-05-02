package br.com.rarp.view.main.scnManutencao;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.annotations.Coluna;
import br.com.rarp.annotations.Objeto;
import br.com.rarp.utils.Utilitarios;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ManutencaoController extends Application implements Initializable {

	private FXMLLoader loader;
	private static Stage stage;
	private Class classe;
	@FXML private TableView tvMovimentacao;
	
	public ManutencaoController(Class classe) {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Manutencao.fxml"));
		loader.setController(this);
		this.classe = classe;
	}
	
	private void configurarTela() {
		for(Field f: classe.getDeclaredFields()) {
			if (f.getDeclaredAnnotation(Objeto.class) != null) {
				for(Field c: Utilitarios.getColunas(f.getType())) {
					TableColumn tc = new TableColumn<>(c.getDeclaredAnnotation(Coluna.class).descricao());
					tvMovimentacao.getColumns().add(tc);
				}
			}
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(loader.load()));
		this.setStage(stage);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurarTela();	
	}

}
