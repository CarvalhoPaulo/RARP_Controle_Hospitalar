package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Convenio;
import br.com.rarp.model.bo.ConvenioBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConvenioCtrl {
	
	private Convenio convenio;

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		ConvenioBusiness convenioBusiness = new ConvenioBusiness();
		return FXCollections.observableList(
				convenioBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Object convenio) {
		this.convenio = (Convenio) convenio;
	}

	public void novoConvenio() {
		convenio = new Convenio();
	}

	public boolean salvar() throws Exception {
		if (verificarDesativacao()) {
			ConvenioBusiness convenioBusiness = new ConvenioBusiness();
			validarDadosObrigatorios();
			convenioBusiness.salvar(convenio);
			return true;
		} else {
			return false;
		}
	}

	private void validarDadosObrigatorios() throws Exception {
		if (convenio == null) 
			novoConvenio();

		if (convenio.getNome().isEmpty()) {
			throw new Exception("Para cadastrar um paciente � necess�rio informar o nome");
		}
		if (convenio.getCnpj().isEmpty()) {
			throw new Exception("Para cadastrar um paciente � necess�rio informar o CNPJ");
		}
	}
	
	private boolean verificarDesativacao() {
		if(convenio != null && !convenio.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este conv�nio?");
		return true;
	}

}