package br.com.rarp.model;

import java.util.List;

public class Espaco {

	private int codigo;
	private int numero;
	private String bloco;
	private int andar;
	private List<Leito> leitos;
	private List<Encaminhamento> encaminhamentos;
	private List<Limpeza> limpezas;
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
	public int getAndar() {
		return andar;
	}
	public void setAndar(int andar) {
		this.andar = andar;
	}
	public List<Leito> getLeitos() {
		return leitos;
	}
	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}
	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}
	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}
	public List<Limpeza> getLimpezas() {
		return limpezas;
	}
	public void setLimpezas(List<Limpeza> limpezas) {
		this.limpezas = limpezas;
	}

}
