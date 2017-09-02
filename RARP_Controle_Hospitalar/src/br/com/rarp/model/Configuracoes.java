package br.com.rarp.model;

public class Configuracoes {
	private static Configuracoes INSTANCE = new Configuracoes();
	
	private boolean controleAcesso;
	private String Usuario;
//	private String Senha;
	private Configuracoes() {
		
	}
	
	public static Configuracoes getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(Configuracoes iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	
	public boolean isControleAcesso() {
		return controleAcesso;
	}

	public void setControleAcesso(boolean controleAcesso) {
		this.controleAcesso = controleAcesso;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public static Configuracoes getInstance() {
		return INSTANCE;
	}
	
	
}
