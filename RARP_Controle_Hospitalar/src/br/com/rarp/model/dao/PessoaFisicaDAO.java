package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class PessoaFisicaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("pessoa"))
			throw new Exception("Crie a tabela de pessoa antes de criar a tabela de pessoa fisica");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "pessoaFisica(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "cpf VARCHAR(11) UNIQUE, ";
		sql += "rg VARCHAR(20), ";
		sql += "sexo VARCHAR(20), ";
		sql += "possuiNecessidades BOOLEAN, ";
		sql += "certidaoNascimento VARCHAR(50), ";
		sql += "ctps VARCHAR(20), ";
		sql += "codigo_pessoa INTEGER REFERENCES pessoa(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
