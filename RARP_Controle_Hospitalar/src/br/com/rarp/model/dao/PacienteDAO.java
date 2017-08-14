package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Cidade;
import br.com.rarp.model.Estado;
import br.com.rarp.model.Paciente;
import br.com.rarp.model.PessoaFisica;

public class PacienteDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("pessoafisica"))
			throw new Exception("Crie a tabela de pessoa fisica antes de criar a tabela de pacientes");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("convenio"))
			throw new Exception("Crie a tabela de pessoa conv�nio antes de criar a tabela de pacientes");

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "paciente(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "codigo_convenio INTEGER REFERENCES convenio(codigo), ";
		sql += "codigo_pf INTEGER REFERENCES pessoaFisica(codigo), ";
		sql += "codigo_resp INTEGER REFERENCES paciente(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public List<Paciente> consultar(String campo, String comparacao, String termo) throws Exception {
		List<Paciente> pacientes = new ArrayList<>();
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "SELECT " 
					+ "PAC.codigo AS codigo_pac, " 
					+ "PAC.codigo_convenio, "
					+ "PAC.codigo_resp, "
					+ "PAC.status AS status_pac, "
					+ "PF.cpf, " 
					+ "PF.rg, "
					+ "PF.sexo, "
					+ "PF.possuinecessidades, " 
					+ "PF.certidaonascimento, " 
					+ "PF.status AS status_pf, "
					+ "PE.codigo AS codigo_pessoa, " 
					+ "PE.nome AS nome_pessoa, "
					+ "PE.logradouro, "
					+ "PE.datanascimento, "
					+ "PE.complemento, " 
					+ "PE.numero, " 
					+ "PE.bairro, " 
					+ "PE.cep, "
					+ "PE.status AS status_pessoa, "
					+ "CID.codigo AS codigo_cidade, " 
					+ "CID.nome AS nome_cidade, "
					+ "CID.status AS status_cidade, "
					+ "ES.uf, "
					+ "ES.nome AS nome_estado "
					+ "FROM paciente AS PAC "
					+ "LEFT JOIN pessoafisica AS PF ON PAC.codigo_pf = PF.codigo "
					+ "LEFT JOIN pessoa AS PE ON PF.codigo_pessoa = PE.codigo "
					+ "LEFT JOIN cidade AS CID ON PE.codigo_cidade = CID.codigo "
					+ "LEFT JOIN estado AS ES ON CID.uf_estado = ES.uf " + "WHERE " + campo + comparacao + termo;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente paciente = new Paciente();
				pacientes.add(paciente);
				
				paciente.setCodigo(rs.getInt("codigo_pac"));
				ConvenioDAO convenioDAO = new ConvenioDAO();
				paciente.setConvenio(convenioDAO.get(rs.getInt("codigo_convenio")));
				paciente.setResponsavel(get(rs.getInt("codigo_resp")));
				paciente.setStatus(rs.getBoolean("status_pac"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setPossuiNecessidades(rs.getBoolean("possuinecessidades"));
				paciente.setCertidaoNascimento(rs.getString("certidaonascimento"));
				paciente.setNome(rs.getString("nome_pessoa"));
				paciente.setLogradouro(rs.getString("logradouro"));
				if(rs.getDate("datanascimento") != null)
					paciente.setDtNascimento(new Date(rs.getDate("datanascimento").getTime()));
				paciente.setComplemento(rs.getString("complemento"));
				paciente.setNumero(rs.getString("numero"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCep(rs.getString("cep"));
				
				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("codigo_cidade"));
				cidade.setNome(rs.getString("nome_cidade"));
				cidade.setStatus(rs.getBoolean("status_cidade"));

				Estado estado = new Estado();
				estado.setNome(rs.getString("nome_estado"));
				estado.setUF(rs.getString("uf"));

				cidade.setEstado(estado);
				paciente.setCidade(cidade);
				
				TelefoneDAO telefoneDAO = new TelefoneDAO();
				paciente.setTelefones(telefoneDAO.getTelefones(rs.getInt("codigo_pessoa")));
			}
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
		return pacientes;
	}
	
	public Paciente get(int codigo) throws Exception {
		if(codigo > 0) {
			List<Paciente> pacientes = consultar("pac.codigo", " - ", codigo + "");
			if(pacientes.size() > 0)
				pacientes.get(0);
		}
		return null;
	}

	public void salvar(Paciente paciente) throws Exception {
		if(paciente != null)
			if(paciente.getCodigo() == 0)
				inserir(paciente);
			else
				alterar(paciente);
	}

	private void alterar(Paciente paciente) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "UPDATE paciente SET codigo_convenio = ?, codigo_resp = ?, status = ? WHERE codigo = ?";
			ps = conexao.getConexao().prepareStatement(sql);
			if(paciente.getConvenio() != null)
				ps.setInt(1, paciente.getConvenio().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			if(paciente.getResponsavel() != null)
				ps.setInt(2, paciente.getResponsavel().getCodigo());
			else
				ps.setNull(2, Types.INTEGER);
			ps.setBoolean(3, paciente.isStatus());
			ps.setInt(4, paciente.getCodigo());

			ps.executeUpdate();
			ps.close();
			
			ResultSet rs = conexao.getConexao().createStatement().executeQuery("SELECT codigo_pf FROM paciente WHERE codigo = " + paciente.getCodigo());
			if(rs.next()) {
				PessoaFisica pf = paciente.clone();
				pf.setCodigo(rs.getInt("codigo_pf"));
				PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
				pessoaFisicaDAO.salvar(pf);
			}
		} finally {
			conexao.getConexao().close();
		}
	}

	private void inserir(Paciente paciente) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisicaDAO.salvar(paciente);
			
			String sql = "INSERT INTO paciente(codigo_convenio, codigo_pf, codigo_resp, status) VALUES(?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			if(paciente.getConvenio() != null)
				ps.setInt(1, paciente.getConvenio().getCodigo());
			else
				ps.setNull(1, Types.INTEGER);
			
			ps.setInt(2, paciente.getCodigo());
			if(paciente.getResponsavel() != null)
				ps.setInt(3, paciente.getResponsavel().getCodigo());
			else
				ps.setNull(3, Types.INTEGER);
			ps.setBoolean(4, paciente.isStatus());

			ps.executeUpdate();
			ps.close();
			
			ps = conexao.getConexao().prepareStatement("SELECT MAX(codigo) AS lastCodigo FROM paciente");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				paciente.setCodigo(rs.getInt("lastCodigo"));
		} finally {
			conexao.getConexao().close();
		}
	}

}