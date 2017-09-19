package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Atendimento;
import br.com.rarp.model.EntradaPaciente;

public class AtendimentoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("entradapaciente"))
			throw new Exception("Crie a tabela de entrada de pacientes antes de criar a tabela de atendimento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentações antes de criar a tabela de atendimento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("receita"))
			throw new Exception("Crie a tabela de receitas antes de criar a tabela de atendimento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("funcionario"))
			throw new Exception("Crie a tabela de funcionarios antes de criar a tabela de atendimento");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "atendimento(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "dataatendimento TIMESTAMP, ";
		sql += "horaini TIMESTAMP, ";
		sql += "horafim TIMESTAMP, ";
		sql += "detalhemedico VARCHAR, ";
		sql += "descricao VARCHAR, ";
		sql += "statusatendimento VARCHAR, ";
		sql += "codigo_entrada INTEGER REFERENCES entradapaciente(codigo), ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "codigo_receita INTEGER REFERENCES receita(codigo), ";
		sql += "codigo_funcionario INTEGER REFERENCES funcionario(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
	
	public void salvar(Connection connection, EntradaPaciente entradaPaciente) throws Exception {
		List<Atendimento> ListaInserir = new ArrayList<>(), ListaAlterar = new ArrayList<>();
		for(Atendimento a: entradaPaciente.getAtendimentos()) {
			if(a != null) {
				if(a.getCodigo() == 0)
					ListaInserir.add(a);
				else
					ListaAlterar.add(a);
			}
		}
		inserir(connection, ListaInserir);
		alterar(connection, ListaAlterar);
	}

	private void alterar(Connection connection, List<Atendimento> atendimentos) throws Exception {
		String sql= "UPDATE atendimento SET dataatendimento = ?, "
				+ "horaini = ?, "
				+ "horafim = ?, "
				+ "detalhemedico = ?, "
				+ "descricao = ?, "
				+ "statusatendimento = ?, "
				+ "codigo_entrada = ?, "
				+ "codigo_receita = ?, "
				+ "codigo_funcionario = ?, "
				+ "status = ? "
				+ "WHERE "
				+ "codigo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
    	int i = 0;
    	for (Atendimento a: atendimentos) { 
    		ReceitaMedicaDAO receitaMedicaDAO = new ReceitaMedicaDAO();
    		receitaMedicaDAO.salvar(connection, a.getReceitaMedica());
    		
    		MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(connection, a);
    		
    		SintomaDAO sintomaDAO = new SintomaDAO();
    		sintomaDAO.salvar(connection, a);
    		
    		ps.setDate(1, new Date(a.getDataAtendimento().getTime()));
    		ps.setDate(2, new Date(a.getHoraIni().getTime()));
    		ps.setDate(3, new Date(a.getHoraFim().getTime()));
    		ps.setString(4, a.getDetalheMedico());
    		ps.setString(5, a.getDescricao());
    		ps.setString(6, a.getStatusAtendimento().toString());
    		if(a.getEntradaPaciente() != null)
    			ps.setInt(7, a.getEntradaPaciente().getCodigo());
    		else
    			ps.setNull(7, Types.INTEGER);
    		
    		if(a.getReceitaMedica() != null)
    			ps.setInt(8, a.getReceitaMedica().getCodigo());
    		else
    			ps.setNull(8, Types.INTEGER);
    		
    		if(a.getResponsavel() != null)
    			ps.setInt(9, a.getResponsavel().getCodigo());
    		else
    			ps.setNull(9, Types.INTEGER);
    		
    		ps.setBoolean(9, a.isStatus());
    		ps.addBatch();
            i++;
            if (i == atendimentos.size()) {
            	ps.executeBatch();
            }
        }
    	ps.executeBatch();
		ps.close(); 
	}

	private void inserir(Connection connection, List<Atendimento> atendimentos) throws Exception {
		String sql= "INSERT INTO atendimento(dataatendimento, "
				+ "horaini, "
				+ "horafim, "
				+ "detalhemedico, "
				+ "descricao, "
				+ "statusatendimento, "
				+ "codigo_entrada, "
				+ "codigo_mov, "
				+ "codigo_receita, "
				+ "codigo_funcionario, "
				+ "status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    	int i = 0;
    	for (Atendimento a : atendimentos) { 
    		ReceitaMedicaDAO receitaMedicaDAO = new ReceitaMedicaDAO();
    		receitaMedicaDAO.salvar(connection, a.getReceitaMedica());
    		
    		MovimentacaoDAO movimentacaoDAO =  new MovimentacaoDAO();
    		movimentacaoDAO.salvar(connection, a);
    		
    		ps.setDate(1, new Date(a.getDataAtendimento().getTime()));
    		ps.setDate(2, new Date(a.getHoraIni().getTime()));
    		ps.setDate(3, new Date(a.getHoraFim().getTime()));
    		ps.setString(4, a.getDetalheMedico());
    		ps.setString(5, a.getDescricao());
    		ps.setString(6, a.getStatusAtendimento().toString());
    		if(a.getEntradaPaciente() != null)
    			ps.setInt(7, a.getEntradaPaciente().getCodigo());
    		else
    			ps.setNull(7, Types.INTEGER);
    		
    		ps.setInt(8, a.getCodigo());
    		
    		if(a.getReceitaMedica() != null)
    			ps.setInt(9, a.getReceitaMedica().getCodigo());
    		else
    			ps.setNull(9, Types.INTEGER);
    		
    		if(a.getResponsavel() != null)
    			ps.setInt(10, a.getResponsavel().getCodigo());
    		else
    			ps.setNull(10, Types.INTEGER);
    		
    		ps.setBoolean(11, a.isStatus());
    		ps.addBatch();
            i++;
            if (i == atendimentos.size()) {
            	ps.executeBatch();
            }
        }
    	
    	ps.executeBatch();
    	ResultSet rs = ps.getResultSet();
    	for (Atendimento a : atendimentos) {
			if (rs.next())
				a.setCodigo(rs.getInt(1));
			
			SintomaDAO sintomaDAO = new SintomaDAO();
    		sintomaDAO.salvar(connection, a);
		}
		ps.close();   
	}
}
