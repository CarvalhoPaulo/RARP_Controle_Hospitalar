package br.com.rarp.enums;

public enum StatusAtendimento {
	emAberto("EmAberto"), emAndamento("EmAndamento"), realizado("Realizado");
	
	private String label;

	private StatusAtendimento(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return label;
	}
}
