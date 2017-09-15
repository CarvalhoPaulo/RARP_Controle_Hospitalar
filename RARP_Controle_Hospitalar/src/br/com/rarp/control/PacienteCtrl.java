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
		if (paciente == null)
			throw new Exception("O paciente n�o foi inst�nciado");
		
		if (confirmarDesativacao()) {
			if(paciente.isStatus())
				validarDadosObrigatorios();
			PacienteBusiness pacienteBusiness = new PacienteBusiness();
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
		if (paciente.getNome() != null && paciente.getNome().isEmpty()) {
			throw new Exception("Para cadastrar um paciente � necess�rio informar o nome");
		}
		if (paciente.getCpf() != null && paciente.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um paciente � necess�rio informar o CPF");
		}
		if(!Utilitarios.isCPF(paciente.getCpfSemMascara()))
			throw new Exception("CPF inv�lido");
	}
	
	private boolean confirmarDesativacao() {
		if(paciente != null && !paciente.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este paciente?");
		return true;
	}

	public ObservableList<Paciente> getPacientes() throws Exception {
		PacienteBusiness pacienteBusiness = new PacienteBusiness();
		return FXCollections.observableList(
				pacienteBusiness.consultar("PAC.codigo",  " > ", "0"));
	}

}
