package br.com.rarp.control;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.List;

import org.com.rarp.interfaces.Consulta;
import org.com.rarp.interfaces.Exception_Exception;
import org.com.rarp.interfaces.PessoaFisica;
import org.com.rarp.interfaces.Requisicao;
import org.com.rarp.interfaces.Resposta;
import org.com.rarp.soap.ConsultaSOAP;

import br.com.rarp.model.Atendimento;
import br.com.rarp.model.EntradaPaciente;
import br.com.rarp.utils.Utilitarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.reflect.Field;

public class CosultaCtrl {
	private ConsultaSOAP consultaSOAP = new ConsultaSOAP();
	private Consulta consulta = consultaSOAP.getConsultaSOAPPort();
	private PessoaFisica pessoaFisica = new PessoaFisica();
	private Requisicao requisicao = new Requisicao();

	public ObservableList consultar() throws Exception {
		try {
			List<Resposta> list;
			consulta.sevidorOn(SistemaCtrl.getInstance().getConfiguracoes().getUsuarioRARP());
			consulta.sevidorOn(SistemaCtrl.getInstance().getConfiguracoes().getUsuarioRARP());
			

			requisicao.setPessoaFisica(pessoaFisica);
			list = consulta.consultar(requisicao);

			List<Atendimento> listA = new ArrayList<>();
			if ((list != null) && (list.size() > 0)) {
				
				for (Resposta resposta : list) {
					
					for(org.com.rarp.interfaces.EntradaPaciente entradaPaciente : resposta.getHistorico().getEntradaPacientes()) {
						
						EntradaPaciente ep = new EntradaPaciente();
						ep  = (EntradaPaciente) copyAttributesFromTo(ep,entradaPaciente);
					}
				}
			}
			return FXCollections.observableArrayList(listA);

		} catch (Exception e) {
			throw new Exception("Falha realizar consulta");
		}
	}
	
	public  Object copyAttributesFromTo(Object a, Object b) throws IllegalArgumentException, IllegalAccessException {
		Field[] fieldsFromFirstClass = a.getClass().getDeclaredFields();
		Field[] fieldsFromSecondClass = b.getClass().getDeclaredFields();
		for (Field currentFieldFromTheFirstClass : fieldsFromFirstClass) {
			for (Field currentFieldFromTheSecondClass : fieldsFromSecondClass) {
				String nameOfTheFirstField = currentFieldFromTheFirstClass.getName();
				String nameOfTheSecondField = currentFieldFromTheSecondClass.getName();
				
				//if (currentFieldFromTheFirstClass.getType().getName() == currentFieldFromTheSecondClass.getType().getName())
				
				if (nameOfTheFirstField.equals(nameOfTheSecondField)) {
					currentFieldFromTheFirstClass.setAccessible(true);
					currentFieldFromTheSecondClass.setAccessible(true);
					currentFieldFromTheFirstClass.set(a, currentFieldFromTheSecondClass.get(b));
				}
			}
		}
		return a;
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
