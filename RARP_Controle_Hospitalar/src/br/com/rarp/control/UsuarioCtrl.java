package br.com.rarp.control;

import br.com.rarp.model.Usuario;

public class UsuarioCtrl {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void Salvar() {
		
	}
	
	public void novoUsuario() {
		usuario = new Usuario();
	}
}
