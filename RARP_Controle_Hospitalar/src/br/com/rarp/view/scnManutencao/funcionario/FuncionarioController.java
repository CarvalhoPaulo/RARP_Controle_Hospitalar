package br.com.rarp.view.scnManutencao.funcionario;

import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Funcionario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroFuncionario.CadastroFuncionarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FuncionarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manuten��o de Funcion�rios");

		TableColumn<Funcionario, String> codigo = new TableColumn<>("C�digo");
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
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());
	}

	public void adicionarCampos() {
		// Adicionar todos os campos que s�o strings num�ricos ou booleanos,
		// para pesquisa.
		cmbCampo.getItems().add(new Campo("codigo", "C�digo", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Fun��o do bot�o Pesquisar");
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
		Utilitarios.atencao("Fun��o do bot�o Alterar");
	}

	@Override
	public void visualizar() {
		Utilitarios.atencao("Fun��o do bot�o Visualizar");
	}
}
