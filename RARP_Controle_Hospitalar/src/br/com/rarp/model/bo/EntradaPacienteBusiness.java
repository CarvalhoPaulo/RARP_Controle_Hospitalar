package br.com.rarp.model.bo;

import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.dao.EntradaPacienteDAO;

public class EntradaPacienteBusiness {

	public void salvar(EntradaPaciente entradaPaciente) throws Exception {
		if(entradaPaciente == null)
			throw new Exception("A entrada de paciente n�o foi inst�nciada");
		
		if(entradaPaciente.isStatus())
			validarEntradaPaciente(entradaPaciente);
		EntradaPacienteDAO entradaPacienteDAO = new EntradaPacienteDAO();
		entradaPacienteDAO.salvar(entradaPaciente);		
	}
	
	private void validarEntradaPaciente(EntradaPaciente entradaPaciente) throws Exception {
		if (entradaPaciente != null) {
			if (entradaPaciente.getMedico() == null && entradaPaciente.getAtendimentos().size() > 0)
				throw new Exception("Para cadastrar os atendimentos para esta entrada de paciente � necess�rio informar o m�dico");
			if (entradaPaciente.getAtendimentos().isEmpty())
				throw new Exception("Para cadastrar uma entrada de paciente � necess�rio informar o m�dico");
		}
	}

}
