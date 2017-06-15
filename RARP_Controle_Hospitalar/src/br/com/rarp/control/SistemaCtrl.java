package br.com.rarp.control;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.CargoDAO;
import br.com.rarp.model.dao.CidadeDAO;
import br.com.rarp.model.dao.Conexao;
import br.com.rarp.model.dao.ConvenioDAO;
import br.com.rarp.model.dao.EstadoDAO;
import br.com.rarp.model.dao.FuncionarioDAO;
import br.com.rarp.model.dao.PerfilUsuarioDAO;
import br.com.rarp.model.dao.PessoaDAO;
import br.com.rarp.model.dao.PessoaFisicaDAO;
import br.com.rarp.model.dao.PessoaJuridicaDAO;
import br.com.rarp.model.dao.Propriedades;
import br.com.rarp.model.dao.TelaDAO;
import br.com.rarp.model.dao.TelefoneDAO;
import br.com.rarp.model.dao.UsuarioDAO;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SistemaCtrl {
	private static final SistemaCtrl INSTANCE = new SistemaCtrl();
	private Conexao conexao;
	private Usuario usuarioSessao;
	
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
	
	public void configuraConexao() throws Exception {
		try {
			SistemaCtrl.getInstance().getConexao().getConexao();
		} catch (Exception e) {
			SistemaCtrl.getInstance().getConexao().criarDataBase();
		}
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
		PerfilUsuarioDAO.criarTabela();
		EstadoDAO.criarTabela();
		CidadeDAO.criarTabela();
		PessoaDAO.criarTabela();
		TelefoneDAO.criarTabela();
		PessoaFisicaDAO.criarTabela();
		PessoaJuridicaDAO.criarTabela();
		ConvenioDAO.criarTabela();
		CargoDAO.criarTabela();
		FuncionarioDAO.criarTabela();
		TelaDAO.criarTabela();
		UsuarioDAO.criarTabela();
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		getPropriedades().setControleAcesso(usuarioSessao != null);
		this.usuarioSessao = usuarioSessao;
	}

}
