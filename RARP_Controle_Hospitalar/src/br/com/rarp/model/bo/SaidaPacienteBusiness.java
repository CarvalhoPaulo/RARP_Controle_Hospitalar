package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.SaidaPaciente;
import br.com.rarp.model.dao.SaidaPacienteDAO;

public class SaidaPacienteBusiness {

	public void salvar(SaidaPaciente saidaPaciente) throws Exception {
		if(saidaPaciente == null)
			throw new Exception("A saída de paciente não foi instânciada");
		
		if(saidaPaciente.isStatus())
			validarSaidaPaciente(saidaPaciente);
		else
			saidaPaciente.setEntradaPaciente(null);
		SaidaPacienteDAO saidaPacienteDAO = new SaidaPacienteDAO();
		saidaPacienteDAO.salvar(saidaPaciente);
	}

	private void validarSaidaPaciente(SaidaPaciente saidaPaciente) throws Exception {
		if(saidaPaciente.getEntradaPaciente() != null && !saidaPaciente.getEntradaPaciente().isAlta())
			throw new Exception("O paciente da entrada de paciente relacionada não ganhou alta, portanto não é possível realizar a saída do mesmo");
	}

	public List<SaidaPaciente> consultar(String campo, String comparacao, String termo) throws Exception {
		return new SaidaPacienteDAO().consultar(campo, comparacao, termo);
	}

}
