package br.com.rarp.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.model.Telefone;

public class TelefoneDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("pessoa"))
			throw new Exception("Crie a tabela de pessoa antes de criar a tabela de telefone");
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "telefone(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "numero VARCHAR(12), ";
		sql += "codigo_pessoa INTEGER REFERENCES pessoa(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}

	public void salvar(List<Telefone> telefones, int codigo_pessoa) throws Exception {
		List<Telefone> telefonesInserir = new ArrayList<>();
		List<Telefone> telefonesAlterar = new ArrayList<>();
		for(Telefone t : telefones) {
			if(t != null) {
				if(t.getCodigo() == 0)
					telefonesInserir.add(t);
				else
					telefonesAlterar.add(t);
			}
		}
		inserir(telefonesInserir, codigo_pessoa);
		alterar(telefonesInserir, codigo_pessoa);
	}
	
	public void inserir(List<Telefone> telefones, int codigo_pessoa) throws Exception {
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		String sql= "INSERT INTO telefone(numero, codigo_pessoa, status) VALUES(?,?,?)";
		PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        try {
        	int i = 0;
        	for (Telefone t : telefones) { 
        		ps.setString(1, t.getNumeroSemMascara());
        		ps.setInt(2, codigo_pessoa);
        		ps.setBoolean(3, t.isStatus());
        		ps.addBatch();
                i++;

                if (i == telefones.size()) {
                	ps.executeBatch();
                }
            }
        	ps.executeBatch();
		} finally {
			ps.close();
		}         
	}
	
	public void alterar(List<Telefone> telefones, int codigo_pessoa) throws Exception {
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		String sql= "UPDATE telefone SET numero=?, codigo_usuario=?, status=? WHERE codigo=?";
		PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        try {
        	int i = 0;
        	for (Telefone t : telefones) { 
        		ps.setString(1, t.getNumeroSemMascara());
        		ps.setInt(2, codigo_pessoa);
        		ps.setBoolean(3, t.isStatus());
        		ps.setInt(4, t.getCodigo());
        		ps.addBatch();
                i++;

                if (i == telefones.size()) {
                	ps.executeBatch();
                }
            }
        	ps.executeBatch();
		} finally {
			ps.close();
		}  
	}

	public List<Telefone> getTelefones(int codigo_pessoa) throws Exception {
		List<Telefone> telefones = new ArrayList<>();
		PreparedStatement ps;
		Conexao conexao = SistemaCtrl.getInstance().getConexao();
		try {
			String sql = "SELECT codigo, numero, status FROM telefone WHERE codigo_pessoa = " + codigo_pessoa;
			ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Telefone t = new Telefone();
				telefones.add(t);
				t.setCodigo(rs.getInt("codigo"));
				t.setNumero(rs.getString("numero"));
				t.setStatus(rs.getBoolean("status"));
			}
			ps.close();
		} finally {
			conexao.getConexao().close();
		}
		return telefones;
	}
}
