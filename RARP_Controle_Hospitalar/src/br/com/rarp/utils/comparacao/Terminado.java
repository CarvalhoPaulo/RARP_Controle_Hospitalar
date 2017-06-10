package br.com.rarp.utils.comparacao;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.utils.Utilitarios;

public class Terminado implements Comparacao {

	@Override
	public String getComparacao() {
		return " LIKE ";
	}

	@Override
	public String getTermo(String termo) {
		return "'%" + Utilitarios.formatStringSQL(termo) + "'";
	}

	@Override
	public String toString() {
		return "Terminado";
	}

}
