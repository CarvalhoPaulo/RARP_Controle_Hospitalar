package br.com.rarp.control;

import br.com.rarp.model.Funcionario;

public class FuncionarioCtrl {
	private Funcionario funcinario;

	public Funcionario getFuncinario() {
		return funcinario;
	}

	public void setFuncinario(Funcionario funcinario) {
		this.funcinario = funcinario;
	}

	public void salvar() {

	}

	public void novoFuncionario() {
		funcinario = new Funcionario();
	}
}
