package br.com.rarp.model;

import java.util.Date;

import br.com.rarp.annotations.Coluna;

public class Movimentacao {

	@Coluna(posicao=0, descricao="Código")
	private int codigo;
	
	@Coluna(posicao=1, descricao="Data")
	private Date dtMovimentacao;
	
	@Coluna(posicao=2, descricao="Hora")
	private Date hrMovimentacao;
	private Usuario usuario;
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
	
	

}
