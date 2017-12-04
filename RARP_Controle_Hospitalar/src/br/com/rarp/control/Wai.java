package br.com.rarp.control;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class Wai {
	Alert alert = new Alert(null);
	
	private boolean  fechar;

	public Wai() {
		// TODO Auto-generated constructor stub
		alert.setTitle("Procurando");
        alert.setHeaderText("Aguarde");
        

       
	
	}
	
	public void abrir() {
		alert.show();
	}
	public void  fechar() {
		fechar = true;
		if (alert != null)
			alert.close();
		
		alert = null;
	}
}
