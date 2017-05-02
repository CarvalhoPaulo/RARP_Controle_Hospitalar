package br.com.rarp.model;

public class Tela {

	private int codigo;
	private String nome;
	private boolean podeInserir;
	private boolean podeAlterar;
	private boolean podeVisualizar;
	private boolean podeAtivar;
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
	public boolean isPodeInserir() {
		return podeInserir;
	}
	public void setPodeInserir(boolean podeInserir) {
		this.podeInserir = podeInserir;
	}
	public boolean isPodeAlterar() {
		return podeAlterar;
	}
	public void setPodeAlterar(boolean podeAlterar) {
		this.podeAlterar = podeAlterar;
	}
	public boolean isPodeVisualizar() {
		return podeVisualizar;
	}
	public void setPodeVisualizar(boolean podeVisualizar) {
		this.podeVisualizar = podeVisualizar;
	}
	public boolean isPodeAtivar() {
		return podeAtivar;
	}
	public void setPodeAtivar(boolean podeAtivar) {
		this.podeAtivar = podeAtivar;
	}

	
}
