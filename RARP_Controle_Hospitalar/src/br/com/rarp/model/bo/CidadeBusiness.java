package br.com.rarp.model.bo;

import br.com.rarp.model.Cidade;
import br.com.rarp.model.dao.CidadeDAO;

public class CidadeBusiness {

	public void salvar(Cidade cidade) throws Exception {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

}
