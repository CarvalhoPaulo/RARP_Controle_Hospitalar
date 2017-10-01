package br.com.rarp.model.bo;

import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.dao.EncaminhamentoDAO;

public class EncaminhamentoBusiness {

	public void salvar(Encaminhamento encaminhamento, Encaminhamento encaminhamentoAntigo) throws Exception {
		if(encaminhamento == null)
			throw new Exception("O encaminhamento não foi instânciada");
		
		if(encaminhamento.isStatus())
			validarEncaminhamento(encaminhamento);
		
		encaminhamentoAntigo.getOrigem().setPaciente(encaminhamentoAntigo.getDestino().getPaciente());
		encaminhamentoAntigo.getOrigem().setSujo(false);
		encaminhamentoAntigo.getDestino().setPaciente(null);
				
		encaminhamento.getDestino().setPaciente(encaminhamento.getOrigem().getPaciente());
		encaminhamento.getOrigem().setSujo(true);
		encaminhamento.getOrigem().setPaciente(null);
		
		EncaminhamentoDAO encaminhamentoDAO = new EncaminhamentoDAO();
		encaminhamentoDAO.salvar(encaminhamento, encaminhamentoAntigo);		
	}

	private void validarEncaminhamento(Encaminhamento encaminhamento) throws Exception {
		if(encaminhamento.getOrigem().getPaciente() == null)
			throw new Exception("O leito de origem deve possuir um paciente para realizar o encaminhamento");
		
		if(encaminhamento.getDestino().getPaciente() != null)
			throw new Exception("O leito de destino deve estar livre para realizar o encaminhamento");
	}

}
