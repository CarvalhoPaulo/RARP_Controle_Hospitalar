package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.bo.EncaminhamentoBusiness;
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

	public boolean salvar() throws Exception {
		if (encaminhamento == null)
			throw new Exception("O encaminhamento não foi instânciada");

		if (confirmarDesativacao()) {
			if (encaminhamento.isStatus())
				validarDadosObrigatorios();
			EncaminhamentoBusiness encaminhamentoBusiness = new EncaminhamentoBusiness();
			encaminhamentoBusiness.salvar(encaminhamento);
			return true;
		} else {
			return false;
		}
	}

	private void validarDadosObrigatorios() {
		// TODO Auto-generated method stub
		
	}

	private boolean confirmarDesativacao() {
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
