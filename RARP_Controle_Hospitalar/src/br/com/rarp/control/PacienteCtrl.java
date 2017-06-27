package br.com.rarp.control;

import br.com.rarp.model.Paciente;
import br.com.rarp.model.bo.PacienteBusiness;

public class PacienteCtrl {
	private Paciente paciente;

	public Paciente getFuncionario() {
		return paciente;
	}

	public void setFuncionario(Paciente pacientes) {
		this.paciente = pacientes;
	}

	public void salvar() throws Exception {
		PacienteBusiness pacienteBusiness = new PacienteBusiness();
		validarDadosObrigatorios();
		PacienteBusiness.salvar(paciente);
	}

	private void validarDadosObrigatorios() throws Exception {
		if (paciente.getNome().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o nome");
		}
		if (paciente.getCpf().isEmpty()) {
			throw new Exception("Para cadastrar um paciente é necessário informar o CPF");
		}
		if (paciente.getCTPS().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o CTPS");
		}
		if (paciente.getLogradouro().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o endereço");
		}
		if (paciente.getRg().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o RG");
		}
		if (paciente.getSexo().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o sexo");
		}
		if (paciente.getCidade().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar uma cidade");
		}
		if (paciente.getBairro().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar um bairro");
		}
		if (paciente.getDtNascimento().toString().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar a data de nascimento");
		}
		if (paciente.getNumero().equals("")) {
			throw new Exception("Para cadastrar um paciente é necessário informar o número da casa");
		}
		if (!paciente.isPossuiNecessidades()) {
			throw new Exception("Para cadastrar um paciente é necessário informar se possui necessidades");
		}
		if (paciente.getConvenios().equals("")) {
			throw new Exception("Paciente necessita de um convênio (SUS Obrigatório)");
		}
	}

	public void novoFuncionario() {
		paciente = new Paciente();
	}

}
