package br.com.rarp.model.bo;

import br.com.rarp.model.Funcionario;
import br.com.rarp.model.dao.FuncionarioDAO;

public class FuncionarioBusiness {

	public void salvar(Funcionario funcionario) throws Exception {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
	}

}
