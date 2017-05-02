package br.com.rarp.model;

import java.util.List;

public class PerfilUsuario {

	private int codigo;
	private String nome;
	private List<Tela> telas;
	private List<Usuario> usuarios;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Tela> getTelas() {
		return telas;
	}
	public void setTelas(List<Tela> telas) {
		this.telas = telas;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
}
