package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.EspecialidadeDAO;

public class EspecialidadeBusiness {

	public void salvar(Especialidade especialidade) throws Exception {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		especialidadeDAO.salvar(especialidade);
	}

	public List<Especialidade> consultar(String campo, String comparacao, String termo) throws Exception {
		// TODO Auto-generated method stub
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultar(campo	, comparacao, termo);
	
	}

}
