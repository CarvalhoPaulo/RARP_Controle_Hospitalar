package br.com.rarp.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.rarp.control.SistemaCtrl;

public class SaidaPacienteDAO {
	public static void criarTabela() throws ClassNotFoundException, SQLException, Exception {
		if (!SistemaCtrl.getInstance().tabelaExiste("movimentacao"))
			throw new Exception("Crie a tabela de movimentação antes de criar a tabela de encaminhamento");
		
		if (!SistemaCtrl.getInstance().tabelaExiste("entradapaciente"))
			throw new Exception("Crie a tabela de entrada de pacientes antes de criar a tabela de encaminhamento");
		
		Statement st = SistemaCtrl.getInstance().getConexao().getConexao().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS ";
		sql += "saidapaciente(";
		sql += "codigo SERIAL NOT NULL PRIMARY KEY, ";
		sql += "estadopaciente VARCHAR, ";
		sql += "codigo_mov INTEGER REFERENCES movimentacao(codigo), ";
		sql += "codigo_entrada INTEGER REFERENCES entradapaciente(codigo), ";
		sql += "status boolean)";
		st.executeUpdate(sql);
	}
}
