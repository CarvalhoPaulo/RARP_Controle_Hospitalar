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
		Funcionario f = new Funcionario();
		f.setBairro(getBairro());
		f.setCargo(getCargo());
		f.setCep(getCep());
		f.setCertidaoNascimento(getCertidaoNascimento());
		f.setCidade(getCidade());
		f.setCodigo(getCodigo());
		f.setComplemento(getComplemento());
		f.setCpf(getCpf());
		f.setCTPS(getCTPS());
		f.setDtAdmissao(getDtAdmissao());
		f.setDtNascimento(getDtAdmissao());
		f.setLogradouro(getLogradouro());
		f.setNome(getNome());
		f.setNumero(getNumero());
		f.setPossuiNecessidades(isPossuiNecessidades());
		f.setRg(getRg());
		f.setSalarioContratual(getSalarioContratual());
		f.setSexo(getSexo());
		f.setStatus(isStatus());
		f.setTelefones(getTelefones());
		return f;
	}

	public String getCTPS() {
		return CTPS;
	}

	public void setCTPS(String cTPS) {
		CTPS = cTPS;
	}
}
