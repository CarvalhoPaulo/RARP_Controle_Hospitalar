package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Leito;
import br.com.rarp.model.Limpeza;
import br.com.rarp.model.dao.LimpezaDAO;

public class LimpezaBusiness {

	public void salvar(Limpeza limpeza, Limpeza limpezaAnt) throws Exception {
		try {
			LimpezaDAO limpezaDAO = new LimpezaDAO();
			if(limpezaAnt != null)
				for(Leito l: limpezaAnt.getLeitos())
					l.setSujo(true);
			for(Leito l: limpeza.getLeitos())
				l.setSujo(false);
			limpezaDAO.salvar(limpeza, limpezaAnt);
		} catch (Exception e) {
			for(Leito l: limpeza.getLeitos())
				l.setSujo(true);
			throw new Exception(e.getMessage());
		}
	}

	public List<Limpeza> consultar(String campo, String comparacao, String termo) throws Exception {
		return new LimpezaDAO().consultar(campo, comparacao, termo);
	}

}
