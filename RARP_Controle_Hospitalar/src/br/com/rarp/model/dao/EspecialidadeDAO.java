package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class EspecialidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
	
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "especialidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome varchar(250) , ";
		sql  += ")";
		st.executeUpdate(sql);
	}

}
