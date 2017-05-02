package br.com.rarp.control;

import br.com.rarp.annotations.Objeto;
import br.com.rarp.model.EntradaPaciente;

public class EntradaPacienteCtrl {
	
	@Objeto
	private EntradaPaciente entradaPaciente;

	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}

	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}

}
