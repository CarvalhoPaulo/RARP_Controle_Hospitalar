package br.com.rarp.model.bo;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.dao.EspecialidadeDAO;

public class EspecialidadeBusiness {

	public void salvar(Especialidade especialidade) throws Exception {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		especialidadeDAO.salvar(especialidade);
	}

}
