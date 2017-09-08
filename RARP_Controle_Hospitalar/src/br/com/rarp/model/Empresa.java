package br.com.rarp.model;

public class Empresa {
	private static Empresa INSTANCE = new Empresa();
	
	private String cnpj;
	private String razao;
	private String telefone;
	private String  email;
	private String endereco;

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazao() {
		return razao;
	}
	public void setRazao(String razao) {
		this.razao = razao;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public static void setINSTANCE(Empresa iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	private Empresa() {
		
	}
	public static Empresa getINSTANCE() {
		return INSTANCE;
	}
	

}
