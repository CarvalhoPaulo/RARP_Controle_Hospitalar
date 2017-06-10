package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class PessoaDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("cidade"))
			throw new Exception("Crie a tabela de cidade antes de criar a tabela de pessoa");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "pessoa(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(255), ";
		sql += "logradouro VARCHAR(255), ";
		sql += "complemento VARCHAR(255), ";
		sql += "numero VARCHAR(50), ";
		sql += "bairro VARCHAR(255), ";
		sql += "cep VARCHAR(9), ";
		sql += "codigo_cidade INTEGER REFERENCES cidade(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
