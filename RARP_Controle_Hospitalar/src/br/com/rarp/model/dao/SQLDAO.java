package br.com.rarp.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
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
	
	public static int getCodigoMovimentacao(String nomeTabela, int codigo) throws Exception {
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			String sql = "SELECT codigo_mov FROM " + nomeTabela + " WHERE codigo = " + codigo;
			ResultSet rs = conexao.prepareStatement(sql).executeQuery();
			if(rs.next())
				return rs.getInt("codigo_mov");			
		} finally {
			conexao.close();
		}
		return 0;	
	}
}
