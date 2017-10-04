package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.dao.EncaminhamentoDAO;

public class EncaminhamentoBusiness {

	public void salvar(Encaminhamento encaminhamento) throws Exception {
		if(encaminhamento == null)
			throw new Exception("O encaminhamento n�o foi inst�nciada");
		
		try {
			if (encaminhamento.isStatus())
				validarEncaminhamento(encaminhamento);
			
			encaminhamento.getDestino().setPaciente(encaminhamento.getOrigem().getPaciente());
			encaminhamento.getOrigem().setSujo(true);
			encaminhamento.getOrigem().setPaciente(null);
			
			EncaminhamentoDAO encaminhamentoDAO = new EncaminhamentoDAO();
			encaminhamentoDAO.salvar(encaminhamento);
		} catch (Exception e) {
			encaminhamento.getOrigem().setPaciente(encaminhamento.getDestino().getPaciente());
			encaminhamento.getOrigem().setSujo(false);
			encaminhamento.getDestino().setPaciente(null);
			throw new Exception(e.getMessage());
		}		
	}

	private void validarEncaminhamento(Encaminhamento encaminhamento) throws Exception {
		if(encaminhamento.getCodigo() != 0)
			throw new Exception("N�o � possivel alterar um encaminhamento realizado. Realize um novo encaminhamento.");
		
		if(encaminhamento.getOrigem().getPaciente() == null)
			throw new Exception("O leito de origem deve possuir um paciente para realizar o encaminhamento");
		
		if(encaminhamento.getDestino().getPaciente() != null)
			throw new Exception("O leito de destino deve estar livre para realizar o encaminhamento");
		
		if(encaminhamento.getEntradaPaciente().getSaidaPaciente() != null 
				&& encaminhamento.getEntradaPaciente().getSaidaPaciente().getCodigo() > 0)
			throw new Exception("N�o � poss�vel realizar o encaminhamento para um paciente que saiu do hospital");
	}

	public List<Encaminhamento> consultar(String campo, String comparacao, String termo) throws Exception {
		return new EncaminhamentoDAO().consultar(campo, comparacao, termo);
	}

}
