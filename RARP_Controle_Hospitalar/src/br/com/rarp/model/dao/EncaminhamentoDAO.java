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
import br.com.rarp.model.Encaminhamento;
import br.com.rarp.model.EntradaPaciente;

public class EncaminhamentoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("leito"))
			throw new Exception("Crie a tabela de leitos antes de criar a tabela de encaminhamento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentação antes de criar a tabela de encaminhamento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("entradapaciente"))
			throw new Exception("Crie a tabela de entrada de pacientes antes de criar a tabela de encaminhamento");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "encaminhamento(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "origem_leito INTEGER REFERENCES leito(codigo), ";
		sql += "destino_leito INTEGER REFERENCES leito(codigo), ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "codigo_entrada INTEGER REFERENCES entradapaciente(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
	
	public void salvar(Connection connection, EntradaPaciente entradaPaciente) throws Exception {
		if (entradaPaciente.getEncaminhamentos() != null) {
			List<Encaminhamento> listaInserir = new ArrayList<>(), listaAlterar = new ArrayList<>();
			for (Encaminhamento e : entradaPaciente.getEncaminhamentos()) {
				if (e != null) {
					e.setEntradaPaciente(entradaPaciente);
					if (e.getCodigo() == 0) 
						listaInserir.add(e);	
					else
						listaAlterar.add(e);
				}
			}
			inserir(connection, listaInserir);
			alterar(connection, listaAlterar);
		}
	}

	private void alterar(Connection connection, List<Encaminhamento> listaAlterar) throws SQLException {
		String sql = "UPDATE encaminhamento SET origem_leito = ?, destino_leito = ?, status = ? WHERE codigo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		int i = 0;
		for(Encaminhamento e: listaAlterar) {
			if(e.getOrigem() != null)
				ps.setInt(1, e.getOrigem().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(e.getDestino() != null)
				ps.setInt(2, e.getDestino().getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			
			ps.setBoolean(3, e.isStatus());
			ps.setInt(4, e.getCodigo());
			ps.addBatch();
            i++;
            if (i == listaAlterar.size()) {
            	ps.executeBatch();
            }
		}
		ps.executeBatch();
		ps.close();
	}

	private void inserir(Connection connection, List<Encaminhamento> listaInserir) throws Exception {
		String sql = "INSERT INTO encaminhamento(origem_leito, destino_leito, codigo_mov, codigo_entrada, status) VALUES(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		int i = 0;
		for(Encaminhamento e: listaInserir) {
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(connection, e);
    		
			if(e.getOrigem() != null)
				ps.setInt(1, e.getOrigem().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(e.getDestino() != null)
				ps.setInt(2, e.getDestino().getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			
			ps.setInt(3, e.getCodigo());
			if(e.getEntradaPaciente() != null)
				ps.setInt(4, e.getEntradaPaciente().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);
			ps.setBoolean(5, e.isStatus());
			
			ps.addBatch();
            i++;
            if (i == listaInserir.size()) {
            	ps.executeBatch();
            }
		}
		ps.executeBatch();
		
    	ResultSet rs = ps.getGeneratedKeys();
    	for (Encaminhamento e : listaInserir) {
			if (rs.next())
				e.setCodigo(rs.getInt(1));
		}
		ps.close();
	}

	public void salvar(Encaminhamento encaminhamento, Encaminhamento encaminhamentoAnt) throws Exception {
		Connection connection = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			connection.setAutoCommit(false);		
			LeitoDAO leitoDAO = new LeitoDAO();
			
			if(encaminhamentoAnt != null) {
				leitoDAO.salvar(connection, encaminhamentoAnt.getOrigem());
				leitoDAO.salvar(connection, encaminhamentoAnt.getDestino());
			}
			
			leitoDAO.salvar(connection, encaminhamento.getOrigem());
			leitoDAO.salvar(connection, encaminhamento.getDestino());
			
			List<Encaminhamento> encaminhamentos = new ArrayList<>();
			encaminhamentos.add(encaminhamento);
			
			if (encaminhamento != null)
				if (encaminhamento.getCodigo() == 0) {
					inserir(connection, encaminhamentos);
				} else {
					alterar(connection, encaminhamentos);
				}
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			throw new Exception("Não foi possível salvar este encaminhamento\nErro: " + e.toString());
		} finally {
			connection.close();
		}
	}

	public List<Encaminhamento> consultar(String campo, String comparacao, String termo) throws Exception {
		return consultar(campo + comparacao + termo);
	}

	private List<Encaminhamento> consultar(String consulta) throws Exception {
		List<Encaminhamento> encaminhamentos = new ArrayList<>();
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			String sql = "SELECT "
					+ "ENC.codigo codigo_enc, "
					+ "ENC.origem_leito, "
					+ "ENC.destino_leito, "
					+ "MOV.data dtmov_enc, "
					+ "MOV.hora hrmov_enc, "
					+ "ENC.codigo_entrada, "
					+ "ENC.status status_enc, "
					+ "ENT.codigo_paciente, "
					+ "MOV1.data dtmov_ent, "
					+ "MOV1.hora hrmov_ent "
					+ "FROM encaminhamento ENC "
					+ "LEFT JOIN entradapaciente ENT ON ENC.codigo_entrada = ENT.codigo "
					+ "LEFT JOIN movimentacao MOV ON ENC.codigo_mov = MOV.codigo "
					+ "LEFT JOIN movimentacao MOV1 ON ENT.codigo_mov = MOV1.codigo "		
					+ "LEFT JOIN leito ORI ON ENC.origem_leito = ORI.codigo "
					+ "LEFT JOIN leito DEST ON ENC.destino_leito = DEST.codigo "
					+ "WHERE "
					+ consulta;
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Encaminhamento encaminhamento = new Encaminhamento();
				encaminhamentos.add(encaminhamento);
				
				encaminhamento.setCodigo(rs.getInt("codigo_enc"));
				encaminhamento.setDestino(new LeitoDAO().getLeito(rs.getInt("destino_leito")));
				encaminhamento.setOrigem(new LeitoDAO().getLeito(rs.getInt("origem_leito")));
				encaminhamento.setDtMovimentacao(rs.getDate("dtmov_enc").toLocalDate());
				encaminhamento.setHrMovimentacao(rs.getTime("hrmov_enc").toLocalTime());
				encaminhamento.setStatus(rs.getBoolean("status_enc"));
				
				EntradaPaciente entradaPaciente = new EntradaPaciente();
				entradaPaciente.setPaciente(new PacienteDAO().getPaciente(rs.getInt("codigo_paciente")));
				entradaPaciente.setDtMovimentacao(rs.getDate("dtmov_ent").toLocalDate());
				entradaPaciente.setHrMovimentacao(rs.getTime("hrmov_ent").toLocalTime());
				
				encaminhamento.setEntradaPaciente(entradaPaciente);
			}
			return encaminhamentos;	
		} finally {
			conexao.close();
		}
	}
}
