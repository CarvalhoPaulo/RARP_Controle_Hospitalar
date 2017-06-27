package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.bo.FuncionarioBusiness;
import br.com.rarp.utils.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioCtrl {
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setFuncionario(Object object) {
		this.funcionario = (Funcionario) object;
	}

	public void salvar() throws Exception {
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		validarDadosObrigatorios();
		funcionarioBusiness.salvar(funcionario);
	}

	public ObservableList consultar(Campo campo, Comparacao comparacao, String termo) throws Exception {
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		return FXCollections.observableList(funcionarioBusiness.consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}

	private void validarDadosObrigatorios() throws Exception {
		if (funcionario.getNome().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o nome");
		}
		if (funcionario.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o CPF");
		}
		// if (funcionario.getCargo().equals("")) {
		// throw new Exception("Para cadastrar um funcionário é necessário
		// informar o cargo");
		// }
		if (funcionario.getDtAdmissao().toString().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar a data de admissão");
		}

		if (funcionario.getSexo().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o sexo");
		}
	}

	public void novoFuncionario() {
		funcionario = new Funcionario();
	}
}
