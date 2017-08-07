package br.com.rarp.view.scnManutencao.especialidade;

import java.io.IOException;

import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Funcionario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroEspecialidade.CadastroEspecialidadeController;
import br.com.rarp.view.scnCadastroFuncionario.CadastroFuncionarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class EspecialidadeController extends ManutencaoController {

	
	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manutenção de Especialidades");

		TableColumn<Especialidade, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<Funcionario, String> nome = new TableColumn<>("Nome");

		tvManutencao.getColumns().addAll(codigo, nome);
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());
	}

	public void adicionarCampos() {
		// Adicionar todos os campos que são strings numéricos ou booleanos,
		// para pesquisa.
		cmbCampo.getItems().add(new Campo("codigo", "Código", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("nome", "Nome", TipoCampo.texto));
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Função do botão Pesquisar");
	}

	@Override
	public void inserir() {	
		try {
			CadastroEspecialidadeController cadastroEspecialidadeController = new CadastroEspecialidadeController();
			cadastroEspecialidadeController.inserir();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes\n"
					   + "Descrição: " + e.getMessage());
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
