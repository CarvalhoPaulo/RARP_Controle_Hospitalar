package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.bo.EspecialidadeBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EspecialidadeCtrl {
	private Especialidade especialidade;

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Object especialida) {
		this.especialidade = (Especialidade) especialida;
	}
	
	private void validaCamposObrigatorios() throws Exception {
		if (especialidade == null ){
			throw new  Exception("Favor criar nova Especialidade");
		}
		
		if (especialidade.getNome().equals("")) {
			throw new  Exception("Favor Inserir um nome");
		}
	}
	
	public boolean salvar() throws Exception {
		if (verificarDesativacao()) {
			EspecialidadeBusiness especialidadeBusiness = new EspecialidadeBusiness();
			validaCamposObrigatorios();
			especialidadeBusiness.salvar(especialidade);
			return true;
		} else {
			return false;
		}	
	}
	
	private boolean verificarDesativacao() {
		if(!especialidade.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar este Especialidade ?");
		return true;
	}

	public void novaEspecialidade() {
		this.especialidade  = new Especialidade();
	}

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		// TODO Auto-generated method stub
		
		EspecialidadeBusiness especialidadeBusiness = new EspecialidadeBusiness();
		return FXCollections.observableArrayList(especialidadeBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	
	}
	
	@SuppressWarnings("rawtypes")
	private  ObservableList<Especialidade> getEsepecialidades() throws Exception {
		EspecialidadeBusiness especialidadeBusiness = new EspecialidadeBusiness();
		return FXCollections.observableArrayList(especialidadeBusiness.consultar("status", " = ", "true"));
	
	}

}
