package br.com.rarp.view.scnManutencao.perfilUsuario;

import br.com.rarp.control.PerfilUsuarioCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroPerfilUsuario.CadastroPerfilUsuarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PerfilUsuarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manuten��o de Perfil de Usu�rio");
		getLblTitle().setStyle("-fx-background-color: #F34227;"
				+ "-fx-font-weight: bold");
		
		TableColumn<PerfilUsuario, String> codigo = new TableColumn<>("C�digo");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		codigo.setPrefWidth(100);
		
		TableColumn<PerfilUsuario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		nome.setPrefWidth(500);
		
		tvManutencao.getColumns().addAll(codigo, nome);
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());
	}

	private void adicionarCampos() {
		cmbCampo.getItems().add(new Campo("codigo", "C�digo", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("nome", "Nome", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		PerfilUsuarioCtrl perfilUsuarioCtrl = new PerfilUsuarioCtrl();
		try {
			tvManutencao.setItems(perfilUsuarioCtrl.consultar(
					cmbCampo.getSelectionModel().getSelectedItem(), 
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue() : edtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
					   + "Descri��o: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			CadastroPerfilUsuarioController controller = new CadastroPerfilUsuarioController();
			controller.inserir();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes\n"
					   + "Descri��o: " + e.getMessage());
		}
	}

	@Override
	public void alterar() {
		try {
			CadastroPerfilUsuarioController controller = new CadastroPerfilUsuarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				PerfilUsuarioCtrl perfilUsuarioCtrl = new PerfilUsuarioCtrl();
				perfilUsuarioCtrl.setPerfilUsuario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(perfilUsuarioCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes");
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		try {
			CadastroPerfilUsuarioController controller = new CadastroPerfilUsuarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				PerfilUsuarioCtrl perfilUsuarioCtrl = new PerfilUsuarioCtrl();
				perfilUsuarioCtrl.setPerfilUsuario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(perfilUsuarioCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes");
			e.printStackTrace();
		}
	}

}
