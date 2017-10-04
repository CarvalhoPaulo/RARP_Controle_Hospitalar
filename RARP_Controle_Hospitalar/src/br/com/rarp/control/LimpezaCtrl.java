package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Limpeza;
import br.com.rarp.model.bo.LimpezaBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.ObservableList;

public class LimpezaCtrl {
	private Limpeza limpeza;

	public Limpeza getLimpeza() {
		return limpeza;
	}
	
	public void novaLimpeza() {
		limpeza = new Limpeza();
	}
	
	public boolean salvar() throws Exception {
		if (limpeza == null)
			throw new Exception("A limpeza n�o foi inst�nciada");
		
		if (confirmarDesativacao()) {
			if (limpeza.isStatus())
				validarDadosObrigatorios();
			LimpezaBusiness limpezaBusiness = new LimpezaBusiness();
			limpezaBusiness.salvar(limpeza);
			return true;
		} else {
			return false;
		}
	}
	
	private void validarDadosObrigatorios() throws Exception {
		if(limpeza.getHrMovimentacao() == null)
			throw new Exception("Para realizar uma limpeza � necess�rio informar o hor�rio da limpeza");
		if(limpeza.getDtMovimentacao() == null)
			throw new Exception("Para realizar uma limpeza � necess�rio informar a data da limpeza");
		if(limpeza.getLeitos() == null || limpeza.getLeitos().size() == 0)
			throw new Exception("Para realizar uma limpeza � necess�rio selecionar ao menos um leito");
		if(limpeza.getFuncionarioLimpeza() == null)
			throw new Exception("Para realizar uma limpeza � necess�rio informar o funcion�rio de limpeza");
	}

	private boolean confirmarDesativacao() {
		if(limpeza != null && !limpeza.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar esta entrada de paciente?");
		return true;
	}

	public ObservableList<Limpeza> consultar(Campo campo, Comparacao comparacao, String termo) {
		return null;
	}

	public void setLimpeza(Object limpeza) {
		this.limpeza = (Limpeza) limpeza;
	}
}
