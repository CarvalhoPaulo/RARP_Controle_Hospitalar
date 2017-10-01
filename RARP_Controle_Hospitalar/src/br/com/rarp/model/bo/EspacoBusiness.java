package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Espaco;
import br.com.rarp.model.dao.EspacoDAO;

public class EspacoBusiness {
	public void salvar(Espaco espaco) throws Exception {
		if(espaco == null)
			throw new Exception("A espaço não foi instânciada");
		
		if(espaco.isStatus())
			validarEspaco(espaco);
		EspacoDAO espacoDAO = new EspacoDAO();
		espacoDAO.salvar(espaco);
	}

	private void validarEspaco(Espaco espaco) {
		//Valida o espaço
	}

	public List<Espaco> consultar(String campo, String comparacao, String termo) throws Exception {
		EspacoDAO espacoDAO = new EspacoDAO();
		return espacoDAO.consultar(campo, comparacao, termo);
	}

	public List<Espaco> getEspacosLivres() throws ClassNotFoundException, Exception {
		return new EspacoDAO().getEspacos(true);
	}

	public List<Espaco> getEspacosCheios() throws ClassNotFoundException, Exception {
		return new EspacoDAO().getEspacos(false);
	}
}
