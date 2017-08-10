package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Convenio;
import br.com.rarp.model.dao.ConvenioDAO;
import br.com.rarp.utils.Utilitarios;

public class ConvenioBusiness {

	public List<Convenio> consultar(String campo, String comparacao, String termo) throws Exception {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		return convenioDAO.consultar(campo, comparacao, termo);
	}

	public void salvar(Convenio convenio) throws Exception {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		validarConvenio(convenio);
		convenioDAO.salvar(convenio);
	}

	private void validarConvenio(Convenio convenio) throws Exception {
		if(!Utilitarios.isCNPJ(convenio.getCnpjSemMascara()))
			throw new Exception("CNPJ Inválido");
	}

}
