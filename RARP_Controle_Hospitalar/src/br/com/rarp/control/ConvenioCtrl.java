package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.bo.ConvenioBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConvenioCtrl {

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		ConvenioBusiness convenioBusiness = new ConvenioBusiness();
		return FXCollections.observableList(
				convenioBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

}
