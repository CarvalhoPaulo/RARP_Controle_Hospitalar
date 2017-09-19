package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.EntradaPaciente;

public class EntradaPacienteDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("paciente"))
			throw new Exception("Crie a tabela de paciente antes de criar a tabela de entrada de pacientes");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("funcionario"))
			throw new Exception("Crie a tabela de funcionario antes de criar a tabela de entrada de pacientes");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("medico"))
			throw new Exception("Crie a tabela de medicos antes de criar a tabela de entrada de pacientes");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentacao antes de criar a tabela de entrada de pacientes");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "entradapaciente(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_paciente INTEGER REFERENCES paciente(codigo), ";
		sql += "atendente_funcionario INTEGER REFERENCES funcionario(codigo), ";
		sql += "enfermeira_funcionario INTEGER REFERENCES funcionario(codigo), ";
		sql += "codigo_medico INTEGER REFERENCES medico(codigo), ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "pretriagem VARCHAR, ";
		sql += "alta BOOLEAN, ";
		sql += "emergencia BOOLEAN, ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(EntradaPaciente entradaPaciente) throws Exception {
		if(entradaPaciente != null)
			if(entradaPaciente.getCodigo() == 0)
				inserir(entradaPaciente);
			else
				alterar(entradaPaciente);
				
	}

	private void alterar(EntradaPaciente entradaPaciente) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		conexao.getConexao().setAutoCommit(false);
		try {
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(conexao.getConexao(), entradaPaciente);
    		
    		AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
			atendimentoDAO.salvar(conexao.getConexao(), entradaPaciente);
			
			EncaminhamentoDAO encaminhamentoDAO = new EncaminhamentoDAO();
			encaminhamentoDAO.salvar(conexao.getConexao(), entradaPaciente);
    		
			String sql = "UPDATE "
					+ "entradapaciente "
					+ "SET "
					+ "codigo_paciente = ?, "
					+ "atendente_funcionario = ?, "
					+ "enfermeira_funcionario = ?, "
					+ "codigo_medico = ?, "
					+ "pretriagem = ?, "
					+ "alta = ?, "
					+ "emergencia = ?, "
					+ "status = ? "
					+ "WHERE "
					+ "codigo = ?";
			ps = conexao.getConexao().prepareStatement(sql);
    		
			if(entradaPaciente.getPaciente() != null)
				ps.setInt(1, entradaPaciente.getPaciente().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(entradaPaciente.getAtendente() != null)
				ps.setInt(2, entradaPaciente.getAtendente() .getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			
			if(entradaPaciente.getEnfermeira() != null)
				ps.setInt(3, entradaPaciente.getEnfermeira().getCodigo());
			else
				ps.setNull(3, Types.INTEGER);
			
			if(entradaPaciente.getMedico() != null)
				ps.setInt(4, entradaPaciente.getMedico().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);
			
			if(entradaPaciente.getPreTriagem() != null)
				ps.setString(5, entradaPaciente.getPreTriagem());
			else
				ps.setNull(5, Types.VARCHAR);
			
			ps.setBoolean(6, entradaPaciente.isAlta());
			ps.setBoolean(7, entradaPaciente.isEmergencia());
			ps.setBoolean(8, entradaPaciente.isStatus());	
			ps.setInt(9, entradaPaciente.getCodigo());
			ps.executeUpdate();
			ps.close();
			conexao.getConexao().commit();
		} catch (Exception e) {
			conexao.getConexao().rollback();
			throw e;
		} finally {
			conexao.getConexao().close();
		} 
	}

	private void inserir(EntradaPaciente entradaPaciente) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		conexao.getConexao().setAutoCommit(false);
		try {
			String sql = "INSERT INTO entradapaciente(codigo, codigo_paciente, atendente_funcionario, enfermeira_funcionario, codigo_medico, pretriagem, alta, emergencia, status) VALUES(?,?,?,?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(conexao.getConexao(), entradaPaciente);
			
			ps.setInt(1, entradaPaciente.getCodigo());
			if(entradaPaciente.getPaciente() != null)
				ps.setInt(2, entradaPaciente.getPaciente().getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			
			if(entradaPaciente.getAtendente() != null)
				ps.setInt(3, entradaPaciente.getAtendente() .getCodigo());
			else
				ps.setNull(3, Types.INTEGER);
			
			if(entradaPaciente.getEnfermeira() != null)
				ps.setInt(4, entradaPaciente.getEnfermeira().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);
			
			if(entradaPaciente.getMedico() != null)
				ps.setInt(5, entradaPaciente.getMedico().getCodigo());
			else
				ps.setNull(5, Types.INTEGER);
			
			if(entradaPaciente.getPreTriagem() != null)
				ps.setString(6, entradaPaciente.getPreTriagem());
			else
				ps.setNull(6, Types.VARCHAR);
			
			ps.setBoolean(7, entradaPaciente.isAlta());
			ps.setBoolean(8, entradaPaciente.isEmergencia());
			ps.setBoolean(9, entradaPaciente.isStatus());
			
			ps.executeUpdate();
			ResultSet rs = ps.getResultSet();
			if(rs.next())
				entradaPaciente.setCodigo(rs.getInt(1));
			
			AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
			atendimentoDAO.salvar(conexao.getConexao(), entradaPaciente);
			
			EncaminhamentoDAO encaminhamentoDAO = new EncaminhamentoDAO();
			encaminhamentoDAO.salvar(conexao.getConexao(), entradaPaciente);
			
			ps.close();
			conexao.getConexao().commit();
		} catch (Exception e) {
			conexao.getConexao().rollback();
			throw e;
		} finally {
			conexao.getConexao().close();
		} 
	}

}
