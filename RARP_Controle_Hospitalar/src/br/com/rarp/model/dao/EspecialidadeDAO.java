package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Funcionario;

public class EspecialidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
	
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "especialidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome varchar(250) ";
		sql  += ")";
		st.executeUpdate(sql);
	}

	public void inserir(Especialidade especialidade) throws Exception{
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			
			String sql = "INSERT INTO especialidade(nome) VALUES(?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, especialidade.getNome());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
	
	public void  alterar(Especialidade especialidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			

			String sql = "UPDATE especialidade SET nome=? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, especialidade.getNome());

			ps.setInt(2, especialidade.getCodigo());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
	
	public void deletar(Especialidade especialidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			
			String sql = "DELETE especialidade WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, especialidade.getCodigo());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
}
