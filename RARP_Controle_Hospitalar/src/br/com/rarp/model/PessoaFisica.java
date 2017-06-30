package br.com.rarp.model;

public class PessoaFisica extends Pessoa {

	private String cpf;
	private String rg;
	private String sexo;
	private boolean possuiNecessidades;
	private String certidaoNascimento;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public boolean isPossuiNecessidades() {
		return possuiNecessidades;
	}
	public void setPossuiNecessidades(boolean possuiNecessidades) {
		this.possuiNecessidades = possuiNecessidades;
	}
	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}
	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}
	public PessoaFisica clone() throws CloneNotSupportedException {
		return (PessoaFisica) super.clone();
	}
}
