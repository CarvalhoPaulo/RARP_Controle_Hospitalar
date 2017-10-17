package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.UsuarioDAO;

public class UsuarioBusiness {

	public void salvar(Usuario usuario) throws Exception {
		if(usuario == null)
			throw new Exception("O usu�rio n�o foi inst�nciado");
		
		if(usuario.isStatus())
			validarUsuario(usuario);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		if(SistemaCtrl.getInstance().getUsuarioSessao() != null)
			SistemaCtrl.getInstance().setUsuarioSessao(new UsuarioDAO().getUsuario(SistemaCtrl.getInstance().getUsuarioSessao().getCodigo()));
	}

	private void validarUsuario(Usuario usuario) {
		// valida o usu�rio
	}

	public List<Usuario> consultar(String campo, String comparacao, String termo) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultar(campo, comparacao, termo);
	}

}
