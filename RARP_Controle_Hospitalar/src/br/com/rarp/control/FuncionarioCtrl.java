package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.bo.FuncionarioBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioCtrl {
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Object object) {
		this.funcionario = (Funcionario) object;
	}

	public void salvar() throws Exception {
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		if (verificarDesativacao()) {
			validarDadosObrigatorios();
			funcionarioBusiness.salvar(funcionario);
		}
	}

	@SuppressWarnings("rawtypes")
	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		return FXCollections.observableList(
				funcionarioBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	private void validarDadosObrigatorios() throws Exception {
		if (funcionario.getNome().equals("")) {
			throw new Exception("Para cadastrar um funcion�rio � necess�rio informar o nome");
		}
		
		if (funcionario.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um funcion�rio � necess�rio informar o CPF");
		}
		
		if (funcionario.getCargo().equals("")) {
			 throw new Exception("Para cadastrar um funcion�rio � necess�rio informar o cargo");
		}
		
		if (funcionario.getDtAdmissao().toString().equals("")) {
			throw new Exception("Para cadastrar um funcion�rio � necess�rio informar a data de admiss�o");
		}

		if (funcionario.getSexo().equals("")) {
			throw new Exception("Para cadastrar um funcion�rio � necess�rio informar o sexo");
		}
	}
	
	private boolean verificarDesativacao() {
		if(!funcionario.isStatus())
			return Utilitarios.pergunta("Tem certeza que voc� deseja desativar este funcion�rio?");
		return true;
	}

	public void novoFuncionario() {
		funcionario = new Funcionario();
	}
}
