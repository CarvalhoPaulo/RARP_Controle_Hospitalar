package br.com.rarp.model;

import java.util.Date;

public class Funcionario extends PessoaFisica {

	private Date dtAdmissao;
	private double salarioContratual;
	private Cargo cargo;
	private String CTPS;

	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public double getSalarioContratual() {
		return salarioContratual;
	}

	public void setSalarioContratual(double salarioContratual) {
		this.salarioContratual = salarioContratual;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public Funcionario clone() throws CloneNotSupportedException {
		return (Funcionario) super.clone();
	}

	public String getCTPS() {
		return CTPS;
	}

	public void setCTPS(String cTPS) {
		CTPS = cTPS;
	}
}
