package br.com.rarp.model;

import java.util.Date;

public class Movimentacao {

	private int codigo;
	private Date dtMovimentacao;
	private Date hrMovimentacao;
	private Usuario usuario;
	private boolean status;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDtMovimentacao() {
		return dtMovimentacao;
	}
	public void setDtMovimentacao(Date dtMovimentacao) {
		this.dtMovimentacao = dtMovimentacao;
	}
	public Date getHrMovimentacao() {
		return hrMovimentacao;
	}
	public void setHrMovimentacao(Date hrMovimentacao) {
		this.hrMovimentacao = hrMovimentacao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
