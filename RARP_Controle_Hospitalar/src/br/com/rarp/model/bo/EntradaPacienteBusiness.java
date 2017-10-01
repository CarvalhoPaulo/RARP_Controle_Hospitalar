package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.control.SistemaCtrl;
import br.com.rarp.enums.StatusAtendimento;
import br.com.rarp.model.Atendimento;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.model.Espaco;
import br.com.rarp.model.Leito;
import br.com.rarp.model.dao.EntradaPacienteDAO;
import br.com.rarp.model.dao.EspacoDAO;
import br.com.rarp.model.dao.LeitoDAO;

public class EntradaPacienteBusiness {

	public void salvar(EntradaPaciente entradaPaciente) throws Exception {
		if(entradaPaciente == null)
			throw new Exception("A entrada de paciente não foi instânciada");
		
		if(entradaPaciente.isStatus())
			validarEntradaPaciente(entradaPaciente);
		
		Espaco recepcao = new EspacoDAO().getRecepcao();
		if(recepcao == null) {
			recepcao = SistemaCtrl.getInstance().getRecepcao();
			recepcao.getLeitos().get(0).setPaciente(entradaPaciente.getPaciente());
			if(new LeitoDAO().consultar("codigo_paciente = " + entradaPaciente.getPaciente().getCodigo()).size() == 0)
				new EspacoDAO().salvar(recepcao);
		} else {
			Leito livre = new Leito(1);
			for(Leito leito: recepcao.getLeitos()) {
				if(leito.getPaciente() == null) {
					livre = leito;
					break;
				}
			}
			livre.setPaciente(entradaPaciente.getPaciente());
			livre.setEspaco(recepcao);
			LeitoDAO leitoDAO = new LeitoDAO();
			if(leitoDAO.consultar("codigo_paciente = " + entradaPaciente.getPaciente().getCodigo()).size() == 0)
				leitoDAO.salvar(livre);
		}
		EntradaPacienteDAO entradaPacienteDAO = new EntradaPacienteDAO();
		entradaPacienteDAO.salvar(entradaPaciente);		
	}
	
	private void validarEntradaPaciente(EntradaPaciente entradaPaciente) throws Exception {
		if (entradaPaciente != null) {
			if (entradaPaciente.getMedico() == null && entradaPaciente.getAtendimentos().size() > 0)
				throw new Exception("Para cadastrar os atendimentos para esta entrada de paciente é necessário informar o médico");			
			if(entradaPaciente.isAlta()) {
				if (entradaPaciente.getMedico() == null)
					throw new Exception("Para cadastrar uma entrada de paciente é necessário informar o médico");
				
				boolean realizado = !entradaPaciente.getAtendimentos().isEmpty();
				for(Atendimento a: entradaPaciente.getAtendimentos()) {
					if(a.getStatusAtendimento() == StatusAtendimento.realizado) {
						realizado = true;
						break;
					}
				}	
				
				if(!realizado)
					throw new Exception("Para cadastrar uma entrada de paciente é necessário possuir pelo menos um atendimento realizado");
				
			}
		}
	}

	public List<EntradaPaciente> consultar(String campo, String comparacao, String termo) throws Exception {
		EntradaPacienteDAO entradaPacienteDAO = new EntradaPacienteDAO();
		return entradaPacienteDAO.consultar(campo, comparacao, termo);
	}

}
