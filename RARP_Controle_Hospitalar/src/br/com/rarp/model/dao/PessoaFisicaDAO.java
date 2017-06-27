package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.PessoaFisica;

public class PessoaFisicaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("pessoa"))
			throw new Exception("Crie a tabela de pessoa antes de criar a tabela de pessoa fisica");

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "pessoaFisica(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "cpf VARCHAR(11), ";
		sql += "rg VARCHAR(20), ";
		sql += "sexo VARCHAR(20), ";
		sql += "possuiNecessidades BOOLEAN, ";
		sql += "certidaoNascimento VARCHAR(50), ";
		sql += "ctps VARCHAR(20), ";
		sql += "codigo_pessoa INTEGER REFERENCES pessoa(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(PessoaFisica pessoaFisica) throws Exception {
		if (pessoaFisica.getCodigo() == 0)
			inserir(pessoaFisica);
		else
			alterar(pessoaFisica);
	}

	private void alterar(PessoaFisica pessoaFisica) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoaFisica);

			String sql = "UPDATE pessoaFisica SET cpf=?, rg=?, sexo=?, possuiNecessidades=?, certidaoNascimento=?, ctps=?, codigo_pessoa=?, status=? WHERE codigo = ?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, pessoaFisica.getCpf());
			ps.setString(2, pessoaFisica.getRg());
			ps.setString(3, pessoaFisica.getSexo());
			ps.setBoolean(4, pessoaFisica.isPossuiNecessidades());
			ps.setString(5, pessoaFisica.getCertidaoNascimento());
			ps.setString(6, pessoaFisica.getCTPS());
			ps.setInt(7, pessoaFisica.getCodigo());
			ps.setBoolean(8, pessoaFisica.isStatus());
			
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}

	private void inserir(PessoaFisica pessoaFisica) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoaFisica);

			String sql = "INSERT INTO pessoaFisica(cpf, rg, sexo, possuiNecessidades, certidaoNascimento, ctps, codigo_pessoa, status) VALUES(?,?,?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, pessoaFisica.getCpf());
			ps.setString(2, pessoaFisica.getRg());
			ps.setString(3, pessoaFisica.getSexo());
			ps.setBoolean(4, pessoaFisica.isPossuiNecessidades());
			ps.setString(5, pessoaFisica.getCertidaoNascimento());
			ps.setString(6, pessoaFisica.getCTPS());
			ps.setInt(7, pessoaFisica.getCodigo());
			ps.setBoolean(8, pessoaFisica.isStatus());

			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}
}
