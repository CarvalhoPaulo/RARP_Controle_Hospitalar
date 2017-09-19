package br.com.rarp.control;

import br.com.rarp.annotations.Objeto;
import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.bo.EntradaPacienteBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.ObservableList;

public class EntradaPacienteCtrl {

	@Objeto
	private EntradaPaciente entradaPaciente;

	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}

	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}

	public ObservableList<EntradaPaciente> consultar(Campo selectedItem, Comparacao selectedItem2, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntradaPaciente(Object entradaPaciente) {
		this.entradaPaciente = (EntradaPaciente) entradaPaciente;
	}

	public boolean salvar() throws Exception {
		if (entradaPaciente == null)
			throw new Exception("A entrada de paciente não foi instânciada");

		if (confirmarDesativacao()) {
			if (entradaPaciente.isStatus())
				validarDadosObrigatorios();
			EntradaPacienteBusiness entradaPacienteBusiness = new EntradaPacienteBusiness();
			entradaPacienteBusiness.salvar(entradaPaciente);
			return true;
		} else {
			return false;
		}
	}

	private void validarDadosObrigatorios() throws Exception {
		if (entradaPaciente != null) {
			if (entradaPaciente.getDtMovimentacao() == null)
				throw new Exception("Para cadastrar uma entrada de paciente é necessário informar a data");

			if (entradaPaciente.getHrMovimentacao() == null)
				throw new Exception("Para cadastrar uma entrada de paciente é necessário informar a hora");

			if (entradaPaciente.getAtendente() == null)
				throw new Exception("Para cadastrar uma entrada de paciente é necessário informar a atendente");
			
			if (entradaPaciente.getPaciente() == null)
				throw new Exception("Para cadastrar uma entrada de paciente é necessário informar o paciente");
		}
	}

	private boolean confirmarDesativacao() {
		if(entradaPaciente != null && !entradaPaciente.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar esta entrada de paciente?");
		return true;
	}

	public void novaEntradaPaciente() {
		entradaPaciente = new EntradaPaciente();
	}

}
