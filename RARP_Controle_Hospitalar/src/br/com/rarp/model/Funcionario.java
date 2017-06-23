package br.com.rarp.model;

import java.util.Date;

public class Funcionario extends PessoaFisica {

	private Date dtAdmissao;
	private double salarioContratual;
	private Cargo cargo;
	private boolean status;
	private String estadoCivil;

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
