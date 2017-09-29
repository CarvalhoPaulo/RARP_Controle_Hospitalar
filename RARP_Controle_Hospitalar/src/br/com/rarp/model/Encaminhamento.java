package br.com.rarp.model;

public class Encaminhamento extends Movimentacao {

	private Leito origem;
	private Leito destino;
	private EntradaPaciente entradaPaciente;
	
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
	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}
	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}

}
