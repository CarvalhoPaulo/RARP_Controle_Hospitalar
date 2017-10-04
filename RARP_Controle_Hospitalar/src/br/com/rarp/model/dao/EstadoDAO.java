package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Estado;

public class EstadoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "estado(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "uf CHAR(2) NOT NULL, ";
		sql += "nome VARCHAR(100))";
		st.executeUpdate(sql);
	}
	
	public List<Estado> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		List<Estado> estados = new ArrayList<>();
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			String sql = "SELECT uf, nome FROM estado WHERE " + campo + comparacao + termo;
			ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Estado estado = new Estado();
				estados.add(estado);
				
				estado.setUF(rs.getString("uf"));
				estado.setNome(rs.getString("nome"));
			}
			ps.close();
		} finally {
			conexao.close();
		}
		return estados;
	}

	public Estado get(Integer codigo) throws SQLException, Exception {
		if (codigo > 0) {
			List<Estado> estados = new ArrayList<>();
			estados = consultar("codigo", " = ", codigo + "");
			if (estados.size() > 0)
				return estados.get(0);
		}
		return null;
	}
}
