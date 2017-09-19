package br.com.rarp.view.scnManutencao.encaminhamento;

import br.com.rarp.control.EncaminhamentoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.TipoCampo;
import br.com.rarp.enums.TipoMovimentacao;
import br.com.rarp.model.Encaminhamento;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroEncaminhamento.CadastroEncaminhamentoController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

public class EncaminhamentoController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Controle de Encaminhamentos");
		getLblTitle().setTextFill(Paint.valueOf("#FFFFFF"));
		getLblTitle().setStyle("-fx-background-color: #8F929C;"
				+ "-fx-font-weight: bold");

		TableColumn<Encaminhamento, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<Encaminhamento, String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Encaminhamento,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Encaminhamento, String> param) {
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
		EncaminhamentoCtrl encaminhamentoCtrl = new EncaminhamentoCtrl();
		try {
			tblManutencao.setItems(encaminhamentoCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: txtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao pesquisar os encaminhamentos.\n" + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.insercao);
			CadastroEncaminhamentoController controler = new CadastroEncaminhamentoController();
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
			CadastroEncaminhamentoController controller = new CadastroEncaminhamentoController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EncaminhamentoCtrl encaminhamentoCtrl = new EncaminhamentoCtrl();
				encaminhamentoCtrl.setEncaminhamento(tblManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(encaminhamentoCtrl);
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
			CadastroEncaminhamentoController controller = new CadastroEncaminhamentoController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EncaminhamentoCtrl encaminhamentoCtrl = new EncaminhamentoCtrl();
				encaminhamentoCtrl.setEncaminhamento(tblManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(encaminhamentoCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}

