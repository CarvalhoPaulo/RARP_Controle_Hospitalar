package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.Leito;

public class LeitoDAO {
	
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("espaco"))
			throw new Exception("Crie a tabela de espa�os antes de criar a tabela de usuarios");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "leito(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "numero INTEGER, ";
		sql += "codigo_espaco INTEGER REFERENCES espaco(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
	
	public void salvar(Espaco espaco) throws Exception {
		List<Leito> leitosInserir = new ArrayList<>();
		List<Leito> leitosAlterar = new ArrayList<>();
		
		for(Leito l: espaco.getLeitos()) {
			if(l.getCodigo() == 0)
				leitosInserir.add(l);
			else
				leitosAlterar.add(l);
		}	
	}
	
	public void inserir(List<Leito> leitos, int codigo_espaco) throws Exception {
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		String sql= "INSERT INTO leito(numero, codigo_espaco, status) VALUES(?,?,?)";
		PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        try {
        	int i = 0;
        	for (Leito leito : leitos) {
        		ps.setInt(1, leito.getNumero());
        		ps.setInt(2, codigo_espaco);
        		ps.setBoolean(3, leito.isStatus());
        		ps.addBatch();
                i++;

                if (i == leitos.size()) {
                	ps.executeBatch();
                }
            }
        	ps.executeBatch();
		} finally {
			ps.close();
		}         
	}
	
	public void alterar(List<Leito> leitos, int codigo_espaco) throws Exception {
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		String sql= "UPDATE leito SET numero=?, codigo_espaco=?, status=? WHERE codigo=?";
		PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        try {
        	int i = 0;
        	for (Leito leito : leitos) { 
        		ps.setInt(1, leito.getNumero());
        		ps.setInt(2, codigo_espaco);
        		ps.setBoolean(3, leito.isStatus());
        		ps.setInt(4, leito.getCodigo());
        		ps.addBatch();
                i++;

                if (i == leitos.size()) {
                	ps.executeBatch();
                }
            }
        	ps.executeBatch();
		} finally {
			ps.close();
		}  
	}

	public List<Leito> getLeitos(Espaco espaco) throws Exception {
		List<Leito> leitos = new ArrayList<>();
        PreparedStatement ps;
        Conexao conexao = SistemaCtrl.getInstance().getConexao();
        try {
        	String sql = "SELECT codigo, numero, status FROM leito WHERE codigo_espaco = ?";
            ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, espaco.getCodigo());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	Leito leito = new Leito();
            	leito.setCodigo(rs.getInt("codigo"));
            	leito.setNumero(rs.getInt("numero"));
            	leito.setStatus(rs.getBoolean("status"));
            	leitos.add(leito);
            }
            ps.close();
        } finally{
            conexao.getConexao().close();
        }
		return leitos;
	}
}
