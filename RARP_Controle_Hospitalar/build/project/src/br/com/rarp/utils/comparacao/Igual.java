package br.com.rarp.utils.comparacao;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.utils.Utilitarios;

public class Igual implements Comparacao {

	@Override
	public String getComparacao() {
		return " = ";
	}

	@Override
	public String getTermo(String termo) throws Exception {
		if(termo.isEmpty())
			throw new Exception("O termo da consulta � obrigat�rio");
		return "'" + Utilitarios.formatStringSQL(termo) + "'";
	}

	@Override
	public String toString() {
		return "Igual";
	}

}
