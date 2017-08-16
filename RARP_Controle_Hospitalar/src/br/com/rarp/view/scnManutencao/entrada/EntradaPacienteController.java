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
		getLblTitle().setText("Manutenção de Espaços");
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

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Função do botão Pesquisar");
	}

	@Override
	public void inserir() {
		Utilitarios.atencao("Função do botão Inserir");
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
