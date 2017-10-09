package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.SaidaPaciente;
import br.com.rarp.model.bo.SaidaPacienteBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SaidaPacienteCtrl {
	private SaidaPaciente saidaPaciente;
	
	public void novaSaidaPaciente() {
		setSaidaPaciente(new SaidaPaciente());
	}

	public SaidaPaciente getSaidaPaciente() {
		return saidaPaciente;
	}

	public void setSaidaPaciente(Object saidaPaciente) {
		this.saidaPaciente = (SaidaPaciente) saidaPaciente;
	}

	public ObservableList<SaidaPaciente> consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		return FXCollections.observableList(new SaidaPacienteBusiness().consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public boolean salvar() throws Exception {
		if (saidaPaciente == null)
			throw new Exception("A sa�da de paciente n�o foi inst�nciada");

		if (confirmarDesativacao()) {
			if (saidaPaciente.isStatus())
				validarDadosObrigatorios();
			SaidaPacienteBusiness saidaPacienteBusiness = new SaidaPacienteBusiness();
			saidaPacienteBusiness.salvar(saidaPaciente);
			return true;
		} else {
			return false;
		}
	}

	private void validarDadosObrigatorios() throws Exception {
		if(saidaPaciente != null) {
			if(saidaPaciente.getDtMovimentacao() == null)
				throw new Exception("Para cadastrar uma sa�da de paciente � necess�rio informar a data");
			if(saidaPaciente.getHrMovimentacao() == null)
				throw new Exception("Para cadastrar uma sa�da de paciente � necess�rio informar a hora");
			if(saidaPaciente.getEntradaPaciente() == null)
				throw new Exception("Para cadastrar uma sa�da de paciente � necess�rio informar a entrada de paciente relacionada");
			if(saidaPaciente.getEstadoPaciente().isEmpty())
				throw new Exception("Para cadastrar uma sa�da de paciente � necess�rio informar o estado do paciente");
		}
	}

	private boolean confirmarDesativacao() {
		if(!saidaPaciente.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar esta saida de paciente?");
		return true;
	}
}