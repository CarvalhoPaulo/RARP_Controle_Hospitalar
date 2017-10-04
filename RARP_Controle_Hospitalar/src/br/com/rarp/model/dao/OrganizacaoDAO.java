package br.com.rarp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Organizacao;
import br.com.rarp.model.Estado;

public class OrganizacaoDAO {

	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "organizacao(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_pj integer NOT NULL REFERENCES pessoaJuridica(codigo), ";
		sql += "CONSTRAINT organizacao_pj UNIQUE (codigo_pj) ";
		sql += ")";

		try {
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Erro ao criar tabela de configuracoes");

		}

	}

	public void salvar() throws Exception {
		new PessoaJuridicaDAO().salvar(SistemaCtrl.getInstance().getOrganizacao());

	}

	public void inserir() throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {

			String sql = "INSERT INTO organizacao(codigo_pj) VALUES(?)";
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, SistemaCtrl.getInstance().getOrganizacao().getCodigo());
			ps.executeUpdate();
			ps.close();

		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Falha ao salvar organizaçao");
		} finally {
			conexao.close();
		}
	}
	
	public void alterar() throws Exception {
		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {

			String sql = "update organizacao set  codigo_pj=?";
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, SistemaCtrl.getInstance().getOrganizacao().getCodigo());
			ps.executeUpdate();
			ps.close();

		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Falha ao salvar organizaçao");
		} finally {
			conexao.close();
		}
	}

	

	public void getOrganizacao() throws Exception {

		PreparedStatement ps;
		Connection conexao = SistemaCtrl.getInstance().getConexao().getConexao();
		try {
			String sql = "SELECT PJ.razaosocial, " + "PJ.cnpj, " + "PE.codigo AS codigo_pessoa, "
					+ "PE.nome AS nome_pessoa, " + "PE.datanascimento, " + "PE.logradouro, " + "PE.complemento, "
					+ "PE.numero, " + "PE.bairro, " + "PE.cep, " + "PE.status AS status_pessoa, "
					+ "CID.codigo AS codigo_cidade, " + "CID.nome AS nome_cidade, " + "CID.status AS status_cidade, "
					+ "ES.uf, " + "ES.nome AS nome_estado " + "FROM  organizacao "
					+ "LEFT JOIN pessoajuridica AS PJ ON organizacao.codigo_pj = PJ.codigo "
					+ "LEFT JOIN pessoa AS PE ON PJ.codigo_pessoa = PE.codigo "
					+ "LEFT JOIN cidade AS CID ON PE.codigo_cidade = CID.codigo "
					+ "LEFT JOIN estado AS ES ON CID.uf_estado = ES.uf  ";
			ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Organizacao empresa = SistemaCtrl.getInstance().getOrganizacao();

				empresa.setRazaoSocial(rs.getString("razaosocial"));
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setNome(rs.getString("nome_pessoa"));
				empresa.setDtNascimento(rs.getDate("datanascimento"));
				empresa.setLogradouro(rs.getString("logradouro"));
				empresa.setComplemento(rs.getString("complemento"));
				empresa.setNumero(rs.getString("numero"));
				empresa.setBairro(rs.getString("bairro"));
				empresa.setCep(rs.getString("cep"));

				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("codigo_cidade"));
				cidade.setNome(rs.getString("nome_cidade"));
				cidade.setStatus(rs.getBoolean("status_cidade"));

				Estado estado = new Estado();
				estado.setNome(rs.getString("nome_estado"));
				estado.setUF(rs.getString("uf"));

				cidade.setEstado(estado);
				empresa.setCidade(cidade);

				TelefoneDAO telefoneDAO = new TelefoneDAO();
				empresa.setTelefones(telefoneDAO.getTelefones(rs.getInt("codigo_pessoa")));
			}
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception

			throw new Exception("falha ao consultar empresa");
		} finally {
			conexao.close();
		}

	}

}
