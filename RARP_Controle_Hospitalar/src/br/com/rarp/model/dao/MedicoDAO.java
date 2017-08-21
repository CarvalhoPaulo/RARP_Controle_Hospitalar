package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Medico;

public class MedicoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("funcionario"))
			throw new Exception("Crie a tabela de funcionario fisica antes de criar a tabela de funcionarios");
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "medico(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_funcionario integer NOT NULL REFERENCES funcionario(codigo), ";
		sql += "CRM character varying(15) , ";	
		sql += "status boolean  ";		
		sql  += ")";
		
		st.executeUpdate(sql);
		
		
		st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "medico_especialidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_medico integer NOT NULL REFERENCES funcionario(codigo), ";
		sql += "codigo_especialidade integer NOT NULL REFERENCES especialidade(codigo) ";			
		sql  += ")";
		
		st.executeUpdate(sql);
	}

	public void salvar(Medico medico) {
		
	}
	public void inserir(Medico medico) throws Exception{
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			
			String sql = "INSERT INTO especialidade(nome) VALUES(?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, medico.getNome());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
	
	private void alterar(Medico especialidade) throws Exception {
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

}
