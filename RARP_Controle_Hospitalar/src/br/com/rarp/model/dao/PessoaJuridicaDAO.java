package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class PessoaJuridicaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("pessoa"))
			throw new Exception("Crie a tabela de pessoa antes de criar a tabela de pessoa juridica");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "pessoaJuridica(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "cnpj VARCHAR(11) UNIQUE, ";
		sql += "codigo_pj INTEGER REFERENCES pessoa(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
