package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class EstadoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "estado(";
		sql += "uf CHAR(2) NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
