package br.com.rarp.model.bo;

import java.util.List;
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.dao.FuncionarioDAO;
import br.com.rarp.utils.Utilitarios;

public class FuncionarioBusiness {

	public void salvar(Funcionario funcionario) throws Exception {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		validarFuncionario(funcionario);
		funcionarioDAO.salvar(funcionario);
	}

	private void validarFuncionario(Funcionario funcionario) throws Exception {
		if(!Utilitarios.isCPF(funcionario.getCpfSemMascara()))
			throw new Exception("CPF inválido");
	}

	public List<Funcionario> consultar(String campo, String comparacao, String termo) throws Exception {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		return funcionarioDAO.consultar(campo, comparacao, termo);
	}
}
