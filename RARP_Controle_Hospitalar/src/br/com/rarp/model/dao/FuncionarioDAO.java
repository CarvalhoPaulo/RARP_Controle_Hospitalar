package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class FuncionarioDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "funcionario(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "";
		sql += "status boolean)";
		st.executeQuery(sql);
	}

}
