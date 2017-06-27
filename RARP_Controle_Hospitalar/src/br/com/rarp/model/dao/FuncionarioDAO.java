package br.com.rarp.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Estado;
import br.com.rarp.model.Funcionario;

public class FuncionarioDAO {

	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("pessoafisica"))
			throw new Exception("Crie a tabela de pessoa fisica antes de criar a tabela de funcionarios");

		if (!SistemaCtrl.getInstance().tabelaExiste("cargo"))
			throw new Exception("Crie a tabela de cargo antes de criar a tabela de funcionarios");
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "funcionario(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "dataAdmissao TIMESTAMP, ";
		sql += "salarioContratual NUMERIC(13, 2), ";
		sql += "codigo_cargo INTEGER REFERENCES cargo(codigo), ";
		sql += "codigo_pf INTEGER REFERENCES pessoaFisica(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public List<Funcionario> consultar(String campo, String comparacao, String termo) throws Exception {
		List<Funcionario> funcionarios = new ArrayList<>();
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "SELECT " + "FUNC.codigo, " + "FUNC.status, " + "PF.cpf, " + "PF.rg, " + "PF.sexo, "
					+ "PF.possuinecessidades, " + "PF.certidaonascimento, " + "PF.ctps, " + "PE.nome, "
					+ "PE.logradouro, " + "PE.complemento, " + "PE.numero, " + "PE.bairro, " + "PE.cep, "
					+ "CID.codigo, " + "CID.nome, " + "ES.uf, " + "ES.nome " + "FROM funcionario FUNC "
					+ "RIGHT JOIN pessoafisica AS PF ON FUNC.codigo_pf = PF.codigo "
					+ "RIGHT JOIN pessoa AS PE ON PF.codigo_pessoa = PE.codigo "
					+ "RIGHT JOIN cidade AS CID ON PE.codigo_cidade = CID.codigo "
					+ "RIGHT JOIN estado AS ES ON CID.uf_estado = ES.uf " + "WHERE " + campo + comparacao + termo;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("FUNC.codigo"));
				funcionario.setCpf(rs.getString("PF.cpf"));
				funcionario.setRg(rs.getString("PF.rg"));
				funcionario.setSexo(rs.getString("PF.sexo"));
				funcionario.setPossuiNecessidades(rs.getBoolean("PF.possuinecessidades"));
				funcionario.setCertidaoNascimento(rs.getString("PF.certidaonascimento"));
				funcionario.setCTPS(rs.getString("PF.ctps"));
				funcionario.setNome(rs.getString("PE.nome"));
				funcionario.setLogradouro(rs.getString("PE.logradouro"));
				funcionario.setComplemento(rs.getString("PE.complemento"));
				funcionario.setNumero(rs.getString(rs.getString("PE.numero")));
				funcionario.setBairro(rs.getString("PE.bairro"));
				funcionario.setCep(rs.getString("PE.cep"));
				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("CID.codigo"));
				cidade.setNome(rs.getString("CID.nome"));

				Estado estado = new Estado();
				estado.setNome(rs.getString("ES.nome"));
				estado.setUF(rs.getString("ES.uf"));

				cidade.setEstado(estado);

				funcionario.setCidade(cidade);

				funcionario.setStatus(rs.getBoolean("FUNC.status"));
				funcionarios.add(funcionario);
			}
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
		return funcionarios;
	}

	public Funcionario consultar(int codigo) throws Exception {
		List<Funcionario> funcionarios = consultar("codigo", " = ", codigo + "");
		if (funcionarios.size() > 0)
			return funcionarios.get(0);
		else
			return null;
	}

	public void salvar(Funcionario funcionario) throws Exception {
		if (funcionario.getCodigo() == 0)
			inserir(funcionario);
		else
			alterar(funcionario);
	}

	private void inserir(Funcionario funcionario) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisicaDAO.salvar(funcionario);

			String sql = "INSERT INTO funcionario(dataAdmissao, salarioContratual, codigo_cargo, status) VALUES(?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setDate(1, new Date(funcionario.getDtAdmissao().getTime()));
			ps.setDouble(2, funcionario.getSalarioContratual());
			if(funcionario.getCargo() != null)
				ps.setInt(3, funcionario.getCargo().getCodigo());
			else
				ps.setNull(3, Types.INTEGER);
			ps.setBoolean(4, funcionario.isStatus());

			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}

	private void alterar(Funcionario funcionario) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisicaDAO.salvar(funcionario);

			String sql = "UPDATE funcionario SET nome=?, cpf =?, rg =?, dtAdmissao =?, salarioContratual =?, cargo =?, estadoCivil =?, sexo =?, possuiNecessidades =?, ctps =?, logradouro =?, complemento =?, numero =?, bairro =?, cep =?, cidade =?, status =? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCep());
			ps.setString(3, funcionario.getRg());
			ps.setDate(4, new Date(funcionario.getDtAdmissao().getTime()));
			ps.setDouble(5, funcionario.getSalarioContratual());
			ps.setInt(6, funcionario.getCargo().getCodigo());
			ps.setString(7, funcionario.getEstadoCivil());
			ps.setBoolean(8, funcionario.isPossuiNecessidades());
			ps.setString(9, funcionario.getCTPS());
			ps.setString(10, funcionario.getLogradouro());
			ps.setString(11, funcionario.getComplemento());
			ps.setString(12, funcionario.getNumero());
			ps.setString(13, funcionario.getBairro());
			ps.setString(14, funcionario.getCep());
			ps.setString(15, funcionario.getComplemento());
			ps.setInt(16, funcionario.getCidade().getCodigo());
			ps.setBoolean(17, funcionario.isStatus());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}

}
