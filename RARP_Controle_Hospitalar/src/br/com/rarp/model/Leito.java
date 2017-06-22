package br.com.rarp.model;

public class Leito {

	private int codigo;
	private int numero;
	
	public Leito(int numero) {
		this.numero = numero;
	}
	
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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return numero == ((Leito) obj).getNumero();
	}
}
