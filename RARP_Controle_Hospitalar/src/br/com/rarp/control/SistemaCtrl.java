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
				return usuarioSessao.getPerfilUsuario().getTelas()
						.get(usuarioSessao.getPerfilUsuario().getTelas().indexOf(new Tela(tela))).isStatus();

			case insercao:
				if (usuarioSessao.getPerfilUsuario().getTelas().contains(new Tela(tela)))
					return usuarioSessao.getPerfilUsuario().getTelas()
							.get(usuarioSessao.getPerfilUsuario().getTelas().indexOf(new Tela(tela))).isPodeInserir();

			case alteracao:
				if (usuarioSessao.getPerfilUsuario().getTelas().contains(new Tela(tela)))
					return usuarioSessao.getPerfilUsuario().getTelas()
							.get(usuarioSessao.getPerfilUsuario().getTelas().indexOf(new Tela(tela))).isPodeAlterar();

			case visualizaco:
				if (usuarioSessao.getPerfilUsuario().getTelas().contains(new Tela(tela)))
					return usuarioSessao.getPerfilUsuario().getTelas()
							.get(usuarioSessao.getPerfilUsuario().getTelas().indexOf(new Tela(tela)))
							.isPodeVisualizar();

			case desativacao:
				if (usuarioSessao.getPerfilUsuario().getTelas().contains(new Tela(tela)))
					return usuarioSessao.getPerfilUsuario().getTelas()
							.get(usuarioSessao.getPerfilUsuario().getTelas().indexOf(new Tela(tela))).isPodeDesativar();
			}
		}
		return !getPropriedades().getControleAcesso();
	}
	
	public static SistemaCtrl getInstance() {
		return INSTANCE;
	}
	
	public List<Tela> getTelas() {
		List<Tela> telas = new ArrayList<>();
		
		telas.add(new Tela("manutencaoUsuario", "Manutenção de Usuário"));
		telas.add(new Tela("manutencaoPerfilUsuario", "Manutenção de Perfil de Usuário"));
		telas.add(new Tela("manutencaoEntradaPaciente", "Manutenção de Entrada de Paciente"));
		telas.add(new Tela("manutencaoEspaco", "Manutenção de Espaço"));
		telas.add(new Tela("manutencaoFuncionario", "Manutenção de Funcionario"));
		telas.add(new Tela("manutencaoCargo", "Manutenção de Cargo"));
		return telas;
	}
	
	public void liberarManutencaoUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoUsuario", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
	}
	
	public void liberarManutencaoPerfilUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoPerfilUsuario", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
	}
	
	public void liberarManutencaoEntradaPaciente(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEntradaPaciente", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
	}
	
	public void liberarManutencaoEspaco(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEspaco", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
	}
	
	public void liberarManutencaoCargo(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoCargo", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
	}
	
	public void liberarManutencaoFuncionario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoFuncionario", tipoMovimentacao))
			throw new Exception("Ação indisponível para este usuario");
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
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		getPropriedades().setControleAcesso(usuarioSessao != null);
		this.usuarioSessao = usuarioSessao;
	}

}
