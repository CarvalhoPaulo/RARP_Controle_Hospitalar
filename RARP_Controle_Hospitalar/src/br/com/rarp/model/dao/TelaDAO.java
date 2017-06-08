package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.Tela;

public class TelaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(SistemaCtrl.getInstance().tabelaExiste("perfilUsuario"))
			throw new Exception("Crie a tabela de perfil de usuario antes de criar a tabela de tela");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "tela(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(100), ";
		sql += "descricao VARCHAR(255), ";
		sql += "podeInserir BOOLEAN, ";
		sql += "podeAlterar BOOLEAN, ";
		sql += "podeVisualizar BOOLEAN, ";
		sql += "podeDesativar BOOLEAN, ";
		sql += "codigo_perfilusuario INTEGER REFERENCES perfilusuario(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(PerfilUsuario perfilUsuario) throws Exception {
		List<Tela> telasInserir = new ArrayList<>();
		List<Tela> telasAlterar = new ArrayList<>();
		for(Tela tela: perfilUsuario.getTelas()) {
			if(tela != null) {
				if(tela.getCodigo() == 0)
					telasInserir.add(tela);
				else
					telasAlterar.add(tela);
			}
		}
		inserir(telasInserir, perfilUsuario.getCodigo());
		alterar(telasAlterar, perfilUsuario.getCodigo());
	}
	
	public void inserir(List<Tela> telas, int codigo_perfilUsuario) throws Exception {
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		String sql= "INSERT INTO tela(nome, descricao, podeInserir, podeAlterar, podeVisualizar, podeDesativar, codigo_perfilusuario, status) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        try {
        	int i = 0;
        	for (Tela tela : telas) { 
        		ps.setString(1, tela.getNome());
        		ps.setString(2, tela.getDescricao());
        		ps.setBoolean(3, tela.isPodeInserir());
        		ps.setBoolean(4, tela.isPodeAlterar());
        		ps.setBoolean(5, tela.isPodeVisualizar());
        		ps.setBoolean(6, tela.isPodeDesativar());
        		ps.setInt(7, codigo_perfilUsuario);
        		ps.setBoolean(8, tela.isStatus());
        		ps.addBatch();
                i++;

                if (i == telas.size()) {
                	ps.executeBatch();
                }
            }
        	ps.executeBatch();
		} catch (Exception e) {
			throw new Exception("Erro ao gravar telas do perfil de usuario");
		} finally {
			ps.close();
		}         
	}
	
	public void alterar(List<Tela> telas, int codigo_perfilUsuario) {
		
	}
}