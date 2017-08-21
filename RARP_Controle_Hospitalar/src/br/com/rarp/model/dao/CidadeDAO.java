package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Estado;

public class CidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("estado"))
			throw new Exception("Crie a tabela de estado antes de criar a tabela de cidade");
		
		boolean existe = SistemaCtrl.getInstance().tabelaExiste("cidade");
			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "cidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "codigo_ibge int, ";
		sql += "codigo_estado Integer REFERENCES estado(codigo), ";
		sql += "status boolean DEFAULT TRUE)";
		st.executeUpdate(sql);
		
		if(!existe) {
			SQLDAO dao = new SQLDAO();
	        dao.executarSQLFile("cidades_estados.sql");			
		}

	}

	public void salvar(Cidade cidade) throws Exception {
		if (cidade != null) {
			if (cidade.getCodigo() == 0)
				inserir(cidade);
			else
				alterar(cidade);
		}
	}

	private void inserir(Cidade cidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO cidade(nome, codigo_ibge, codigo_estado, status) VALUES(?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, cidade.getNome());
			ps.setInt(2, cidade.getCodigoIBGE());
			if (cidade.getEstado() != null)
				ps.setString(3, cidade.getEstado().getUF());
			else
				ps.setNull(3, Types.VARCHAR);
			ps.setBoolean(4, cidade.isStatus());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		} 
	}

	private void alterar(Cidade cidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "UPDATE cidade SET nome = ?, codigo_ibge = ?, codigo_estado=?, status=? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, cidade.getNome());
			ps.setInt(2, cidade.getCodigoIBGE());
			if (cidade.getEstado() != null)
				ps.setString(3, cidade.getEstado().getUF());
			else
				ps.setNull(3, Types.VARCHAR);
			ps.setBoolean(4, cidade.isStatus());
			ps.setInt(5, cidade.getCodigo());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		} 
	}

	public List<Cidade> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		List<Cidade> cidades = new ArrayList<>();
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "SELECT "
					+ "cid.codigo, "
					+ "cid.nome, "
					+ "cid.codigo_ibge, "
					+ "cid.codigo_estado, "
					+ "cid.status, "
					+ "es.nome AS nome_estado, "
					+ "es.uf "
					+ "FROM cidade AS cid "
					+ "LEFT JOIN estado AS es ON es.codigo = cid.codigo_estado "
					+ "WHERE " + campo + comparacao + termo;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("codigo"));
				cidade.setNome(rs.getString("nome"));
				cidade.setCodigoIBGE(rs.getInt("codigo_ibge"));
				Estado estado = new Estado();
				estado.setNome(rs.getString("nome_estado"));
				estado.setUF(rs.getString("uf"));
				estado.setCodigo(rs.getInt("codigo_estado"));
				cidade.setEstado(estado);
				cidades.add(cidade);
			}
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
		return cidades;
	}

}
