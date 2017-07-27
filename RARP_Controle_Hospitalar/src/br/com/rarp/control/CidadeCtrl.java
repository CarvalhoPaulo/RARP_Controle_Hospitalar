package br.com.rarp.control;

import java.sql.SQLException;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.bo.CidadeBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CidadeCtrl {
	
	private Cidade cidade;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws SQLException, Exception {
		return FXCollections.observableList(new CidadeBusiness().consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
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

	public void setCidade(Object object) {
		this.cidade = (Cidade) object;
	}


}
