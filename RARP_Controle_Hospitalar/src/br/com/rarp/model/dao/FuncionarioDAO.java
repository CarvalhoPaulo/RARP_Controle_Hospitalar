package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class FuncionarioDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("pessoafisica"))
			throw new Exception("Crie a tabela de pessoa fisica antes de criar a tabela de funcionarios");
		
		if(!SistemaCtrl.getInstance().tabelaExiste("cargo"))
			throw new Exception("Crie a tabela de cargo antes de criar a tabela de funcionarios");
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "funcionario(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_pf INTEGER REFERENCES pessoaFisica(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

}
