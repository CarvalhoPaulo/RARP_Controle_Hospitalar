package br.com.rarp.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class SQLDAO {
	public void executarSQLFile(String fileName) throws ClassNotFoundException, SQLException, Exception {
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		BufferedReader br = new BufferedReader(new FileReader(getClass().getResource(fileName).getFile()));
		String sql = "";
		while(br.ready())
		   sql += br.readLine();
		br.close();
		st.executeUpdate(sql);
	}
}
