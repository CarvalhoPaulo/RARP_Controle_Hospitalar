package br.com.rarp.model.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.dao.PerfilUsuarioDAO;
import br.com.rarp.model.dao.UsuarioDAO;

public class PerfilUsuarioBusiness {

	public void salvar(PerfilUsuario perfilUsuario) throws Exception {
		if(perfilUsuario == null)
			throw new Exception("O perfil de usu�rio n�o foi inst�nciado");
			
		if(perfilUsuario.isStatus())
			validarPerfilUsuario(perfilUsuario);
		else
			validarDesativacao(perfilUsuario);
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
		perfilUsuarioDAO.salvar(perfilUsuario);
		if(SistemaCtrl.getInstance().getUsuarioSessao() != null)
			SistemaCtrl.getInstance().setUsuarioSessao(new UsuarioDAO().getUsuario(SistemaCtrl.getInstance().getUsuarioSessao().getCodigo()));
	}

	private void validarDesativacao(PerfilUsuario perfilUsuario) throws Exception {
		if(new UsuarioDAO().consultar("codigo_perfilusuario", " = ", perfilUsuario.getCodigo() + "").size() > 0)
			throw new Exception("N�o � poss�vel desativar um perfil de usu�rio que possui usu�rios");
	}

	private void validarPerfilUsuario(PerfilUsuario perfilUsuario) {
		// valida o perfil de usu�rio
	}

	public List<PerfilUsuario> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
		return perfilUsuarioDAO.consultar(campo, comparacao, termo);
	}

}
