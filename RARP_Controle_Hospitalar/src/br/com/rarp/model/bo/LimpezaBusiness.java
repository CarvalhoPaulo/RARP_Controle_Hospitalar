package br.com.rarp.model.bo;

import br.com.rarp.model.Leito;
import br.com.rarp.model.Limpeza;
import br.com.rarp.model.dao.LimpezaDAO;

public class LimpezaBusiness {

	public void salvar(Limpeza limpeza) throws Exception {
		try {
			LimpezaDAO limpezaDAO = new LimpezaDAO();
			for(Leito l: limpeza.getLeitos())
				l.setSujo(false);
			limpezaDAO.salvar(limpeza);
		} catch (Exception e) {
			for(Leito l: limpeza.getLeitos())
				l.setSujo(true);
			throw new Exception(e.getMessage());
		}
	}

}
