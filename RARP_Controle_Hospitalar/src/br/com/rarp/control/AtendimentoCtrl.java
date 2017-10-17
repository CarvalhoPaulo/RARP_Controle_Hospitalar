package br.com.rarp.control;

import br.com.rarp.interfaces.Comparacao;
import br.com.rarp.model.Atendimento;
import br.com.rarp.model.bo.AtendimentoBusiness;
import br.com.rarp.utils.Campo;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendimentoCtrl {
	private Atendimento atendimento;

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Object atendimento) {
		this.atendimento = (Atendimento) atendimento;
	}
	
	public void novoAtendimento() {
		atendimento = new Atendimento();
	}
	
	public boolean salvar() throws Exception {
		if (atendimento == null)
			throw new Exception("O atendimento não foi instânciada");

		if (confirmarDesativacao()) {
			if (atendimento.isStatus())
				validarDadosObrigatorios();
			AtendimentoBusiness atendimentoBusiness = new AtendimentoBusiness();
			atendimentoBusiness.salvar(atendimento);
			return true;
		} else {
			return false;
		}
	}

	private boolean confirmarDesativacao() {
		if(atendimento != null && !atendimento.isStatus())
			return Utilitarios.pergunta("Tem certeza que você deseja desativar este atendimento?");
		return true;
	}

	private void validarDadosObrigatorios() throws Exception {
		if(atendimento.getDtMovimentacao() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a data");
		
		if(atendimento.getHrMovimentacao() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a hora");
		
		if(atendimento.getResponsavel() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar o funcionário responsável pelo atendimento");
		
		if(atendimento.getDataAtendimento() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a data do atendimento");
		
		if(atendimento.getHoraIni() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a hora do inicio do atendimento");
		
		if(atendimento.getHoraFim() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a hora do fim do atendimento");
		
		if(atendimento.getEntradaPaciente() == null)
			throw new Exception("Para cadastrar um atendimento é necessário informar a entrada de paciente");
	}

	public ObservableList<Atendimento> consultar(Campo campo, Comparacao comparacao, String termo) throws ClassNotFoundException, Exception {
		return FXCollections.observableList(new AtendimentoBusiness().consultar(campo.getNome(), comparacao.getComparacao(), comparacao.getTermo(termo)));
	}
	
	@Override
	public AtendimentoCtrl clone() throws CloneNotSupportedException {
		AtendimentoCtrl a = new AtendimentoCtrl();
		a.setAtendimento(atendimento.clone());
		return a;
	}
}
