package br.com.rarp.control;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.dao.EspecialidadeDAO;

public class EspecialidadeCtrl {
	private Especialidade especialida;

	public Especialidade getEspecialida() {
		return especialida;
	}

	public void setEspecialida(Especialidade especialida) {
		this.especialida = especialida;
	}
	
	private void validarDadosObrigatorios() throws Exception {
		if (especialida == null ){
			throw new  Exception("Favor criar nova Especialidade");
		}
		
		if (especialida.getNome().equals("")) {
			throw new  Exception("Favor Inserir um nome");
		}
		
		
	}
	
	public void salvar() throws Exception {
		validarDadosObrigatorios();
		
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		
		if (this.especialida.getCodigo() > 0) {
			especialidadeDAO.alterar(this.especialida);
		}else {
			especialidadeDAO.inserir(this.especialida);
		}

		
		
	}
	
	public void novaEspecialidade() {
		this.especialida  = new Especialidade();
	}

}
