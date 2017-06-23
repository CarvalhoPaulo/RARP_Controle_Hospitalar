package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class TelefoneDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("pessoa"))
			throw new Exception("Crie a tabela de pessoa antes de criar a tabela de telefone");
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "telefone(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "numero VARCHAR(12), ";
		sql += "codigo_pessoa INTEGER REFERENCES pessoa(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
