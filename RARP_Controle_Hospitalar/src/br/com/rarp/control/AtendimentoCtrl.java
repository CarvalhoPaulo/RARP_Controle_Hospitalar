package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Atendimento;
import br.com.rarp.utils.Campo;
import javafx.collections.ObservableList;

public class AtendimentoCtrl {
	private Atendimento atendimento;

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Object atendimento) {
		this.atendimento = (Atendimento) atendimento;
	}
	
	public void novoAtendimento() {
		atendimento = new Atendimento();
	}
	
	public boolean salvar() {
		return false;
	}

	public ObservableList<Atendimento> consultar(Campo selectedItem, Comparacao selectedItem2, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
