package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.SaidaPaciente;

public class SaidaPacienteDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentação antes de criar a tabela de encaminhamento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("entradapaciente"))
			throw new Exception("Crie a tabela de entrada de pacientes antes de criar a tabela de encaminhamento");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "saidapaciente(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "estadopaciente VARCHAR, ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "codigo_entrada INTEGER REFERENCES entradapaciente(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
	
	public List<SaidaPaciente> consultar(String consulta) throws Exception {
		List<SaidaPaciente> saidas = new ArrayList<SaidaPaciente>();
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			String sql = "SELECT "
					+ "SAI.codigo AS codigo_saida, "
					+ "SAI.codigo_entrada, "
					+ "SAI.estadopaciente, "
					+ "MOV.data dtmovimentacao, "
					+ "MOV.hora hrmovimentacao, "
					+ "SAI.status AS status_saida "
					+ "FROM saidapaciente SAI "
					+ "LEFT JOIN movimentacao MOV ON SAI.codigo_mov = MOV.codigo "
					+ "WHERE "
					+ consulta;
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SaidaPaciente saidaPaciente = new SaidaPaciente();
				saidaPaciente.setCodigo(rs.getInt("codigo_saida"));
				saidaPaciente.setEstadoPaciente(rs.getString("estadopaciente"));
				saidaPaciente.setDtMovimentacao(rs.getDate("dtmovimentacao").toLocalDate());
				saidaPaciente.setHrMovimentacao(rs.getTime("hrmovimentacao").toLocalTime());
				saidaPaciente.setEntradaPaciente(new EntradaPacienteDAO().getEntrada(rs.getInt("codigo_entrada")));
				saidaPaciente.setStatus(rs.getBoolean("status_saida"));
				saidas.add(saidaPaciente);
			}
			return saidas;	
		} finally {
			conexao.close();
		}
	}

	public void salvar(SaidaPaciente saidaPaciente) throws Exception {
		if(saidaPaciente != null) {
			if(saidaPaciente.getCodigo() == 0)
				inserir(saidaPaciente);
			else
				alterar(saidaPaciente);
		}
	}

	private void alterar(SaidaPaciente saidaPaciente) throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		conexao.setAutoCommit(false);
		try {    		
			String sql = "UPDATE "
					+ "saidapaciente "
					+ "SET "
					+ "estadopaciente = ?, "
					+ "codigo_entrada = ?, "
					+ "status = ? "
					+ "WHERE "
					+ "codigo = ?";
			ps = conexao.prepareStatement(sql);
			
    		ps.setString(1, saidaPaciente.getEstadoPaciente());
    		
    		if(saidaPaciente.getEntradaPaciente() != null && saidaPaciente.getEntradaPaciente().getCodigo() > 0)
    			ps.setInt(2, saidaPaciente.getEntradaPaciente().getCodigo());
    		else
    			ps.setNull(2, Types.INTEGER);
    		
    		ps.setBoolean(3, saidaPaciente.isStatus());
    		ps.setInt(4, saidaPaciente.getCodigo());
			
			ps.executeUpdate();
			
			saidaPaciente.setCodigo(SQLDAO.getCodigoMovimentacao("saidapaciente", saidaPaciente.getCodigo()));
			if(saidaPaciente.getCodigo() > 0)
				new MovimentacaoDAO().salvar(conexao, saidaPaciente);
    		
			ps.close();
			conexao.commit();
		} catch (Exception e) {
			conexao.rollback();
			throw e;
		} finally {
			conexao.close();
		} 
	}

	private void inserir(SaidaPaciente saidaPaciente) throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		conexao.setAutoCommit(false);
		try {
			String sql = "INSERT INTO saidapaciente(estadopaciente, codigo_mov, codigo_entrada, status) VALUES(?,?,?,?)";
			ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(conexao, saidaPaciente);
    		
    		ps.setString(1, saidaPaciente.getEstadoPaciente());
    		ps.setInt(2, saidaPaciente.getCodigo());
    		
    		if(saidaPaciente.getEntradaPaciente() != null && saidaPaciente.getEntradaPaciente().getCodigo() > 0)
    			ps.setInt(3, saidaPaciente.getEntradaPaciente().getCodigo());
    		else
    			ps.setNull(3, Types.INTEGER);
    		
    		ps.setBoolean(4, saidaPaciente.isStatus());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				saidaPaciente.setCodigo(rs.getInt(1));
			
			ps.close();
			conexao.commit();
		} catch (Exception e) {
			conexao.rollback();
			throw e;
		} finally {
			conexao.close();
		} 
	}

	public List<SaidaPaciente> consultar(String campo, String comparacao, String termo) throws Exception {
		return consultar(campo + comparacao + termo);
	}
}
