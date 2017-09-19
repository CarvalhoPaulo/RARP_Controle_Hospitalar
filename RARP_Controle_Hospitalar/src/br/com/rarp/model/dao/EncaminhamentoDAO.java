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
		List<Encaminhamento> listaInserir = new ArrayList<>(), listaAlterar = new ArrayList<>();
		for(Encaminhamento e: entradaPaciente.getEncaminhamentos()) {
			if(e != null) {
				if(e.getCodigo() == 0)
					listaInserir.add(e);
				else
					listaAlterar.add(e);
			}	
		}
		inserir(connection, listaInserir, entradaPaciente);
		alterar(connection, listaAlterar, entradaPaciente);
	}

	private void alterar(Connection connection, List<Encaminhamento> listaAlterar, EntradaPaciente entradaPaciente) throws SQLException {
		String sql = "UPDATE encaminhamento SET origem_leito = ?, destino_leito = ?, status = ? WHERE codigo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		int i = 0;
		for(Encaminhamento e: listaAlterar) {
			if(e.getOrigem() != null)
				ps.setInt(1, e.getOrigem().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(e.getOrigem() != null)
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

	private void inserir(Connection connection, List<Encaminhamento> listaInserir, EntradaPaciente entradaPaciente) throws Exception {
		String sql = "INSERT encaminhamento(origem_leito, destino_leito, codigo_mov, codigo_entrada, status) VALUES(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		int i = 0;
		for(Encaminhamento e: listaInserir) {
			MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(connection, e);
    		
			if(e.getOrigem() != null)
				ps.setInt(1, e.getOrigem().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(e.getOrigem() != null)
				ps.setInt(2, e.getDestino().getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			
			ps.setInt(3, e.getCodigo());
			ps.setInt(4, entradaPaciente.getCodigo());
			ps.setBoolean(5, e.isStatus());
			
			ps.addBatch();
            i++;
            if (i == listaInserir.size()) {
            	ps.executeBatch();
            }
		}
		ps.executeBatch();
		
    	ResultSet rs = ps.getResultSet();
    	for (Encaminhamento e : listaInserir) {
			if (rs.next())
				e.setCodigo(rs.getInt(1));
		}
		ps.close();
	}
}
