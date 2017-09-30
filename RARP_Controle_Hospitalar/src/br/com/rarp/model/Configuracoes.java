package br.com.rarp.model;

import br.com.rarp.annotations.IgnorarField;

public class Configuracoes {
	@IgnorarField
	private static Configuracoes INSTANCE = new Configuracoes();
	private boolean controleAcesso;
	private String usuario;
	private String senha;
	private int codigoRARP;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private Configuracoes() {

	}

	public boolean isControleAcesso() {
		return controleAcesso;
	}

	public void setControleAcesso(boolean controleAcesso) {
		this.controleAcesso = controleAcesso;
	}

	public static Configuracoes getInstance() {
		return INSTANCE;
	}

	public int getCodigoRARP() {
		return codigoRARP;
	}

	public void setCodigoRARP(int codigoRARP) {
		this.codigoRARP = codigoRARP;
	}

}
