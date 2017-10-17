package br.com.rarp.model;

public class Organizacao extends PessoaJuridica{
	private static Organizacao INSTANCE = new Organizacao();
	

	

	private Organizacao() {
		
	}
	public static Organizacao getINSTANCE() {
		return INSTANCE;
	}
	

}
