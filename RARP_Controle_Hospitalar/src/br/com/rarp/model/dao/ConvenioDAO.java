package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class ConvenioDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if(!SistemaCtrl.getInstance().tabelaExiste("pessoajuridica"))
			throw new Exception("Crie a tabela de pessoa juridica antes de criar a tabela de convênio");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "convenio(";
		sql += "codigo INTEGER NOT NULL PRIMARY KEY, ";
		sql += "nome VARCHAR(50), ";
		sql += "ans VARCHAR(50), ";
		sql += "tipo INTEGER, ";
		sql += "codigo_pj INTEGER REFERENCES pessoaJuridica(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
