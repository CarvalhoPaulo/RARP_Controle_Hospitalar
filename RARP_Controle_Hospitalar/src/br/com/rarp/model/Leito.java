package br.com.rarp.model;

public class Leito {

	private int codigo;
	private int numero;
	private boolean status;
	private Paciente paciente;
	
	public Leito(int numero) {
		this.numero = numero;
		this.status = true;
	}
	
	public Leito() {
		this.status = true;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
