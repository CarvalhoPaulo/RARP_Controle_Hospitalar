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
		getLblTitle().setText("Manutenção de Usuários");

		TableColumn<Funcionario, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<Usuario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("data"));
		TableColumn<Usuario, String> usuario = new TableColumn<>("Usuário");
		usuario.setCellValueFactory(new PropertyValueFactory<>("hora"));
		TableColumn<Usuario, String> funcionario = new TableColumn<>("Funcionário");
		funcionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
		TableColumn<Usuario, String> perfilUsuario = new TableColumn<>("Perfil");
		perfilUsuario.setCellValueFactory(new PropertyValueFactory<>("perfilUsuario"));

		tvManutencao.getColumns().addAll(codigo, nome, usuario, funcionario, perfilUsuario);
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Função do botão Pesquisar");
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
		Utilitarios.atencao("Função do botão Alterar");
	}

	@Override
	public void visualizar() {
		Utilitarios.atencao("Função do botão Visualizar");
	}
}
