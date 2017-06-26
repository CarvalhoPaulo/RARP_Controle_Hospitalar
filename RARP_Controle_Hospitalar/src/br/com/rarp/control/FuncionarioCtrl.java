package br.com.rarp.control;

import br.com.rarp.model.Funcionario;
import br.com.rarp.model.bo.FuncionarioBusiness;

public class FuncionarioCtrl {
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void salvar() throws Exception {
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		validarDadosObrigatorios();
		funcionarioBusiness.salvar(funcionario);
	}

	private void validarDadosObrigatorios() throws Exception {
		if (funcionario.getNome().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o nome");
		}
		if (funcionario.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o CPF");
		}
		if (funcionario.getCTPS().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o CTPS");
		}
		if (funcionario.getLogradouro().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o endereço");
		}
		if (funcionario.getCargo().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o cargo");
		}
		if (funcionario.getDtAdmissao().toString().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar a data de admissão");
		}
		if (funcionario.getRg().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o RG");
		}
		if (funcionario.getSexo().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o sexo");
		}
		if (funcionario.getCidade().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar uma cidade");
		}
		if (funcionario.getBairro().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar um bairro");
		}
		if (funcionario.getDtNascimento().toString().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar a data de nascimento");
		}
		if (funcionario.getNumero().equals("")) {
			throw new Exception("Para cadastrar um funcionário é necessário informar o número da casa");
		}
		// if (funcionario.getSalarioContratual() == null) {
		// throw new Exception("Para cadastrar um funcionário é necessário
		// informar o salário");
		// }
		if (!funcionario.isPossuiNecessidades()) {
			throw new Exception("Para cadastrar um funcionário é necessário informar se possui necessidades");
		}
	}

	public void novoFuncionario() {
		funcionario = new Funcionario();
	}
}
