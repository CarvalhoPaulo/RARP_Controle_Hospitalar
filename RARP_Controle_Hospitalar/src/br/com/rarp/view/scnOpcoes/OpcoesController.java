package br.com.rarp.view.scnOpcoes;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Configuracoes;
import br.com.rarp.model.Organizacao;
import br.com.rarp.model.Telefone;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnComponents.IntegerTextField;
import br.com.rarp.view.scnComponents.MaskTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpcoesController extends Application implements Initializable {

	@FXML // fx:id="txtRazao"
	private TextField txtRazao; // Value injected by FXMLLoader

	@FXML // fx:id="txtCNPJ"
	private MaskTextField txtCNPJ; // Value injected by FXMLLoader

	@FXML // fx:id="txtEndereco"
	private TextField txtEndereco; // Value injected by FXMLLoader

	@FXML // fx:id="txtFone"
	private MaskTextField txtFone; // Value injected by FXMLLoader

	@FXML // fx:id="txtEmail"
	private MaskTextField txtEmail; // Value injected by FXMLLoader

	@FXML // fx:id="txtUsuario"
	private TextField txtUsuario; // Value injected by FXMLLoader

	@FXML // fx:id="txtSenha"
	private PasswordField txtSenha; // Value injected by FXMLLoader

	@FXML // fx:id="btnAplicar"
	private Button btnAplicar; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancelar"
	private Button btnCancelar; // Value injected by FXMLLoader
	
	@FXML // fx:id="txtCodigoRARP"
    private IntegerTextField txtCodigoRARP; // Value injected by FXMLLoader

	private Stage stage;

	   @FXML // fx:id="tblTelefones"
	    private TableView<Telefone> tblTelefones; // Value injected by FXMLLoader

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		try {
			preparaTela();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void start(Stage pstage) throws Exception {
		// TODO Auto-generated method stub
		stage = pstage;
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Opcoes.fxml"))));
		stage.setResizable(false);
		stage.showAndWait();
	}

	@FXML
	private void gravar(ActionEvent e) {

		try {
			preencherConfiguracoes();
			SistemaCtrl.getInstance().salvarConfiguracoes();
			stage.hide();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			Utilitarios.erro(e1.toString());
		}

	}

	@FXML
	private void voltar(ActionEvent e) {
		stage.hide();
	}

	public void preparaTela() {

		Organizacao empresa = SistemaCtrl.getInstance().getOrganizacao();

		txtRazao.setText(empresa.getRazaoSocial());

		txtCNPJ.setText(empresa.getCnpj());

		txtEndereco.setText(empresa.getLogradouro());

	//	txtFone.setText(empresa.getT);

		txtEmail.setText(empresa.getEmail());

		Configuracoes configuracoes = SistemaCtrl.getInstance().getConfiguracoes();

		txtUsuario.setText(configuracoes.getUsuario());

		txtSenha.setText(configuracoes.getSenha());
		txtCodigoRARP.setText(String.valueOf(configuracoes.getCodigoRARP()));
	}

	private void preencherConfiguracoes() {
		SistemaCtrl.getInstance().getConfiguracoes().setSenha(txtSenha.getText());
		SistemaCtrl.getInstance().getConfiguracoes().setUsuario(txtUsuario.getText());
		SistemaCtrl.getInstance().getConfiguracoes().setCodigoRARP(txtCodigoRARP.getValue());

	}

	private void PreencherEmpresa() {
		SistemaCtrl.getInstance().getOrganizacao().setCnpj(txtCNPJ.getText());
		SistemaCtrl.getInstance().getOrganizacao().setEmail(txtEmail.getText());
		SistemaCtrl.getInstance().getOrganizacao().setLogradouro(txtEndereco.getText());
		SistemaCtrl.getInstance().getOrganizacao().setRazaoSocial(txtRazao.getText());
		SistemaCtrl.getInstance().getOrganizacao().setTelefones(tblTelefones.getItems());
		//SistemaCtrl.getInstance().getEmpresa().setTelefone(txtFone.getText());
	}
}
