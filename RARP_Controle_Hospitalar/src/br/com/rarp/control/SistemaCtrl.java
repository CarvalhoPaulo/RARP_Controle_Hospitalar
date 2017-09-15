package br.com.rarp.control;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rarp.enums.TipoMovimentacao;
import br.com.rarp.model.Configuracoes;
import br.com.rarp.model.Empresa;
import br.com.rarp.model.Tela;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.CargoDAO;
import br.com.rarp.model.dao.CidadeDAO;
import br.com.rarp.model.dao.Conexao;
import br.com.rarp.model.dao.ConfiguracoesDAO;
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
import br.com.rarp.view.scnConexao.ConexaoController;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SistemaCtrl {
	private static final SistemaCtrl INSTANCE = new SistemaCtrl();
	
	private Usuario usuarioSessao;
	
	private SistemaCtrl() {
		getPropriedades();
	}
	
	public Configuracoes getConfiguracoes() {
		return Configuracoes.getInstance();
	}
	
	public Empresa getEmpresa() {
		return Empresa.getINSTANCE();
	}
	
	public void salvarConfiguracoes() throws Exception {
		new ConfiguracoesDAO().salvar();
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
		return !getConfiguracoes().isControleAcesso();
	}
	
	public static SistemaCtrl getInstance() {
		return INSTANCE;
	}
	
	public List<Tela> getTelas() {
		List<Tela> telas = new ArrayList<>();
		telas.add(new Tela(1, "manutencaoUsuario", "Manutenção de Usuário"));
		telas.add(new Tela(2, "manutencaoPerfilUsuario", "Manutenção de Perfil de Usuário"));
		telas.add(new Tela(3, "manutencaoEspaco", "Manutenção de Espaço"));
		telas.add(new Tela(4, "manutencaoFuncionario", "Manutenção de Funcionario"));
		telas.add(new Tela(5, "manutencaoCargo", "Manutenção de Cargo"));
		telas.add(new Tela(6, "manutencaoCidade", "Manutenção de Cidade"));
		telas.add(new Tela(7, "manutencaoPaciente", "Manutenção de Paciente"));
		telas.add(new Tela(8, "manutencaoConvenio", "Manutenção de Convênio"));
		telas.add(new Tela(9, "manutencaoEspecialidade", "Manutenção de Especialidade"));
		telas.add(new Tela(10, "manutencaoMedico", "Manutenção de Especialidade"));
		
		telas.add(new Tela(11, "controleEntradaPaciente", "Controle de Entrada de Paciente"));
		
		return telas;
	}
	
	public void liberarManutencaoUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoUsuario", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoPerfilUsuario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoPerfilUsuario", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarControleEntradaPaciente(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("controleEntradaPaciente", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoEspaco(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEspaco", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoCargo(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoCargo", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoFuncionario(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoFuncionario", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoCidade(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoCidade", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoPaciente(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoPaciente", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoConvenio(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoConvenio", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoEspecialidade(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoEspecialidade", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public void liberarManutencaoMedico(TipoMovimentacao tipoMovimentacao) throws Exception {
		if(!podeLiberar("manutencaoMedico", tipoMovimentacao))
			throw new Exception("Acesso negado a essa área");
	}
	
	public Propriedades getPropriedades() {
		return Propriedades.getInstance();
	}
	
	public Conexao getConexao() throws Exception {
		return new Conexao();
	}
	
	public void configuraConexao() throws Exception {
		try {
			SistemaCtrl.getInstance().getConexao().getConexao();
		} catch (Exception e) {
			
			try {
				ConexaoController conexaoController = new ConexaoController();
				conexaoController.configurar();
				
				SistemaCtrl.getInstance().getConexao().getConexao();
			}catch(Exception e2) {
				System.out.println(e.getMessage());
				SistemaCtrl.getInstance().getConexao().criarDataBase();
			}
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
		PacienteDAO.criarTabela();
		LeitoDAO.criarTabela();
		EspecialidadeDAO.criarTabela();
		MedicoDAO.criarTabela();
		ConfiguracoesDAO.criarTabela();
		
		
		//SQLDAO sqldao = new SQLDAO();
		//sqldao.executarSQLFile("cidades_estados.sql");
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	public void getConfiguracoesDB() throws Exception {
		// TODO Auto-generated method stub 
		new ConfiguracoesDAO().getConfiguracoes();
	}
	
	

}
