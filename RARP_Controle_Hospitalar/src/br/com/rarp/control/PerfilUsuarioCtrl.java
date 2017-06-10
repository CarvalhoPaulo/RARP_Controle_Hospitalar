package br.com.rarp.control;

import java.sql.SQLException;
import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.bo.PerfilUsuarioBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerfilUsuarioCtrl extends Object {
	PerfilUsuario perfilUsuario;

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}
	
	public void novoPerfilUsuario() {
		perfilUsuario = new PerfilUsuario();
	}

	public void salvar() throws Exception {
		PerfilUsuarioBusiness perfilUsuarioBusiness = new PerfilUsuarioBusiness();
		perfilUsuarioBusiness.salvar(perfilUsuario);
	}

	public ObservableList<PerfilUsuario> consultar(Campo campo, Comparacao comparacao, String termo) throws SQLException, Exception {
		PerfilUsuarioBusiness perfilUsuarioBusiness = new PerfilUsuarioBusiness();
		return FXCollections.observableArrayList(perfilUsuarioBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public void setPerfilUsuario(Object perfilUsuario) {
		this.perfilUsuario = (PerfilUsuario) perfilUsuario;
	}

}
