package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Pessoa;

public class PessoaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("cidade"))
			throw new Exception("Crie a tabela de cidade antes de criar a tabela de pessoa");

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "pessoa(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(255), ";
		sql += "logradouro VARCHAR(255), ";
		sql += "complemento VARCHAR(255), ";
		sql += "numero VARCHAR(50), ";
		sql += "bairro VARCHAR(255), ";
		sql += "cep VARCHAR(9), ";
		sql += "codigo_cidade INTEGER REFERENCES cidade(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(Pessoa pessoa) throws Exception {
		if (pessoa.getCodigo() == 0)
			inserir(pessoa);
		else
			alterar(pessoa);
	}

	private void alterar(Pessoa pessoa) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "UPDATE pessoa SET nome = ?,logradouro = ?, complemento = ?, numero = ?, bairro = ?, cep = ?, codigo_cidade = ?, status = ? WHERE codigo = ?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getLogradouro());
			ps.setString(3, pessoa.getComplemento());
			ps.setString(4, pessoa.getNumero());
			ps.setString(5, pessoa.getBairro());
			ps.setString(6, pessoa.getCep());
			ps.setInt(7, pessoa.getCidade().getCodigo());
			ps.setBoolean(8, pessoa.isStatus());
			ps.setInt(9, pessoa.getCodigo());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}

	}

	private void inserir(Pessoa pessoa) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO pessoa(nome, logradouro, complemento, numero, bairro, cep, codigo_cidade, status) VALUES(?,?,?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getLogradouro());
			ps.setString(3, pessoa.getComplemento());
			ps.setString(4, pessoa.getNumero());
			ps.setString(5, pessoa.getBairro());
			ps.setString(6, pessoa.getCep());
			ps.setInt(7, pessoa.getCidade().getCodigo());
			ps.setBoolean(8, pessoa.isStatus());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
}
