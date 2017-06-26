package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cidade;

public class CidadeDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("estado"))
			throw new Exception("Crie a tabela de estado antes de criar a tabela de cidade");

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "cidade(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "codigo_ibge int, ";
		sql += "uf_estado CHAR(2) REFERENCES estado(uf), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(Cidade cidade) throws Exception {
		if (cidade.getCodigo() == 0)
			inserir(cidade);
		else
			alterar(cidade);
	}

	private void inserir(Cidade cidade) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO cidade(codigo, nome, codigo_ibge, uf_estado, status) VALUES(?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, cidade.getCodigo());
			ps.setString(2, cidade.getNome());
			ps.setInt(3, cidade.getCodigoIBGE());
			ps.setString(4, cidade.getEstado().getUF());
			ps.setBoolean(5, cidade.isStatus());
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
			String sql = "UPDATE cidade SET codigo = ?, nome = ?, codigo_ibge = ?, uf_estado, status=? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, cidade.getNome());
			ps.setString(2, cidade.getNome());
			ps.setInt(3, cidade.getCodigoIBGE());
			ps.setString(4, cidade.getEstado().getUF());
			ps.setBoolean(5, cidade.isStatus());
			ps.setBoolean(6, cidade.isStatus());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
}
