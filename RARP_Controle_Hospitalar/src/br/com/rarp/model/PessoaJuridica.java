package br.com.rarp.model;

public class PessoaJuridica extends Pessoa {

	private String cnpj;

	public String getCnpj() {
		String cnpj = getCnpjSemMascara();
		
		return String.format("%s.%s.%s/%s-%s",
				cnpj.substring(0, 2), 
				cnpj.substring(2, 5), 
				cnpj.substring(5, 8), 
				cnpj.substring(8, 12), 
				cnpj.substring(12, 14));
	}
	
	public String getCnpjSemMascara() {
		return cnpj.replaceAll("[\\D]", "");
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj.replaceAll("[\\D]", "");;
	}
	

}
