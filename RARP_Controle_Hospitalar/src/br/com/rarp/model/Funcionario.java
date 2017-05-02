package br.com.rarp.model;

import java.util.Date;
import java.util.Currency;

public class Funcionario extends PessoaFisica {

	private Date dtUltimaAdmissao;
	private Currency salarioContratual;
	private Cargo cargo;
	public Date getDtUltimaAdmissao() {
		return dtUltimaAdmissao;
	}
	public void setDtUltimaAdmissao(Date dtUltimaAdmissao) {
		this.dtUltimaAdmissao = dtUltimaAdmissao;
	}
	public Currency getSalarioContratual() {
		return salarioContratual;
	}
	public void setSalarioContratual(Currency salarioContratual) {
		this.salarioContratual = salarioContratual;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	

}
