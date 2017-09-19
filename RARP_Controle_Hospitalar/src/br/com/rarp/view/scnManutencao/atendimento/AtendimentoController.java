package br.com.rarp.view.scnManutencao.atendimento;

import br.com.rarp.control.AtendimentoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.TipoCampo;
import br.com.rarp.enums.TipoMovimentacao;
import br.com.rarp.model.Atendimento;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroAtendimento.CadastroAtendimentoController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

public class AtendimentoController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Controle de Atendimentos");
		getLblTitle().setTextFill(Paint.valueOf("#FFFFFF"));
		getLblTitle().setStyle("-fx-background-color: #8F929C;"
				+ "-fx-font-weight: bold");

		TableColumn<Atendimento, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<Atendimento, String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atendimento,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Atendimento, String> param) {
				if(param != null && param.getValue() != null && param.getValue().isStatus())
					return new SimpleStringProperty("Ativado");
				else
					return new SimpleStringProperty("Desativado");
			}
		});
		
		codigo.setPrefWidth(100);
		status.setPrefWidth(200);

		tblManutencao.getColumns().addAll(codigo, status);
		tblManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());

	}

	public void adicionarCampos() {
		cmbCampo.getItems().add(new Campo("codigo", "Código", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		AtendimentoCtrl atendimentoCtrl = new AtendimentoCtrl();
		try {
			tblManutencao.setItems(atendimentoCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: txtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao pesquisar os atendimentos.\n" + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.insercao);
			CadastroAtendimentoController controler = new CadastroAtendimentoController();
			controler.inserir();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.alteracao);
			CadastroAtendimentoController controller = new CadastroAtendimentoController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				AtendimentoCtrl atendimentoCtrl = new AtendimentoCtrl();
				atendimentoCtrl.setAtendimento(tblManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(atendimentoCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.visualizaco);
			CadastroAtendimentoController controller = new CadastroAtendimentoController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				AtendimentoCtrl atendimentoCtrl = new AtendimentoCtrl();
				atendimentoCtrl.setAtendimento(tblManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(atendimentoCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}

