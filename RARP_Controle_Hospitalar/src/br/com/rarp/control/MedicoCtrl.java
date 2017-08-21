package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Medico;
import br.com.rarp.model.bo.MedicoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedicoCtrl {
	Medico a;
	private Medico medico;

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico  = medico;
	}
	
	private void validaCamposObrigatorios() throws Exception {
		if (medico == null ){
			throw new  Exception("Favor criar nova Medico");
		}
		
		if (medico.getNome().equals("")) {
			throw new  Exception("Favor Inserir um nome");
		}
	}
	
	public boolean salvar() throws Exception {
		if (verificarDesativacao()) {
			MedicoBusiness medicoBusiness = new MedicoBusiness();
			validaCamposObrigatorios();
			medicoBusiness.salvar(medico);
			return true;
		} else {
			return false;
		}	
	}
	
	private boolean verificarDesativacao() {
		if(!medico.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar este cargo?");
		return true;
	}

	public void novoMedico() {
		this.medico  = new Medico();
	}

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		// TODO Auto-generated method stub
		
		MedicoBusiness MedicoBusiness = new MedicoBusiness();
		return FXCollections.observableArrayList(MedicoBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	
	}

}
