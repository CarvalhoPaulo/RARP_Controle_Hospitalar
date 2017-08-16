package br.com.rarp.view.scnManutencao.entrada;

import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

public class EntradaPacienteController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() throws Exception {
		getLblTitle().setText("Manuten��o de Espa�os");
		getLblTitle().setTextFill(Paint.valueOf("#FFFFFF"));
		getLblTitle().setStyle("-fx-background-color: #006F4C;"
				+ "-fx-font-weight: bold;"
				+ "-fx-color: white");
		
		TableColumn<EntradaPaciente, String> codigo = new TableColumn<>("C�digo");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<EntradaPaciente, String> data = new TableColumn<>("Data");
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		TableColumn<EntradaPaciente, String> hora = new TableColumn<>("Hora");
		hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
		TableColumn<EntradaPaciente, String> paciente = new TableColumn<>("Paciente");
		paciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
		
		tblManutencao.getColumns().addAll(codigo, data, hora, paciente);
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
