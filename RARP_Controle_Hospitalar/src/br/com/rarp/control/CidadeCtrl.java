package br.com.rarp.control;

import br.com.rarp.model.Cidade;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.comparacao.Ativado;
import javafx.collections.ObservableList;

public class CidadeCtrl {
	
	private Cidade cidade;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Ativado ativado, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean salvar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void novaCidade() {
		cidade = new Cidade();
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


}
