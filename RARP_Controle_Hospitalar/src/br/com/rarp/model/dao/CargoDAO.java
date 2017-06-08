package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class CargoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "cargo(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "funcao VARCHAR(100), ";
		sql += "requisitos VARCHAR(255), ";
		sql += "nivel INTEGER, ";
		sql += "baseSalarial DECIMAL, ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
