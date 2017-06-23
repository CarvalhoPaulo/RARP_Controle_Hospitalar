package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.bo.EspacoBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.ObservableList;

public class EspacoCtrl {
	private Espaco espaco;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void novoEspaco() {
		
	}
	
	public void salvar() {
		EspacoBusiness espacoBusiness = new EspacoBusiness();
	}

	public void setUsuario(Object object) {
		this.espaco = (Espaco) object;
	}
	
}
