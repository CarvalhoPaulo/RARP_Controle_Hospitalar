package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Medico;
import br.com.rarp.model.bo.MedicoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedicoCtrl {
	private Medico medico;

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico  = medico;
	}
	
	private void validaCamposObrigatorios() throws Exception {
		if (medico == null){
			throw new  Exception("Favor Selecioanr um funcionario");
		}
		
		if (medico.getNome().equals("")) {
			throw new  Exception("Favor Selecione um Funcionario");
		}
		
		if (medico.getCRM().equals("")) {
			throw new  Exception("Favor insira um CRM para o Medico");
		}
		if (medico.getCRM().length() < 8) {
			throw new  Exception("Favor insira um CRM para o valido");
		}
		
		if ((medico.getEspecialidades() == null ) || (medico.getEspecialidades().size() < 1)) {
			throw new  Exception("Favor insira um especialidade para o Medico");
		}
	}
	
	public boolean salvar() throws Exception {
		if (medico == null)
			throw new Exception("O m�dico n�o foi inst�nciado");
		
		if (confirmarDesativacao()) {
			if(medico.isStatus())
				validaCamposObrigatorios();
			MedicoBusiness medicoBusiness = new MedicoBusiness();
			medicoBusiness.salvar(medico);
			return true;
		} else {
			return false;
		}	
	}
	
	private boolean confirmarDesativacao() {
		if(!medico.isStatus())
			return Utilitarios.pergunta("Tem certeza que deseja desativar este cargo?");
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
