package br.com.rarp.model;

import java.util.List;

/**
 * 
 * @author rober
 *classe edico
 */
public class Medico extends Funcionario {

	private String CRM;
	private List<Especialidade> especialidades;
	public String getCRM() {
		return CRM;
	}
	public void setCRM(String cRM) {
		CRM = cRM;
	}
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	
}
