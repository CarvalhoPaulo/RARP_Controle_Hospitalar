package br.com.rarp.model.bo;

import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.dao.EncaminhamentoDAO;

public class EncaminhamentoBusiness {

	public void salvar(Encaminhamento encaminhamento) throws Exception {
		if(encaminhamento == null)
			throw new Exception("O encaminhamento não foi instânciada");
		
		if(encaminhamento.isStatus())
			validarEncaminhamento(encaminhamento);
		EncaminhamentoDAO encaminhamentoDAO = new EncaminhamentoDAO();
		encaminhamentoDAO.salvar(encaminhamento);		
	}

	private void validarEncaminhamento(Encaminhamento encaminhamento) {
		
	}

}
