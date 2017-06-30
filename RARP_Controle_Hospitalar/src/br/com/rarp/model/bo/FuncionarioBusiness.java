package br.com.rarp.model.bo;

import java.util.List;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.dao.FuncionarioDAO;

public class FuncionarioBusiness {

	public void salvar(Funcionario funcionario) throws Exception {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
	}

	public List<Funcionario> consultar(String campo, String comparacao, String termo) throws Exception {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		return funcionarioDAO.consultar(campo, comparacao, termo);
	}
}
