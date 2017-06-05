package br.com.rarp.view.scnManutencao.Entrada;

import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class EntradaPacienteController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manuten��o de Entrada de paciente");
		TableColumn<EntradaPaciente, String> codigo = new TableColumn<>("C�digo");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<EntradaPaciente, String> data = new TableColumn<>("Data");
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		TableColumn<EntradaPaciente, String> hora = new TableColumn<>("Hora");
		hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
		TableColumn<EntradaPaciente, String> paciente = new TableColumn<>("Paciente");
		paciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
		
		getTvManutencao().getColumns().addAll(codigo, data, hora, paciente);
		
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Fun��o do bot�o Pesquisar");
	}

	@Override
	public void inserir() {
		Utilitarios.atencao("Fun��o do bot�o Inserir");
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
