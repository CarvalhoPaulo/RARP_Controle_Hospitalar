package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.bo.EncaminhamentoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.ObservableList;

public class EncaminhamentoCtrl {
	
	private Encaminhamento encaminhamento;

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Object encaminhamento) {
		this.encaminhamento = (Encaminhamento) encaminhamento;
	}

	public boolean salvar(EncaminhamentoCtrl encaminhamentoCtrl) throws Exception {
		if (encaminhamento == null)
			throw new Exception("O encaminhamento não foi instânciada");
		
		Encaminhamento encaminhamentoAntigo = null;
		if (encaminhamentoCtrl != null)
			encaminhamentoAntigo = encaminhamentoCtrl.getEncaminhamento();
		
		if (confirmarDesativacao()) {
			if (encaminhamento.isStatus())
				validarDadosObrigatorios();
			EncaminhamentoBusiness encaminhamentoBusiness = new EncaminhamentoBusiness();
			encaminhamentoBusiness.salvar(encaminhamento, encaminhamentoAntigo);
			return true;
		} else {
			return false;
		}
	}
	
	public EncaminhamentoCtrl clone() {
		EncaminhamentoCtrl encaminhamentoCtrl = new EncaminhamentoCtrl();
		encaminhamentoCtrl.setEncaminhamento(encaminhamento.clone());
		return encaminhamentoCtrl;
	}

	private void validarDadosObrigatorios() {
		// TODO Auto-generated method stub
		
	}

	private boolean confirmarDesativacao() {
		if(encaminhamento != null && !encaminhamento.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar esta entrada de paciente?");
		return true;
	}

	public void novoEncaminhamento() {
		encaminhamento = new Encaminhamento();
	}

	public ObservableList<Encaminhamento> consultar(Campo selectedItem, Comparacao selectedItem2, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
