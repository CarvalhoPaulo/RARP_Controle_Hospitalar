package br.com.rarp.control;

import br.com.rarp.annotations.Objeto;
import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.utils.Campo;
import javafx.collections.ObservableList;

public class EntradaPacienteCtrl {
	
	@Objeto
	private EntradaPaciente entradaPaciente;

	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}

	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}

	public ObservableList<EntradaPaciente> consultar(Campo selectedItem, Comparacao selectedItem2, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntradaPaciente(Object entradaPaciente) {
		this.entradaPaciente = (EntradaPaciente) entradaPaciente;
	}

}
