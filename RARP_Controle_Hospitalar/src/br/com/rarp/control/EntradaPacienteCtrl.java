package br.com.rarp.control;

import java.util.List;

import br.com.rarp.enums.TipoCampo;
import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.bo.EntradaPacienteBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.utils.comparacao.Maior;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntradaPacienteCtrl {

	private EntradaPaciente entradaPaciente;

	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}

	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}

	public ObservableList<EntradaPaciente> consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		EntradaPacienteBusiness entradaPacienteBusiness = new EntradaPacienteBusiness();
		return FXCollections.observableList(
				entradaPacienteBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public void setEntradaPaciente(Object entradaPaciente) {
		this.entradaPaciente = (EntradaPaciente) entradaPaciente;
	}

	public boolean salvar() throws Exception {
		if (entradaPaciente == null)
			throw new Exception("A entrada de paciente n�o foi inst�nciada");

		if (confirmarDesativacao()) {
			if (entradaPaciente.isStatus())
				validarDadosObrigatorios();
			EntradaPacienteBusiness entradaPacienteBusiness = new EntradaPacienteBusiness();
			entradaPacienteBusiness.salvar(entradaPaciente);
			return true;
		} else {
			return false;
		}
	}

	private void validarDadosObrigatorios() throws Exception {
		if (entradaPaciente != null) {
			if (entradaPaciente.getDtMovimentacao() == null)
				throw new Exception("Para cadastrar uma entrada de paciente � necess�rio informar a data");

			if (entradaPaciente.getHrMovimentacao() == null)
				throw new Exception("Para cadastrar uma entrada de paciente � necess�rio informar a hora");

			if (entradaPaciente.getAtendente() == null)
				throw new Exception("Para cadastrar uma entrada de paciente � necess�rio informar a atendente");
			
			if (entradaPaciente.getPaciente() == null)
				throw new Exception("Para cadastrar uma entrada de paciente � necess�rio informar o paciente");
		}
	}

	private boolean confirmarDesativacao() {
		if(entradaPaciente != null && !entradaPaciente.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar esta entrada de paciente?");
		return true;
	}

	public void novaEntradaPaciente() {
		entradaPaciente = new EntradaPaciente();
	}

	public List<EntradaPaciente> getEntradasPaciente() throws Exception {
		return consultar(new Campo("ENT.codigo", "", TipoCampo.texto), new Maior(), "0");
	}

}
