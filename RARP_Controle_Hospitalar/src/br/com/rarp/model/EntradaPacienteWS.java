package br.com.rarp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntradaPacienteWS extends EntradaPaciente {
	private String Hospital;
	
	private String Descrioes;

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getDescrioes() {
		return Descrioes;
	}

	public void setDescrioes(String descrioes) {
		Descrioes = descrioes;
	}
	
}
