package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.ReceitaMedica;

public class ReceitaMedicaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {	
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "receita(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "descricao VARCHAR, ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(Connection connection, ReceitaMedica receitaMedica) throws SQLException {
		if(receitaMedica != null) {
			if(receitaMedica.getCodigo() == 0)
				inserir(connection, receitaMedica);
			else
				alterar(connection, receitaMedica);
		}
	}

	private void alterar(Connection connection, ReceitaMedica receitaMedica) throws SQLException {
		String sql= "UPDATE atendimento SET "
				+ "descricao = ?, "
				+ "WHERE "
				+ "codigo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, receitaMedica.getDescricao());
		ps.setInt(2, receitaMedica.getCodigo());
		ps.executeUpdate();
		ps.close();
	}

	private void inserir(Connection connection, ReceitaMedica receitaMedica) throws SQLException {
		String sql= "INSERT atendimento(descricao) VALUES(?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, receitaMedica.getDescricao());
		ps.executeUpdate();
		ResultSet rs = ps.getResultSet();
		if (rs.next())
			receitaMedica.setCodigo(rs.getInt(1));
		ps.close();
	}
}
