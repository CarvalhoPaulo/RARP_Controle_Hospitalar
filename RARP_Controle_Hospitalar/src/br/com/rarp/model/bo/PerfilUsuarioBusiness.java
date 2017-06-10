package br.com.rarp.model.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.dao.PerfilUsuarioDAO;

public class PerfilUsuarioBusiness {

	public void salvar(PerfilUsuario perfilUsuario) throws Exception {
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
		perfilUsuarioDAO.salvar(perfilUsuario);
	}

	public List<PerfilUsuario> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
		return perfilUsuarioDAO.consultar(campo, comparacao, termo);
	}

}
