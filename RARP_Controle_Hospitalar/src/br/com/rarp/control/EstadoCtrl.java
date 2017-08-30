package br.com.rarp.control;

import java.sql.SQLException;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Estado;
import br.com.rarp.model.bo.CidadeBusiness;
import br.com.rarp.model.bo.EstadoBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstadoCtrl {
	
	private Estado estado;


	public ObservableList<Estado> getEstados() throws SQLException, Exception {
		return FXCollections.observableList(new EstadoBusiness().consultar("codigo", " > ", "0"));
	}

	public boolean salvar() throws Exception {
		return false;
	
	}
	
	private void validarDadosObrigatorios() throws Exception {

	}

	private boolean verificarDesativacao() {
		// TODO Auto-generated method stub
		return false;
	}

	public void novaCidade() {
		estado = new Estado();
	}

	


}
