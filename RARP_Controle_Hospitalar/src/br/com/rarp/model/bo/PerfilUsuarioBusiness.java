package br.com.rarp.model.bo;

import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.dao.PerfilUsuarioDAO;

public class PerfilUsuarioBusiness {

	public void salvar(PerfilUsuario perfilUsuario) throws Exception {
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
		perfilUsuarioDAO.salvar(perfilUsuario);
	}

}
