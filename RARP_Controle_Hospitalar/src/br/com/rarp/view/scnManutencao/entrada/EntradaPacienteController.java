package br.com.rarp.view.scnManutencao.entrada;

import br.com.rarp.control.EntradaPacienteCtrl;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.TipoCampo;
import br.com.rarp.enums.TipoMovimentacao;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroEntrada.CadastroEntradaController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

public class EntradaPacienteController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() throws Exception {
		getLblTitle().setText("Controle de Entrada de Pacientes");
		getLblTitle().setTextFill(Paint.valueOf("#FFFFFF"));
		getLblTitle().setStyle("-fx-background-color: #006F4C;"
				+ "-fx-font-weight: bold;"
				+ "-fx-color: white");
		
		TableColumn<EntradaPaciente, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<EntradaPaciente, String> data = new TableColumn<>("Data");
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		TableColumn<EntradaPaciente, String> hora = new TableColumn<>("Hora");
		hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
		TableColumn<EntradaPaciente, String> paciente = new TableColumn<>("Paciente");
		paciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
		
		tblManutencao.getColumns().addAll(codigo, data, hora, paciente);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar() {
		EntradaPacienteCtrl entradaPacienteCtrl = new EntradaPacienteCtrl();
		try {
			tblManutencao.setItems(entradaPacienteCtrl.consultar(cmbCampo.getSelectionModel().getSelectedItem(),
					cmbComparacao.getSelectionModel().getSelectedItem(),
					cmbCampo.getSelectionModel().getSelectedItem().getTipo() == TipoCampo.booleano ? cmbTermo.getValue()
							: txtTermo.getText()));
		} catch (Exception e) {
			Utilitarios.erro("Erro ao pesquisar os funcionários.\n" + "Descrição: " + e.getMessage());
		}
	}

	@Override
	public void inserir() {
		try {
			SistemaCtrl.getInstance().liberarControleEntradaPaciente(TipoMovimentacao.insercao);
			CadastroEntradaController controler = new CadastroEntradaController();
			controler.inserir();
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		try {
			SistemaCtrl.getInstance().liberarControleEntradaPaciente(TipoMovimentacao.alteracao);
			CadastroEntradaController controller = new CadastroEntradaController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EntradaPacienteCtrl entradaPacienteCtrl = new EntradaPacienteCtrl();
				entradaPacienteCtrl.setEntradaPaciente(tblManutencao.getSelectionModel().getSelectedItem());
				controller.alterar(entradaPacienteCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		try {
			SistemaCtrl.getInstance().liberarControleEntradaPaciente(TipoMovimentacao.visualizaco);
			CadastroEntradaController controller = new CadastroEntradaController();
			if (tblManutencao.getSelectionModel().getSelectedItem() == null)
				Utilitarios.erro("Nenhum registro foi selecionado");
			else {
				EntradaPacienteCtrl entradaPacienteCtrl = new EntradaPacienteCtrl();
				entradaPacienteCtrl.setEntradaPaciente(tblManutencao.getSelectionModel().getSelectedItem());
				controller.visualizar(entradaPacienteCtrl);
			}
		} catch (Exception e) {
			Utilitarios.erro(e.getMessage());
			e.printStackTrace();
		}
	}

}
