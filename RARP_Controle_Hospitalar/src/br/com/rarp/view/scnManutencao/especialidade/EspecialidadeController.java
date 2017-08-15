package br.com.rarp.view.scnManutencao.especialidade;

import br.com.rarp.control.EspecialidadeCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.control.Enum.TipoMovimentacao;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Funcionario;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroEspecialidade.CadastroEspecialidadeController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.event.ActionEvent;
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
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Funcionario, String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new PropertyValueFactory<>("Status"));
		
		codigo.setPrefWidth(100);
		nome.setPrefWidth(1000);
		status.setPrefWidth(100);
		
		tvManutencao.getColumns().addAll(codigo, nome,status);
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
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		EspecialidadeCtrl especialidadeCtrl = new EspecialidadeCtrl();
		try {
			tvManutencao.setItems(especialidadeCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: edtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao pesquisar as especialidades.\n" + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEspecialidade(TipoMovimentacao.insercao);
			CadastroEspecialidadeController controler = new CadastroEspecialidadeController();
			controler.inserir();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEspecialidade(TipoMovimentacao.alteracao);
			CadastroEspecialidadeController controller = new CadastroEspecialidadeController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EspecialidadeCtrl especialidadeCtrl = new EspecialidadeCtrl();
				especialidadeCtrl.setEspecialidade(tvManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(especialidadeCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoEspecialidade(TipoMovimentacao.visualizaco);
			CadastroEspecialidadeController controller = new CadastroEspecialidadeController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EspecialidadeCtrl especialidadeCtrl = new EspecialidadeCtrl();
				especialidadeCtrl.setEspecialidade(tvManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(especialidadeCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}
