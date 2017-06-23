package br.com.rarp.view.scnManutencao.funcionario;

import br.com.rarp.model.Funcionario;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroFuncionario.CadastroFuncionarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FuncionarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manutenção de Funcionários");

		TableColumn<Funcionario, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<Funcionario, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		TableColumn<Funcionario, String> cpf = new TableColumn<>("CPF");
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		TableColumn<Funcionario, String> cargo = new TableColumn<>("Cargo");
		cargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		TableColumn<Funcionario, String> telefone = new TableColumn<>("Telefone");
		telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tvManutencao.getColumns().addAll(codigo, nome, cpf, cargo, telefone);
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Função do botão Pesquisar");
	}

	@Override
	public void inserir() {
		try {
			CadastroFuncionarioController controler = new CadastroFuncionarioController();
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
