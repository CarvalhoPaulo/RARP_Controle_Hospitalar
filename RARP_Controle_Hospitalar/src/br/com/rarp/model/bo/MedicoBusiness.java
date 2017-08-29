package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Medico;
import br.com.rarp.model.dao.EspecialidadeDAO;
import br.com.rarp.model.dao.MedicoDAO;
import br.com.rarp.utils.Utilitarios;

public class MedicoBusiness {

	public void salvar(Medico medico) throws Exception {
		if(medico == null)
			throw new Exception("O medico não foi instânciado");
		
		if(medico.isStatus())
			validarMedico(medico);
		MedicoDAO medicoDAO = new MedicoDAO();
		medicoDAO.salvar(medico);
	}

	private void validarMedico(Medico medico) throws Exception {
		if(!Utilitarios.isCPF(medico.getCpfSemMascara()))
			throw new Exception("CPF inválido");
	}

	public List<Especialidade> consultar(String campo, String comparacao, String termo) throws Exception {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultar(campo	, comparacao, termo);
	}

}
