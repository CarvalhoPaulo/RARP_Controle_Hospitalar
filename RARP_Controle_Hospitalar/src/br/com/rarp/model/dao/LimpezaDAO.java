package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Leito;
import br.com.rarp.model.Limpeza;

public class LimpezaDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentação antes de criar a tabela de limpeza e limpeza_leitos");
	
		if(!SistemaCtrl.getInstance().tabelaExiste("leito"))
			throw new Exception("Crie a tabela de leito antes de criar a tabela de limpeza e limpeza_leitos");
		
		if(!SistemaCtrl.getInstance().tabelaExiste("funcionario"))
			throw new Exception("Crie a tabela de funcionário antes de criar a tabela de limpeza e limpeza_leitos");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "limpeza(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "codigo_funcionario INTEGER REFERENCES funcionario(codigo), ";
		sql += "status boolean)";
		
		sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "limpeza_leito(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_limpeza INTEGER REFERENCES limpeza(codigo), ";
		sql += "codigo_leito INTEGER REFERENCES leito(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(Limpeza limpeza) throws Exception {
		if(limpeza != null)
			if(limpeza.getCodigo() == 0)
				inserir(limpeza);
			else
				alterar(limpeza);
	}

	private void alterar(Limpeza limpeza) throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		conexao.setAutoCommit(false);
		try {
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(conexao, limpeza);
    		
			String sql = "UPDATE "
					+ "limpeza "
					+ "SET "
					+ "codigo_funcionario = ?, "
					+ "status = ? "
					+ "WHERE "
					+ "codigo = ?";
			ps = conexao.prepareStatement(sql);
			if(limpeza.getFuncionarioLimpeza() != null && limpeza.getFuncionarioLimpeza().getCodigo() > 0)
				ps.setInt(1, limpeza.getFuncionarioLimpeza().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
    		ps.setBoolean(2, limpeza.isStatus());
    		ps.setInt(3, limpeza.getCodigo());
			ps.executeUpdate();
			
			ps.close();			
			sql = "INSERT INTO limpeza_leito(codigo_limpeza, codigo_leito, status) ";
			sql += "VALUES(?,?,?) ";
			sql += "ON CONFLICT (codigo) ";
			sql += "DO UPDATE SET ";
			sql += "codigo_limpeza = ?, ";
			sql += "codigo_leito = ?, ";
			sql += "status = ?";
			ps.close();	
			salvarLeitosLimpeza(conexao, limpeza);
			
			conexao.commit();
		} catch (Exception e) {
			conexao.rollback();
			throw e;
		} finally {
			conexao.close();
		} 
	}
	
	private void salvarLeitosLimpeza(Connection conexao, Limpeza limpeza) throws SQLException {
		if(limpeza != null && conexao != null && limpeza.getCodigo() > 0) {
			String sql = "INSERT INTO limpeza_leito(codigo_limpeza, codigo_leito, status) ";
			sql += "VALUES(?,?,?) ";
			sql += "ON CONFLICT (codigo) ";
			sql += "DO UPDATE SET ";
			sql += "codigo_limpeza = ?, ";
			sql += "codigo_leito = ?, ";
			sql += "status = ?";
			PreparedStatement ps = conexao.prepareStatement(sql);
        	int i = 0;
        	
        	if (limpeza.getCodigo() != 0) {
				for (Leito leito : limpeza.getLeitos()) {
					if (leito.getCodigo() == 0)
						continue;
					ps.setInt(1, limpeza.getCodigo());
					ps.setInt(2, leito.getCodigo());
					ps.setBoolean(3, limpeza.isStatus());
					ps.setInt(4, limpeza.getCodigo());
					ps.setInt(5, leito.getCodigo());
					ps.setBoolean(6, limpeza.isStatus());
					ps.addBatch();
					i++;

					if (i == limpeza.getLeitos().size()) {
						ps.executeBatch();
					}
				} 
			}
			ps.executeBatch();
		}
	}

	private void inserir(Limpeza limpeza) throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		conexao.setAutoCommit(false);
		try {
			String sql = "INSERT INTO limpeza(codigo_funcionario, codigo_mov, status) VALUES(?,?,?)";
			ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(conexao, limpeza);
		
    		if(limpeza.getFuncionarioLimpeza() != null && limpeza.getFuncionarioLimpeza().getCodigo() > 0)
    			ps.setInt(1, limpeza.getFuncionarioLimpeza().getCodigo());
    		else
				ps.setNull(1, Types.INTEGER);
    		
    		ps.setInt(2, limpeza.getCodigo());
    		ps.setBoolean(3, limpeza.isStatus());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				limpeza.setCodigo(rs.getInt(1));
			
			ps.close();
			salvarLeitosLimpeza(conexao, limpeza);
			conexao.commit();
		} catch (Exception e) {
			conexao.rollback();
			throw e;
		} finally {
			conexao.close();
		} 
	}

}
