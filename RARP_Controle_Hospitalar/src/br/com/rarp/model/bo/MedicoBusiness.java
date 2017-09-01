package br.com.rarp.model.bo;

import java.util.List;

import br.com.rarp.model.Especialidade;
import br.com.rarp.model.Medico;
import br.com.rarp.model.Usuario;
import br.com.rarp.model.dao.EspecialidadeDAO;
import br.com.rarp.model.dao.MedicoDAO;

public class MedicoBusiness {

	public void salvar(Medico medico) throws Exception {
		MedicoDAO medicoDAO = new MedicoDAO();
		validaMedico(medico);
		medicoDAO.salvar(medico);
	}

	public List<Medico> consultar(String campo, String comparacao, String termo) throws Exception {
		// TODO Auto-generated method stub
		MedicoDAO medicoDAO = new MedicoDAO();
		return medicoDAO.consultar(campo, comparacao, termo);

	}

	private void validaMedico(Medico medico) throws Exception {
		if (medico.getCodigoMedico() == 0 )
			if (!new MedicoDAO().consultar("MED.codigo_funcionario ", " = ", String.valueOf(medico.getCodigo())).isEmpty()) {
				throw new Exception("Fucionario ja Relaciona a um medico");
			}
	}

}
