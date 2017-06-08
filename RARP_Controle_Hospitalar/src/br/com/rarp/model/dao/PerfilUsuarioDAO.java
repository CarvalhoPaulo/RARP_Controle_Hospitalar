package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.PerfilUsuario;

public class PerfilUsuarioDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {			
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "perfilUsuario(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
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
            
            ResultSet rs = ps.executeQuery("SELECT MAX(codigo) FROM perfilusuario");
            if(rs.next())
            	perfilUsuario.setCodigo(rs.getInt(0));
            
            TelaDAO telaDAO = new TelaDAO();
            telaDAO.salvar(perfilUsuario);
            ps.close();
            
            
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar perfil de usuario");
        }finally{
            conexao.getConexao().close();
        }
	}
	
	private void alterar(PerfilUsuario perfilUsuario) {
		
	}
	
}
