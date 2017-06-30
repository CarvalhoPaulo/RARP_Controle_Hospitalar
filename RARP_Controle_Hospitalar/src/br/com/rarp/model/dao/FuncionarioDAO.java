package br.com.rarp.model.dao;

import java.sql.Date;
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
import br.com.rarp.model.Funcionario;
import br.com.rarp.model.PessoaFisica;

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
		sql += "ctps VARCHAR(20), ";
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
			String sql = "SELECT " 
					+ "FUNC.codigo, " 
					+ "FUNC.dataAdmissao, "
					+ "FUNC.ctps, "
					+ "FUNC.salarioContratual, "
					+ "FUNC.status, "
					+ "PF.cpf, " 
					+ "PF.rg, "
					+ "PF.sexo, "
					+ "PF.possuinecessidades, " 
					+ "PF.certidaonascimento, " 
					+ "PF.status, " 
					+ "PE.nome, "
					+ "PE.logradouro, "
					+ "PE.complemento, " 
					+ "PE.numero, " 
					+ "PE.bairro, " 
					+ "PE.cep, "
					+ "PE.status, "
					+ "CID.codigo, " 
					+ "CID.nome, "
					+ "CID.status, "
					+ "ES.uf, "
					+ "ES.nome, "
					+ "ES.status "
					+ "FROM funcionario AS FUNC "
					+ "RIGHT JOIN pessoafisica AS PF ON FUNC.codigo_pf = PF.codigo "
					+ "RIGHT JOIN pessoa AS PE ON PF.codigo_pessoa = PE.codigo "
					+ "RIGHT JOIN cidade AS CID ON PE.codigo_cidade = CID.codigo "
					+ "RIGHT JOIN estado AS ES ON CID.uf_estado = ES.uf " + "WHERE " + campo + comparacao + termo;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("FUNC.codigo"));
				funcionario.setDtAdmissao(new java.util.Date(rs.getDate("FUNC.dataAdmissao").getTime()));
				funcionario.setCTPS(rs.getString("FUNC.ctps"));
				funcionario.setSalarioContratual(rs.getDouble("FUNC.salarioContratual"));
				funcionario.setStatus(rs.getBoolean("status_func"));
				funcionario.setCpf(rs.getString("PF.cpf"));
				funcionario.setRg(rs.getString("PF.rg"));
				funcionario.setSexo(rs.getString("PF.sexo"));
				funcionario.setPossuiNecessidades(rs.getBoolean("PF.possuinecessidades"));
				funcionario.setCertidaoNascimento(rs.getString("PF.certidaonascimento"));
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
			
			String sql = "INSERT INTO funcionario(dataAdmissao, salarioContratual, ctps, codigo_cargo, codigo_pf, status) VALUES(?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setDate(1, new Date(funcionario.getDtAdmissao().getTime()));
			ps.setDouble(2, funcionario.getSalarioContratual());
			ps.setString(3, funcionario.getCTPS());
			if(funcionario.getCargo() != null)
				ps.setInt(4, funcionario.getCargo().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);
			ps.setInt(5, funcionario.getCodigo());
			ps.setBoolean(6, funcionario.isStatus());

			ps.executeUpdate();
			ps.close();
			
			ps = conexao.getConexao().prepareStatement("SELECT MAX(codigo) AS lastCodigo FROM funcionario");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				funcionario.setCodigo(rs.getInt("lastCodigo"));
		} finally {
			conexao.getConexao().close();
		}
	}

	private void alterar(Funcionario funcionario) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO funcionario SET dataAdmissao = ?, salarioContratual = ?, ctps = ?, codigo_cargo = ?, status = ? WHERE codigo = ?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setDate(1, new Date(funcionario.getDtAdmissao().getTime()));
			ps.setDouble(2, funcionario.getSalarioContratual());
			ps.setString(3, funcionario.getCTPS());
			if(funcionario.getCargo() != null)
				ps.setInt(4, funcionario.getCargo().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);
			ps.setBoolean(5, funcionario.isStatus());
			ps.setInt(6, funcionario.getCodigo());
			ps.executeUpdate();
			ps.close();
			
			PessoaFisica pf = funcionario.clone();
			pf.setCodigo(conexao.getConexao().createStatement().executeQuery("SELECT codigo_pf FROM funcionario WHERE codigo = " + funcionario.getCodigo()).getInt("codigo_pf"));
			
			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisicaDAO.salvar(pf);
		} finally {
			conexao.getConexao().close();
		}
	}

}
