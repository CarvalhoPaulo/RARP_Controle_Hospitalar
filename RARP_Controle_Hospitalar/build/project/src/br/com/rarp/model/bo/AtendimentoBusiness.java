package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Atendimento;
import br.com.rarp.model.dao.AtendimentoDAO;

public class AtendimentoBusiness {

	public void salvar(Atendimento atendimento) throws Exception {
		if(atendimento == null)
			throw new Exception("A entrada de paciente n�o foi inst�nciada");
		
		if(atendimento.isStatus())
			validarAtendimento(atendimento);
		AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
		atendimentoDAO.salvar(atendimento);
	}

	private void validarAtendimento(Atendimento atendimento) {
		// TODO Auto-generated method stub
		
	}

	public List<Atendimento> consultar(String campo, String comparacao, String termo) throws ClassNotFoundException, Exception {
		return new AtendimentoDAO().consultar(campo, comparacao, termo);
	}

}
