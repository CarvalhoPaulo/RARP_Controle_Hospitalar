package br.com.rarp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntradaPacienteWS extends EntradaPaciente {
	private String Hospital;

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}
	
}
