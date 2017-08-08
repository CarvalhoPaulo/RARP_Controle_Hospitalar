package br.com.rarp.model.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.rarp.model.Cidade;
import br.com.rarp.model.dao.CidadeDAO;

public class CidadeBusiness {

	public void salvar(Cidade cidade) throws Exception {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	public List<Cidade> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		return new CidadeDAO().consultar(campo, comparacao, termo);
	}

}
