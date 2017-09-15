package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.PerfilUsuario;

public class PerfilUsuarioDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "perfilUsuario(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
		
		sql = "INSERT INTO perfilUsuario(codigo, nome, status) "
				+ "SELECT 1, 'Super Administrador', 'TRUE' WHERE NOT EXISTS "
				+ "("
					+ "SELECT codigo FROM perfilUsuario WHERE codigo = 1"
				+ ")";
		st.executeUpdate(sql);
	}
	
	public PerfilUsuario consultar(int codigo) throws SQLException, Exception {
		List<PerfilUsuario> perfilUsuarios =  consultar("codigo", " = ", codigo + "");
		if(perfilUsuarios.size() > 0)
			return perfilUsuarios.get(0);
		else
			return null;
	}
	
	public List<PerfilUsuario> consultar(String campo, String comparacao, String termo) throws SQLException, Exception {
		List<PerfilUsuario> perfilUsuarios = new ArrayList<>();
        PreparedStatement ps;
        Conexao conexao = SistemaCtrl.getInstance().getConexao();
        try {
        	String sql = "SELECT codigo, nome, status FROM perfilusuario WHERE " + campo + comparacao + termo;
            ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	PerfilUsuario perfilUsuario = new PerfilUsuario();
            	perfilUsuario.setCodigo(rs.getInt("codigo"));
            	perfilUsuario.setNome(rs.getString("nome"));
            	perfilUsuario.setStatus(rs.getBoolean("status"));
            	perfilUsuario.setTelas(new TelaDAO().getTelas(perfilUsuario));
            	perfilUsuarios.add(perfilUsuario);
            }
            ps.close();
        } finally{
            conexao.getConexao().close();
        }
		return perfilUsuarios;
	}

	public void salvar(PerfilUsuario perfilUsuario) throws Exception {
		if(perfilUsuario != null) 
			if(perfilUsuario.getCodigo() == 0)
				inserir(perfilUsuario);
			else
				alterar(perfilUsuario);
	}
	
	private void inserir(PerfilUsuario perfilUsuario) throws Exception {
        PreparedStatement ps;
        Conexao conexao = SistemaCtrl.getInstance().getConexao();
        try {
            String sql= "INSERT INTO perfilusuario(nome, status) VALUES(?,?)";
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, perfilUsuario.getNome());
            ps.setBoolean(2, perfilUsuario.isStatus());            
            ps.executeUpdate();
            ps.close();
           
            ps = conexao.getConexao().prepareStatement("SELECT MAX(codigo) AS lastCodigo FROM perfilusuario");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            	perfilUsuario.setCodigo(rs.getInt("lastCodigo"));
            
            TelaDAO telaDAO = new TelaDAO();
            telaDAO.salvar(perfilUsuario);
            ps.close();
        } finally{
            conexao.getConexao().close();
        }
	}
	
	private void alterar(PerfilUsuario perfilUsuario) throws Exception {
        PreparedStatement ps;
        Conexao conexao = SistemaCtrl.getInstance().getConexao();
        try {
            String sql= "UPDATE perfilusuario SET nome=?, status=? WHERE codigo=?";
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, perfilUsuario.getNome());
            ps.setBoolean(2, perfilUsuario.isStatus());
            ps.setInt(3, perfilUsuario.getCodigo());
            ps.executeUpdate();   
            TelaDAO telaDAO = new TelaDAO();
            telaDAO.salvar(perfilUsuario);
            ps.close();
        } finally{
            conexao.getConexao().close();
        }		
	}
	
}
