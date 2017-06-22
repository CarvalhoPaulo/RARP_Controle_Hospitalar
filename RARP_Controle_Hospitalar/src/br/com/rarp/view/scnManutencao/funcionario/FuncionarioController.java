package br.com.rarp.view.scnManutencao.funcionario;

import br.com.rarp.model.Funcionario;
import br.com.rarp.model.Usuario;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroUsuario.CadastroUsuarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FuncionarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manuten��o de Usu�rios");

		TableColumn<Funcionario, String> codigo = new TableColumn<>("C�digo");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<Usuario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("data"));
		TableColumn<Usuario, String> usuario = new TableColumn<>("Usu�rio");
		usuario.setCellValueFactory(new PropertyValueFactory<>("hora"));
		TableColumn<Usuario, String> funcionario = new TableColumn<>("Funcion�rio");
		funcionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
		TableColumn<Usuario, String> perfilUsuario = new TableColumn<>("Perfil");
		perfilUsuario.setCellValueFactory(new PropertyValueFactory<>("perfilUsuario"));

		tvManutencao.getColumns().addAll(codigo, nome, usuario, funcionario, perfilUsuario);
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Fun��o do bot�o Pesquisar");
	}

	@Override
	public void inserir() {
		try {
			CadastroUsuarioController controler = new CadastroUsuarioController();
			controler.inserir();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes");
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		Utilitarios.atencao("Fun��o do bot�o Alterar");
	}

	@Override
	public void visualizar() {
		Utilitarios.atencao("Fun��o do bot�o Visualizar");
	}
}
