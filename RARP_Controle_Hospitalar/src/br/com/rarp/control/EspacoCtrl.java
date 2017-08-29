package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.bo.EspacoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EspacoCtrl {
	private Espaco espaco;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		EspacoBusiness espacoBusiness = new EspacoBusiness();
		return FXCollections.observableList(espacoBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void novoEspaco() {
		espaco = new Espaco();
	}
	
	public boolean salvar() throws Exception {
		if(espaco == null)
			throw new Exception("O espa�o n�o foi inst�nciado");
		
		if(confirmarDesativacao()) {
			if(espaco.isStatus())
				validarDadosObrigatorios();
			EspacoBusiness espacoBusiness = new EspacoBusiness();
			espacoBusiness.salvar(espaco);
			return true;
		} else {
			return false;
		}
	}
	
	private void validarDadosObrigatorios() throws Exception {
		if(espaco.getNome().isEmpty()) 
			throw new Exception("Para cadastrar um espa�o � necess�rio informar o nome");
		if(espaco.getAndar().isEmpty())
			throw new Exception("Para cadastrar um espa�o � necess�rio informar o andar. Dica: Coloque 1 caso s� possua um andar.");
		if(espaco.getBloco().isEmpty())
			throw new Exception("Para cadastrar um espa�o � necess�rio informar o bloco. Dica: Coloque 1 caso s� possua um bloco");
		if(espaco.getLeitos().size() == 0) 
			throw new Exception("Para cadastrar um espa�o � necess�rio informar pelo menos um leito.");
	}

	public void setUsuario(Object object) {
		this.espaco = (Espaco) object;
	}
	
	private boolean confirmarDesativacao() {
		if(espaco != null && !espaco.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este espa�o?");
		return true;
	}
	
}
