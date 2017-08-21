package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Medico;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.EspecialidadeDAO;
import br.com.rarp.model.dao.MedicoDAO;

public class MedicoBusiness {

	public void salvar(Medico especialidade) throws Exception {
		MedicoDAO medicoDAO = new MedicoDAO();
		medicoDAO.salvar(especialidade);
	}

	public List<Especialidade> consultar(String campo, String comparacao, String termo) throws Exception {
		// TODO Auto-generated method stub
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		return especialidadeDAO.consultar(campo	, comparacao, termo);
	
	}

}
