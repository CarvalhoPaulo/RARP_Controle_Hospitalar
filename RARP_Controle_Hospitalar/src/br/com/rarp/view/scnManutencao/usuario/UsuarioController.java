package br.com.rarp.view.scnManutencao.usuario;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.UsuarioCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.control.Enum.TipoMovimentacao;
import br.com.rarp.model.Usuario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroUsuario.CadastroUsuarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manutenção de Usuários");
		getLblTitle().setStyle("-fx-background-color: #72c4fb;"
				+ "-fx-font-weight: bold");
		
		TableColumn<Usuario, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<Usuario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		TableColumn<Usuario, String> usuario = new TableColumn<>("Usuário");
		usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		TableColumn<Usuario, String> funcionario = new TableColumn<>("Funcionário");
		funcionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
		TableColumn<Usuario, String> perfilUsuario = new TableColumn<>("Perfil");
		perfilUsuario.setCellValueFactory(new PropertyValueFactory<>("perfilUsuario"));
		
		tvManutencao.getColumns().addAll(codigo, nome, usuario, funcionario, perfilUsuario);
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());
	}

	private void adicionarCampos() {
		cmbCampo.getItems().add(new Campo("codigo", "Código", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("nome", "Nome", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("usuario", "Usuário", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
		try {
			tvManutencao.setItems(usuarioCtrl.consultar(
					cmbCampo.getSelectionModel().getSelectedItem(), 
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue() : edtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n"
					   + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.insercao);
			CadastroUsuarioController controler = new CadastroUsuarioController();
			controler.inserir();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.alteracao);
			CadastroUsuarioController controller = new CadastroUsuarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
				usuarioCtrl.setUsuario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(usuarioCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.visualizaco);
			CadastroUsuarioController controller = new CadastroUsuarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
				usuarioCtrl.setUsuario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(usuarioCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}
