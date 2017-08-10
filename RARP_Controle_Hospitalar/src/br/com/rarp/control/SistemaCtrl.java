package br.com.rarp.control;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.control.Enum.TipoMovimentacao;
import br.com.rarp.model.Tela;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.CargoDAO;
import br.com.rarp.model.dao.CidadeDAO;
import br.com.rarp.model.dao.Conexao;
import br.com.rarp.model.dao.ConvenioDAO;
import br.com.rarp.model.dao.EspacoDAO;
import br.com.rarp.model.dao.EspecialidadeDAO;
import br.com.rarp.model.dao.EstadoDAO;
import br.com.rarp.model.dao.FuncionarioDAO;
import br.com.rarp.model.dao.LeitoDAO;
import br.com.rarp.model.dao.MedicoDAO;
import br.com.rarp.model.dao.PacienteDAO;
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
	
	public boolean podeLiberar(String tela, TipoMovimentacao tipo) {
		if (usuarioSessao != null && usuarioSessao.getPerfilUsuario() != null && usuarioSessao.getPerfilUsuario().getTelas().size() > 0) {
			switch (tipo) {
			case acesso:
				boolean permitido = false;
				for(Tela t: usuarioSessao.getPerfilUsuario().getTelas())
					if(t.getNome().equals(tela))
						permitido = t.isStatus();
				return permitido;

			case insercao:
				boolean podeInserir = false;
				for(Tela t: usuarioSessao.getPerfilUsuario().getTelas())
					if(t.getNome().equals(tela))
						podeInserir = t.isPodeInserir();
				return podeInserir;

			case alteracao:
				boolean podeAlterar = false;
				for(Tela t: usuarioSessao.getPerfilUsuario().getTelas())
					if(t.getNome().equals(tela))
						podeAlterar = t.isPodeAlterar();
				return podeAlterar;

			case visualizaco:
				boolean podeVisualizar = false;
				for(Tela t: usuarioSessao.getPerfilUsuario().getTelas())
					if(t.getNome().equals(tela))
						podeVisualizar = t.isPodeVisualizar();
				return podeVisualizar;

			case desativacao:
				boolean podeDesativar = false;
				for(Tela t: usuarioSessao.getPerfilUsuario().getTelas())
					if(t.getNome().equals(tela))
						podeDesativar = t.isPodeDesativar();
				return podeDesativar;
			}
		}
		return !getPropriedades().getControleAcesso();
	}
	
	public static SistemaCtrl getInstance() {
		return INSTANCE;
	}
	
	public List<Tela> getTelas() {
		List<Tela> telas = new ArrayList<>();
		
		telas.add(new Tela("manutencaoUsuario", "Manuten��o de Usu�rio"));
		telas.add(new Tela("manutencaoPerfilUsuario", "Manuten��o de Perfil de Usu�rio"));
		telas.add(new Tela("manutencaoEntradaPaciente", "Manuten��o de Entrada de Paciente"));
		telas.add(new Tela("manutencaoEspaco", "Manuten��o de Espa�o"));
		telas.add(new Tela("manutencaoFuncionario", "Manuten��o de Funcionario"));
		telas.add(new Tela("manutencaoCargo", "Manuten��o de Cargo"));
		telas.add(new Tela("manutencaoCidade", "Manuten��o de Cidade"));
		telas.add(new Tela("manutencaoPaciente", "Manuten��o de Paciente"));
		telas.add(new Tela("manutencaoConvenio", "Manuten��o de Conv�nio"));
		
		return telas;
	}
	
	public void liberarManutencaoUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoUsuario", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoPerfilUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoPerfilUsuario", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoEntradaPaciente(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEntradaPaciente", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoEspaco(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEspaco", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoCargo(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoCargo", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoFuncionario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoFuncionario", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoCidade(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoCidade", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoPaciente(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoPaciente", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
	}
	
	public void liberarManutencaoConvenio(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoConvenio", tipoMovimentacao))
			throw new Exception("A��o indispon�vel para este usuario");
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
		EspacoDAO.criarTabela();
		LeitoDAO.criarTabela();
		EspecialidadeDAO.criarTabela();
		MedicoDAO.criarTabela();
		PacienteDAO.criarTabela();
		
		//SQLDAO sqldao = new SQLDAO();
		//sqldao.executarSQLFile("cidades_estados.sql");
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		getPropriedades().setControleAcesso(usuarioSessao != null);
		this.usuarioSessao = usuarioSessao;
	}

}
