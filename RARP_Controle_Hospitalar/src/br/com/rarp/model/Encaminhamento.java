package br.com.rarp.model;

public class Encaminhamento extends Movimentacao {

	private Espaco espacoOrigem;
	private Espaco espacoDestino;
	
	public Espaco getEspacoOrigem() {
		return espacoOrigem;
	}
	public void setEspacoOrigem(Espaco espacoOrigem) {
		this.espacoOrigem = espacoOrigem;
	}
	public Espaco getEspacoDestino() {
		return espacoDestino;
	}
	public void setEspacoDestino(Espaco espacoDestino) {
		this.espacoDestino = espacoDestino;
	}

}
