package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Usuario;

public class UsuarioDAO {

	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("funcionario"))
			throw new Exception("Crie a tabela de funcionarios antes de criar a tabela de usuarios");

		if (!SistemaCtrl.getInstance().tabelaExiste("perfilusuario"))
			throw new Exception("Crie a tabela de perfil de usuarios antes de criar a tabela de usuarios");

		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "usuario(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(225), ";
		sql += "usuario VARCHAR(225) NOT NULL UNIQUE, ";
		sql += "password VARCHAR(225), ";
		sql += "codigo_funcionario INTEGER REFERENCES funcionario(codigo), ";
		sql += "codigo_perfilusuario Integer REFERENCES perfilusuario(codigo), ";
		sql += "status BOOLEAN)";
		st.executeUpdate(sql);
	}

	public void salvar(Usuario usuario) throws Exception {
		if (usuario.getCodigo() == 0)
			inserir(usuario);
		else
			alterar(usuario);
	}

	private void inserir(Usuario usuario) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "INSERT INTO usuario(nome, usuario, password, codigo_funcionario, codigo_perfilusuario, status) VALUES(?,?,?,?,?,?)";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			if (usuario.getSenha() != null)
				ps.setLong(3, usuario.getSenha().hashCode());
			else
				ps.setString(3, usuario.getSenha());

			if (usuario.getFuncionario() != null)
				ps.setInt(4, usuario.getFuncionario().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);

			if (usuario.getPerfilUsuario() != null)
				ps.setInt(5, usuario.getPerfilUsuario().getCodigo());
			else
				ps.setNull(5, Types.INTEGER);

			ps.setBoolean(6, usuario.isStatus());
			ps.executeUpdate();
			ps.close();

			ps = conexao.getConexao().prepareStatement("SELECT MAX(codigo) AS lastCodigo FROM usuario");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				usuario.setCodigo(rs.getInt("lastCodigo"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

			throw new Exception("Falha ao sallvar Usuario");
		} finally {
			conexao.getConexao().close();
		}
	}

	private void alterar(Usuario usuario) throws Exception {
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "Update usuario SET nome=?, usuario=?, password=?, codigo_funcionario=?, codigo_perfilusuario=?, status=? WHERE codigo=?";
			ps = conexao.getConexao().prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			if (usuario.getSenha() != null)
				ps.setLong(3, usuario.getSenha().hashCode());
			else
				ps.setString(3, usuario.getSenha());
			if (usuario.getFuncionario() != null)
				ps.setInt(4, usuario.getFuncionario().getCodigo());
			else
				ps.setNull(4, Types.INTEGER);

			if (usuario.getPerfilUsuario() != null)
				ps.setInt(5, usuario.getPerfilUsuario().getCodigo());
			else
				ps.setNull(5, Types.INTEGER);
			ps.setBoolean(6, usuario.isStatus());
			ps.setInt(7, usuario.getCodigo());
			ps.executeUpdate();
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
	}

	public List<Usuario> consultar(String campo, String comparacao, String termo) throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "SELECT codigo, nome, usuario, password, codigo_funcionario, codigo_perfilusuario, status FROM usuario WHERE "
					+ campo + comparacao + termo;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("codigo"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("password"));

				PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO();
				usuario.setPerfilUsuario(perfilUsuarioDAO.consultar(rs.getInt("codigo_perfilusuario")));

				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				usuario.setFuncionario(funcionarioDAO.consultar(rs.getInt("codigo_funcionario")));

				usuario.setStatus(rs.getBoolean("status"));
				usuarios.add(usuario);
			}
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
		return usuarios;
	}

	public Usuario getUsuario(int codigo) throws Exception {
		List<Usuario> usuarios = consultar("codigo", " = ", "" + codigo);
		return usuarios.get(0);
	}

}
