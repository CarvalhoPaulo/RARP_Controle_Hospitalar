package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class CidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("estado"))
			throw new Exception("Crie a tabela de estado antes de criar a tabela de cidade");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "cidade(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "uf_estado CHAR(2) REFERENCES estado(uf), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
