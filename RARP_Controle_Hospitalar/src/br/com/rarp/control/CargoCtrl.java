package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Cargo;
import br.com.rarp.model.bo.CargoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CargoCtrl {
	
	private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Object object) {
		this.cargo = (Cargo) object;
	}

	public void novoCargo() {
		cargo = new Cargo();
	}

	public boolean salvar() throws Exception {
		if (verificarDesativacao()) {
			CargoBusiness cargoBusiness = new CargoBusiness();
			validaCamposObrigatorios();
			cargoBusiness.salvar(cargo);
			return true;
		} else {
			return false;
		}	
	}

	private void validaCamposObrigatorios() throws Exception {
		if(cargo == null)
			cargo = new Cargo();
		
		if(cargo.getNome().isEmpty())
			throw new Exception("Para cadastrar um cargo � necess�rio informar o nome");
	}

	private boolean verificarDesativacao() {
		if(!cargo.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este cargo?");
		return true;
	}

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		CargoBusiness cargoBusiness = new CargoBusiness();
		return FXCollections.observableList(cargoBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

}
