package br.com.rarp.model;

public class Paciente extends PessoaFisica {
	private Convenio convenio;
	private Paciente responsavel;

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Paciente getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Paciente responsavel) {
		this.responsavel = responsavel;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}
	
}
