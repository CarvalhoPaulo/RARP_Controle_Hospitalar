package br.com.rarp.model.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.rarp.enums.StatusAtendimento;
import br.com.rarp.model.Atendimento;
import br.com.rarp.model.dao.AtendimentoDAO;

public class AtendimentoBusiness {

	public void salvar(Atendimento atendimento) throws Exception {
		if(atendimento == null)
			throw new Exception("A entrada de paciente não foi instânciada");
		
		if(atendimento.isStatus())
			validarAtendimento(atendimento);
		else
			validarDesativacao(atendimento);
		AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
		atendimentoDAO.salvar(atendimento);
	}

	public void validarAtendimento(Atendimento atendimento) throws Exception {
		if(atendimento != null) {
			if(atendimento.getDtMovimentacao().isAfter(LocalDate.now()))
				throw new Exception("A data informada deve ser menor que a data atual");
			
			if(atendimento.getDtMovimentacao().isEqual(LocalDate.now()) && atendimento.getHrMovimentacao().isAfter(LocalTime.now()))
				throw new Exception("A hora informada deve ser menor que a hora atual");
			
			if(atendimento.getCodigo() == 0) {
				if (atendimento.getDataAtendimento().isBefore(LocalDate.now()) && atendimento.getStatusAtendimento() != StatusAtendimento.realizado) 
					throw new Exception("Não é possível cadastrar um atendimento não realizado com data retroativa");
				
				if (atendimento.getHoraFim().isBefore(LocalTime.now()) && atendimento.getStatusAtendimento() != StatusAtendimento.realizado) 
					throw new Exception("Não é possível cadastrar um atendimento não realizado com hora retroativa");
			}
		}
	}

	public List<Atendimento> consultar(String campo, String comparacao, String termo) throws ClassNotFoundException, Exception {
		return new AtendimentoDAO().consultar(campo, comparacao, termo);
	}

	public void validarDesativacao(Atendimento a) throws Exception {
		if(a.getStatusAtendimento() == StatusAtendimento.realizado)
			throw new Exception("Não é possível desativar um atendimento já realizado");
	}

}
