package br.com.rarp.model;

import java.util.Currency;
import java.util.Date;

public class Funcionario extends PessoaFisica {

	// Primeiro commit testando

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
