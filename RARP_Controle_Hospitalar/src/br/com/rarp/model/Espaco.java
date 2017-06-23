package br.com.rarp.model;

import java.util.List;

public class Espaco {

	private int codigo;
	private int numero;
	private String bloco;
	private String andar;
	private List<Leito> leitos;
	private boolean status;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getAndar() {
		return andar;
	}
	public void setAndar(String andar) {
		this.andar = andar;
	}
	public List<Leito> getLeitos() {
		return leitos;
	}
	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
