package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Paciente;
import br.com.rarp.model.bo.PacienteBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PacienteCtrl {
	private Paciente paciente;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Object paciente) {
		this.paciente = (Paciente) paciente;
	}
	
	public void novoPaciente() {
		paciente = new Paciente();
	}
	
	public boolean salvar() throws Exception {
		if (verificarDesativacao()) {
			PacienteBusiness pacienteBusiness = new PacienteBusiness();
			validarDadosObrigatorios();
			pacienteBusiness.salvar(paciente);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		PacienteBusiness pacienteBusiness = new PacienteBusiness();
		return FXCollections.observableList(
				pacienteBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	private void validarDadosObrigatorios() throws Exception {
		if (paciente != null) 
			novoPaciente();

		if (paciente.getNome().isEmpty()) {
			throw new Exception("Para cadastrar um paciente é necessário informar o nome");
		}
		if (paciente.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um paciente é necessário informar o CPF");
		}
		if (!Utilitarios.isMaiorIdade(paciente.getDtNascimento()) && paciente.getResponsavel() == null) {
			throw new Exception("Para cadastrar um paciente menor que 18 anos é necessário informar o responsável");
		}
	}
	
	private boolean verificarDesativacao() {
		if(paciente != null && !paciente.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar este paciente?");
		return true;
	}

}
