package br.com.rarp.view.scnManutencao.funcionario;

import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.control.FuncionarioCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoMovimentacao;
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
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());

	}

	public void adicionarCampos() {
		cmbCampo.getItems().add(new Campo("func.codigo", "Código", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("func.status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		FuncionarioCtrl funcionarioCtrl = new FuncionarioCtrl();
		try {
			tvManutencao.setItems(funcionarioCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: edtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao salvar perfil de usuario.\n" + "Descrição: " + e.getMessage());
		}
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
		try {
			SistemaCtrl.getInstance().liberarManutencaoUsuario(TipoMovimentacao.alteracao);
			CadastroFuncionarioController controller = new CadastroFuncionarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				FuncionarioCtrl funcionarioCtrl = new FuncionarioCtrl();
				funcionarioCtrl.setFuncionario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(funcionarioCtrl);
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
			CadastroFuncionarioController controller = new CadastroFuncionarioController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				FuncionarioCtrl funcionarioCtrl = new FuncionarioCtrl();
				funcionarioCtrl.setFuncionario(tvManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(funcionarioCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}
