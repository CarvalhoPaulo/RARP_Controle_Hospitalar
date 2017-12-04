package br.com.rarp.control;

import java.util.List;

import org.com.rarp.interfaces.Consulta;
import org.com.rarp.interfaces.PessoaFisica;
import org.com.rarp.interfaces.Requisicao;
import org.com.rarp.interfaces.Resposta;
import org.com.rarp.soap.ConsultaSOAP;

import br.com.rarp.utils.Utilitarios;

public class CosultaCtrl {
	private ConsultaSOAP consultaSOAP = new ConsultaSOAP();
	private Consulta consulta = consultaSOAP.getConsultaSOAPPort();
	private PessoaFisica pessoaFisica = new PessoaFisica();
	private Requisicao requisicao = new Requisicao();
	private List<Resposta> list;

	public void consultar() throws Exception {
		try {
			consulta.sevidorOn(SistemaCtrl.getInstance().getConfiguracoes().getUsuarioRARP());
			consulta.sevidorOn(SistemaCtrl.getInstance().getConfiguracoes().getUsuarioRARP());
			PessoaFisica pessoaFisica = new PessoaFisica();

			requisicao.setPessoaFisica(pessoaFisica);
			list = consulta.consultar(requisicao);

			if ((list != null) && (list.size() > 0)) {
				Utilitarios.message("soufoda");
			}

		} catch (Exception e) {
			throw new Exception("Falha realizar consulta");
		}
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

}
