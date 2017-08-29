package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Paciente;
import br.com.rarp.model.dao.PacienteDAO;
import br.com.rarp.utils.Utilitarios;

public class PacienteBusiness {

	public List<Paciente> consultar(String campo, String comparacao, String termo) throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();
		return pacienteDAO.consultar(campo, comparacao, termo);
	}

	public void salvar(Paciente paciente) throws Exception {
		if(paciente == null)
			throw new Exception("O paciente não foi instânciado");
		
		if(paciente.isStatus())
			validarPaciente(paciente);
		PacienteDAO pacienteDAO = new PacienteDAO();
		pacienteDAO.salvar(paciente);
	}

	private void validarPaciente(Paciente paciente) throws Exception {
		if(!Utilitarios.isCPF(paciente.getCpfSemMascara()))
			throw new Exception("CPF inválido");
	}

}
