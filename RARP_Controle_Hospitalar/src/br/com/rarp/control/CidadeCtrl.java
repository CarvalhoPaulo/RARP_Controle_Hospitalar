package br.com.rarp.control;

import java.sql.SQLException;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.bo.CidadeBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CidadeCtrl {
	
	private Cidade cidade;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws SQLException, Exception {
		return FXCollections.observableList(new CidadeBusiness().consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public boolean salvar() throws Exception {
		if(cidade == null)
			throw new Exception("A cidade n�o foi inst�nciada");
		if(confirmarDesativacao()) {
			CidadeBusiness cidadeBusiness = new CidadeBusiness();
			if(cidade.isStatus())
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
				throw new Exception("Para cadastrar uma cidade � necess�rio informar o nome");
			if(cidade.getEstado() == null)
				throw new Exception("Para cadastrar uma cidade � necess�rio informar o estado");
		}
	}

	private boolean confirmarDesativacao() {
		if(cidade != null && !cidade.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este conv�nio?");
		return true;
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
