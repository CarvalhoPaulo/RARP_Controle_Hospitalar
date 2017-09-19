package br.com.rarp.model;

public class Encaminhamento extends Movimentacao {

	private Leito origem;
	private Leito destino;
	
	public Leito getDestino() {
		return destino;
	}
	public void setDestino(Leito destino) {
		this.destino = destino;
	}
	public Leito getOrigem() {
		return origem;
	}
	public void setOrigem(Leito origem) {
		this.origem = origem;
	}

}
