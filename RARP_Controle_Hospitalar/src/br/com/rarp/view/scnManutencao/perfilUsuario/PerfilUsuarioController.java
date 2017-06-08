package br.com.rarp.view.scnManutencao.perfilUsuario;

import br.com.rarp.control.PerfilUsuarioCtrl;
import br.com.rarp.model.PerfilUsuario;
import br.com.rarp.model.Tela;
import br.com.rarp.utils.Utilitarios;
import br.com.rarp.view.scnCadastroPerfilUsuario.CadastroPerfilUsuarioController;
import br.com.rarp.view.scnCadastroUsuario.CadastroUsuarioController;
import br.com.rarp.view.scnManutencao.ManutencaoController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PerfilUsuarioController extends ManutencaoController {

	@SuppressWarnings("unchecked")
	@Override
	public void prepararTela() {
		getLblTitle().setText("Manutenção de Perfil de Usuário");
		
		TableColumn<PerfilUsuario, String> codigo = new TableColumn<>("Código");
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<PerfilUsuario, String> nome = new TableColumn<>("Nome");
		codigo.setCellValueFactory(new PropertyValueFactory<>("nome"));
		getTvManutencao().getColumns().addAll(codigo, nome);
	}

	@Override
	public void pesquisar() {
		Utilitarios.atencao("Função do botão Pesquisar");
	}

	@Override
	public void inserir() {
		try {
			CadastroPerfilUsuarioController controller = new CadastroPerfilUsuarioController();
			controller.inserir();
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes");
			e.printStackTrace();
		}
	}

	@Override
	public void alterar() {
		try {
			CadastroPerfilUsuarioController controller = new CadastroPerfilUsuarioController();
			PerfilUsuarioCtrl perfilUsuarioCtrl = new PerfilUsuarioCtrl();
			perfilUsuarioCtrl.novoPerfilUsuario();
			perfilUsuarioCtrl.getPerfilUsuario().setNome("Super Administrador");
			Tela tela = new Tela("manutencaoEntradaPaciente", "Manutenção de Entrada de Paciente");
			tela.setPodeInserir(true);
			tela.setPodeDesativar(true);
			perfilUsuarioCtrl.getPerfilUsuario().getTelas().add(tela);
			controller.alterar(perfilUsuarioCtrl);;
		} catch (Exception e) {
			Utilitarios.erro("Erro ao criar tela de cadastro de pacientes");
			e.printStackTrace();
		}
	}

	@Override
	public void visualizar() {
		Utilitarios.atencao("Função do botão Visualizar");
	}

}
