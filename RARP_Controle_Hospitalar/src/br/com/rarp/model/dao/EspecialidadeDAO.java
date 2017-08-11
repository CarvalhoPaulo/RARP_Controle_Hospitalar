package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Especialidade;

public class EspecialidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
	
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "especialidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome varchar(250) ,";
		sql += "observacoes character varying ,";
		sql += "status boolean ";
		sql  += ")";
		st.executeUpdate(sql);
	}
	
	public void salvar(Especialidade especialidade) throws Exception {
		if(especialidade.getCodigo() == 0)
			inserir(especialidade);
		else
			alterar(especialidade);
	}

	private void inserir(Especialidade especialidade) throws Exception{
			PreparedStatement ps;
			Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			
			String sql = "INSERT INTO especialidade(nome,observacoes,status) VALUES(?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, especialidade.getNome());
			ps.setString(2, especialidade.getObservacoes());
			ps.setBoolean(3, especialidade.isStatus());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			throw new Exception("Erro a salvar Especialidade");
			
			
		}finally {
		
			conexao.getConexao().close();
		}
	}
	
	private void  alterar(Especialidade especialidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			
			String sql = "UPDATE especialidade SET nome=?,  observacoes=?,  status=? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, especialidade.getNome());
			ps.setString(2, especialidade.getObservacoes());
			ps.setBoolean(3, especialidade.isStatus());
			ps.setInt(4, especialidade.getCodigo());
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
