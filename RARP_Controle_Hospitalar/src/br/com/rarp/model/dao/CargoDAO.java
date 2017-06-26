package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cargo;

public class CargoDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "cargo(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "funcao VARCHAR(100), ";
		sql += "requisitos VARCHAR(255), ";
		sql += "nivel VARCHAR(20), ";
		sql += "status boolean)";
		st.executeUpdate(sql);

	}

	public void salvar(Cargo cargo) throws Exception {
		if (cargo.getCodigo() == 0)
			inserir(cargo);
		else
			alterar(cargo);
	}

	private void inserir(Cargo cargo) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO cargo(codigo, nome, funcao, requisitos, nivel, status) VALUES(?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, cargo.getCodigo());
			ps.setString(2, cargo.getNome());
			ps.setString(3, cargo.getFuncao());
			ps.setString(4, cargo.getRequisitos());
			ps.setString(5, cargo.getNivel());
			ps.setBoolean(6, cargo.isStatus());

			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}

	private void alterar(Cargo cargo) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "UPDATE cargo SET codigo=?, nome = ?, funcao = ?, requisitos = ?, nivel = ?, status = ? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, cargo.getNome());
			ps.setString(2, cargo.getFuncao());
			ps.setString(3, cargo.getRequisitos());
			ps.setString(4, cargo.getNivel());
			ps.setBoolean(5, cargo.isStatus());

			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
}
