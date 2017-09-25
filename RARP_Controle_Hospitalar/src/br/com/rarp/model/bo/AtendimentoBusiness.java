package br.com.rarp.model.bo;

import br.com.rarp.model.Atendimento;
import br.com.rarp.model.dao.AtendimentoDAO;

public class AtendimentoBusiness {

	public void salvar(Atendimento atendimento) throws Exception {
		if(atendimento == null)
			throw new Exception("A entrada de paciente não foi instânciada");
		
		if(atendimento.isStatus())
			validarEntradaPaciente(atendimento);
		AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
		atendimentoDAO.salvar(atendimento);
	}

	private void validarEntradaPaciente(Atendimento atendimento) {
		// TODO Auto-generated method stub
		
	}

}
