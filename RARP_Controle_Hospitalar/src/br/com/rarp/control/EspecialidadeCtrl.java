package br.com.rarp.control;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.dao.EspecialidadeDAO;

public class EspecialidadeCtrl {
	private Especialidade especialidade;

	public Especialidade getEspecialida() {
		return especialidade;
	}

	public void setEspecialida(Especialidade especialida) {
		this.especialidade = especialida;
	}
	
	private void validarDadosObrigatorios() throws Exception {
		if (especialidade == null ){
			throw new  Exception("Favor criar nova Especialidade");
		}
		
		if (especialidade.getNome().equals("")) {
			throw new  Exception("Favor Inserir um nome");
		}
		 
		
	}
	
	public void salvar() throws Exception {
		validarDadosObrigatorios();
		
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		
		especialidadeDAO.salvar(especialidade);

		
		
	}
	
	public void novaEspecialidade() {
		this.especialidade  = new Especialidade();
	}

}
