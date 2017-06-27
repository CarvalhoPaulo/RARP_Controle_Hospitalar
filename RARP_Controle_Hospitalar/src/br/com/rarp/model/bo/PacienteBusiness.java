package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Paciente;
import br.com.rarp.model.dao.PacienteDAO;

public class PacienteBusiness {

	public static void salvar(Paciente paciente) throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();
		PacienteDAO.salvar(paciente);
	}

	public List<Paciente> consultar(String campo, String comparacao, String termo) throws Exception {
		PacienteDAO pacienteDAO = new PacienteDAO();
		return pacienteDAO.consultar(campo, comparacao, termo);
	}

}
