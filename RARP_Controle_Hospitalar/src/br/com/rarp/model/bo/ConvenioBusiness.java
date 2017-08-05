package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Convenio;
import br.com.rarp.model.dao.ConvenioDAO;

public class ConvenioBusiness {

	public List<Convenio> consultar(String campo, String comparacao, String termo) throws Exception {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		return convenioDAO.consultar(campo, comparacao, termo);
	}

}
