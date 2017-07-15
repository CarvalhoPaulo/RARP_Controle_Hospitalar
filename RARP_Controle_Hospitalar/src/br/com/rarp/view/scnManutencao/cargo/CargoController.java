package br.com.rarp.view.scnManutencao.cargo;

import br.com.rarp.control.Enum.TipoCampo;
import br.com.rarp.control.CargoCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.control.Enum.TipoMovimentacao;
import br.com.rarp.model.Cargo;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroCargo.CadastroCargoController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CargoController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manutenção de Funcionários");

		TableColumn<Cargo, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<Cargo, String> nome = new TableColumn<>("Nome");
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cargo, String> funcao = new TableColumn<>("Função");
		funcao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
		
		TableColumn<Cargo, String> nivel = new TableColumn<>("Nível");
		nivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
		
		TableColumn<Cargo, String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cargo,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Cargo, String> param) {
				if(param != null && param.getValue() != null && param.getValue().isStatus())
					return new SimpleStringProperty("Ativado");
				else
					return new SimpleStringProperty("Desativado");
			}
		});

		tvManutencao.getColumns().addAll(codigo, nome, funcao, nivel, status);
		tvManutencao.setEditable(false);
		adicionarCampos();
		cmbCampo.getSelectionModel().select(0);
		cmbCampo.getOnAction().handle(new ActionEvent());

	}

	public void adicionarCampos() {
		cmbCampo.getItems().add(new Campo("codigo", "Código", TipoCampo.numerico));
		cmbCampo.getItems().add(new Campo("nome", "Nome", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("funcao", "Função", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("nivel", "Nível", TipoCampo.texto));
		cmbCampo.getItems().add(new Campo("status", "Ativado", TipoCampo.booleano));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		CargoCtrl cargoCtrl = new CargoCtrl();
		try {
			tvManutencao.setItems(cargoCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: edtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao consultar cargo.\n" + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarManutencaoCargo(TipoMovimentacao.insercao);
			CadastroCargoController controler = new CadastroCargoController();
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
			CadastroCargoController controller = new CadastroCargoController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				CargoCtrl cargoCtrl = new CargoCtrl();
				cargoCtrl.setCargo(tvManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(cargoCtrl);
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
			CadastroCargoController controller = new CadastroCargoController();
			if (tvManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				CargoCtrl cargoCtrl = new CargoCtrl();
				cargoCtrl.setCargo(tvManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(cargoCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}
}

