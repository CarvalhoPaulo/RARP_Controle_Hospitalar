package br.com.rarp.control;

import br.com.rarp.model.Cidade;
import br.com.rarp.model.bo.CidadeBusiness;
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

	public boolean salvar() throws Exception {
		if(verificarDesativacao()) {
			CidadeBusiness cidadeBusiness = new CidadeBusiness();
			validarDadosObrigatorios();
			cidadeBusiness.salvar(cidade);
			return true;
		} else {
			return false;
		}
	}
	
	private void validarDadosObrigatorios() throws Exception {
		if(cidade != null) {
			if(cidade.getNome().isEmpty())
				throw new Exception("Para cadastrar uma cidade é necessário informar o nome");
			if(cidade.getEstado() == null)
				throw new Exception("Para cadastrar uma cidade é necessário informar o estado");
		}
	}

	private boolean verificarDesativacao() {
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
