package br.com.rarp.control;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rarp.model.dao.Conexao;
import br.com.rarp.model.dao.FuncionarioDAO;
import br.com.rarp.model.dao.PerfilUsuarioDAO;
import br.com.rarp.model.dao.Propriedades;
import br.com.rarp.model.dao.UsuarioDAO;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SistemaCtrl {
	private static final SistemaCtrl INSTANCE = new SistemaCtrl();
	private Conexao conexao;
	
	private SistemaCtrl() {
		getPropriedades();
	}
	
	public static SistemaCtrl getInstance() {
		return INSTANCE;
	}
	
	public Propriedades getPropriedades() {
		return Propriedades.getInstance();
	}
	
	public Conexao getConexao() throws Exception {
		if (conexao == null)
			conexao = new Conexao();
		return conexao;
	}

	public Stage getStage() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.APPLICATION_MODAL);
		return stage;
	}
	
	public boolean tabelaExiste(String nome) throws ClassNotFoundException, SQLException, Exception {
		DatabaseMetaData dbm = SistemaCtrl.getInstance().getConexao().getConexao().getMetaData();
		ResultSet rs = dbm.getTables(null, null, nome, null);
		return rs.next();		
	}
	
	public void criarTabelas() throws ClassNotFoundException, SQLException, Exception {
		FuncionarioDAO.criarTabela();
		PerfilUsuarioDAO.criarTabela();
		UsuarioDAO.criarTabela();
		
	}

}
