package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Encaminhamento;
import br.com.rarp.utils.Campo;
import javafx.collections.ObservableList;

public class EncaminhamentoCtrl {
	private Encaminhamento encaminhamento;

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Object encaminhamento) {
		this.encaminhamento = (Encaminhamento) encaminhamento;
	}

	public boolean salvar() {
		// TODO Auto-generated method stub
		return false;
	}

	public void novoEncaminhamento() {
		encaminhamento = new Encaminhamento();
	}

	public ObservableList<Encaminhamento> consultar(Campo selectedItem, Comparacao selectedItem2, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
