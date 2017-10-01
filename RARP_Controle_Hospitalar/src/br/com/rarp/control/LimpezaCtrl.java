package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Limpeza;
import br.com.rarp.utils.Campo;
import javafx.collections.ObservableList;

public class LimpezaCtrl {
	private Limpeza limpeza;

	public Limpeza getLimpeza() {
		return limpeza;
	}
	
	public void novaLimpeza() {
		limpeza = new Limpeza();
	}
	
	public boolean salvar() {
		return true;
	}
	
	public ObservableList<Limpeza> consultar(Campo campo, Comparacao comparacao, String termo) {
		return null;
	}

	public void setLimpeza(Object limpeza) {
		this.limpeza = (Limpeza) limpeza;
	}
}
