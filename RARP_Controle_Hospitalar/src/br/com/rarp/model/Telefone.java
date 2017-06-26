package br.com.rarp.model;

public class Telefone {

	private int Codigo;
	private String numero;

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return numero == ((Telefone) obj).getNumero();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return numero;
	}
}
