package br.com.rarp.control;

import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.bo.PerfilUsuarioBusiness;

public class PerfilUsuarioCtrl extends Object {
	PerfilUsuario perfilUsuario;

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}
	
	public void novoPerfilUsuario() {
		perfilUsuario = new PerfilUsuario();
	}

	public void salvar() throws Exception {
		PerfilUsuarioBusiness perfilUsuarioBusiness = new PerfilUsuarioBusiness();
		perfilUsuarioBusiness.salvar(perfilUsuario);
	}

}
