package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.bo.EspacoBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EspacoCtrl {
	private Espaco espaco;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		EspacoBusiness espacoBusiness = new EspacoBusiness();
		return FXCollections.observableList(espacoBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void novoEspaco() {
		espaco = new Espaco();
	}
	
	public void salvar() throws Exception {
		EspacoBusiness espacoBusiness = new EspacoBusiness();
		espacoBusiness.salvar(espaco);
	}

	public void setUsuario(Object object) {
		this.espaco = (Espaco) object;
	}
	
}
