package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Espaco;
import br.com.rarp.model.dao.EspacoDAO;

public class EspacoBusiness {
	public void salvar(Espaco espaco) throws Exception {
		EspacoDAO espacoDAO = new EspacoDAO();
		espacoDAO.salvar(espaco);
	}

	public List<Espaco> consultar(String campo, String comparacao, String termo) throws Exception {
		EspacoDAO espacoDAO = new EspacoDAO();
		return espacoDAO.consultar(campo, comparacao, termo);
	}
}
