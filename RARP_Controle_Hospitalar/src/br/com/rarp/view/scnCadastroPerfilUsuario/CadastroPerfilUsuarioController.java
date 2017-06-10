package br.com.rarp.view.scnCadastroPerfilUsuario;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rarp.control.PerfilUsuarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Tela;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.SwitchButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroPerfilUsuarioController extends Application implements Initializable {
	@FXML private TextField edtCodigo;
	@FXML private TextField edtNome;
	@FXML private ListView<Tela> lvTelas;
	@FXML private ListView<Tela> lvTelasPermitidas;
	@FXML private CheckBox ckbInserir;
	@FXML private CheckBox ckbAlterar;
	@FXML private CheckBox ckbVisualizar;
	@FXML private CheckBox ckbDesativar;
	@FXML private Button btnSelectAll;
	@FXML private Button btnRemove;
	@FXML private Button btnRemoveAll;
	@FXML private Button btnAdd;
	@FXML private Button btnAddAll;
	@FXML private SwitchButton sbAtivado;
	
	private static PerfilUsuarioCtrl perfilUsuarioCtrl;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CadastroPerfilUsuario.fxml"))));
		stage.setTitle("Cadastro de Perfil de Usuário");
		setStage(stage);
	}
	
	public static void setStage(Stage stage) {
		CadastroPerfilUsuarioController.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}
	
	@SuppressWarnings("static-access")
	public void alterar(PerfilUsuarioCtrl perfilUsuarioCtrl) throws Exception {
		this.perfilUsuarioCtrl = perfilUsuarioCtrl;
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adicionarTelas();
		
		sbAtivado.switchOnProperty().set(true);
		if(perfilUsuarioCtrl != null)
			preencheTela();
		
		lvTelas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvTelasPermitidas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvTelasPermitidas.getSelectionModel().getSelectedItems().addListener(changeListener);
	}
	
	ListChangeListener<Tela> changeListener = new ListChangeListener<Tela>() {
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends Tela> c) {
			lvTelasSelecionadas();
		}
	};
	
	private void adicionarTelas() {
		List<Tela> telas = new ArrayList<>();
		
		telas.add(new Tela("manutencaoUsuario", "Manutenção de Usuários"));
		telas.add(new Tela("manutencaoPerfilUsuario", "Manutenção de Perfil de Usuário"));
		telas.add(new Tela("manutencaoEntradaPaciente", "Manutenção de Entrada de Paciente"));
		lvTelas.setItems(FXCollections.observableArrayList(telas));
	}

	public void inserir() throws Exception {
		start(SistemaCtrl.getInstance().getStage());
		stage.setResizable(false);
		stage.showAndWait();		
	}
	
	@FXML
	private void selectAll(ActionEvent event) {
		lvTelasPermitidas.getSelectionModel().selectAll();
	}
	
	@FXML
	private void addAll(ActionEvent event) {
		lvTelasPermitidas.getItems().addAll(lvTelas.getItems());
		lvTelas.getItems().clear();
	}
	
	@FXML
	private void removeAll(ActionEvent event) {
		lvTelas.getItems().addAll(lvTelasPermitidas.getItems());
		lvTelasPermitidas.getItems().clear();
	}
	
	@FXML
	private void add(ActionEvent event) {
		lvTelasPermitidas.getItems().addAll(lvTelas.getSelectionModel().getSelectedItems());
		lvTelas.getItems().removeAll(lvTelas.getSelectionModel().getSelectedItems());
	}
	
	@FXML
	private void remove(ActionEvent event) {
		lvTelas.getItems().addAll(lvTelasPermitidas.getSelectionModel().getSelectedItems());
		lvTelasPermitidas.getItems().removeAll(lvTelasPermitidas.getSelectionModel().getSelectedItems());
	}
	
	@FXML
	private void salvar() {
		preencheObjeto();
		try {
			perfilUsuarioCtrl.salvar();
			Utilitarios.message("Perfil de usuário salvo com sucesso.");
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
						   + "Descrição: " + e.getMessage());
		}
	}
	
	private void preencheObjeto() {
		if (perfilUsuarioCtrl == null)
			perfilUsuarioCtrl = new PerfilUsuarioCtrl();
		
		if (perfilUsuarioCtrl.getPerfilUsuario() == null)
			perfilUsuarioCtrl.novoPerfilUsuario();
		
		perfilUsuarioCtrl.getPerfilUsuario().setNome(edtNome.getText());
		
		//Adiciona telas permitidas
		for(Tela tela: lvTelasPermitidas.getItems()) {
			tela.setStatus(true);
			perfilUsuarioCtrl.getPerfilUsuario().getTelas().add(tela);
		}
		//Adiciona telas não permitidas
		for(Tela tela: lvTelas.getItems()) {
			tela.setStatus(false);
			tela.setPodeAlterar(false);
			tela.setPodeInserir(false);
			tela.setPodeVisualizar(false);
			tela.setPodeDesativar(false);
			perfilUsuarioCtrl.getPerfilUsuario().getTelas().add(tela);
		}
		perfilUsuarioCtrl.getPerfilUsuario().setStatus(sbAtivado.switchOnProperty().get());
	}
	
	@FXML
	private void lvTelasSelecionadas() {
		if(lvTelasPermitidas.getSelectionModel().getSelectedItem() != null)
			ckbInserir.setSelected(lvTelasPermitidas.getSelectionModel().getSelectedItem().isPodeInserir());
		if(lvTelasPermitidas.getSelectionModel().getSelectedItem() != null)
			ckbAlterar.setSelected(lvTelasPermitidas.getSelectionModel().getSelectedItem().isPodeAlterar());
		if(lvTelasPermitidas.getSelectionModel().getSelectedItem() != null)
			ckbVisualizar.setSelected(lvTelasPermitidas.getSelectionModel().getSelectedItem().isPodeVisualizar());
		if(lvTelasPermitidas.getSelectionModel().getSelectedItem() != null)
			ckbDesativar.setSelected(lvTelasPermitidas.getSelectionModel().getSelectedItem().isPodeDesativar());
	}
	
	@FXML
	private void ckbClick(ActionEvent event) {
		for(Tela tela: lvTelasPermitidas.getSelectionModel().getSelectedItems()) { 
			tela.setPodeInserir(ckbInserir.isSelected());
			tela.setPodeAlterar(ckbAlterar.isSelected());
			tela.setPodeVisualizar(ckbVisualizar.isSelected());
			tela.setPodeDesativar(ckbDesativar.isSelected());
		}	
	}
	
	@FXML
	private void voltar() {
		stage.hide();
	}

	private void preencheTela() {
		edtNome.setText(perfilUsuarioCtrl.getPerfilUsuario().getNome());
		edtCodigo.setText(perfilUsuarioCtrl.getPerfilUsuario().getCodigo() + "");
		for(Tela tela: perfilUsuarioCtrl.getPerfilUsuario().getTelas()){
			if(lvTelas.getItems().contains(tela))
				if(tela.isStatus()) {
					lvTelas.getItems().set(lvTelas.getItems().indexOf(tela), tela);
					lvTelas.getSelectionModel().select(tela);
					add(new ActionEvent());
				} else {
					lvTelas.getItems().set(lvTelas.getItems().indexOf(tela), tela);
				}
		}
	}

}
