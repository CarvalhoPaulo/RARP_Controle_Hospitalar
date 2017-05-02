package br.com.rarp.model;

import java.util.Date;

public class Agendamento extends Movimentacao {

	private Date dtAgendamento;
	private Date hrInicio;
	private Date hrFim;
	
	public Date getDtAgendamento() {
		return dtAgendamento;
	}
	public void setDtAgendamento(Date dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}
	public Date getHrFim() {
		return hrFim;
	}
	public void setHrFim(Date hrFim) {
		this.hrFim = hrFim;
	}
	public Date getHrInicio() {
		return hrInicio;
	}
	public void setHrInicio(Date hrInicio) {
		this.hrInicio = hrInicio;
	}
	

}
