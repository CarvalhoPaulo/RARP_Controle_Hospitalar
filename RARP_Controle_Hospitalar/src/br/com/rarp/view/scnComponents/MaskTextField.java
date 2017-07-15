package br.com.rarp.view.scnComponents;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MaskTextField extends TextField {
	private SimpleBooleanProperty cnpj = new SimpleBooleanProperty();
	private SimpleBooleanProperty cep = new SimpleBooleanProperty();
	private SimpleBooleanProperty email = new SimpleBooleanProperty();
	private SimpleBooleanProperty telefone = new SimpleBooleanProperty();
	private SimpleBooleanProperty cpf = new SimpleBooleanProperty();

	public MaskTextField() {
		super();
		
		cpf.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					email.set(oldValue);
					cep.set(oldValue);
					telefone.set(oldValue);
					cnpj.set(oldValue);
					
			        setOnKeyTyped((KeyEvent event) -> {
			            if(!"0123456789".contains(event.getCharacter())){
			                event.consume();
			            }

			            if(event.getCharacter().trim().length()==0){ // apagando

			                if(getText().length()==4){
			                    setText(getText().substring(0,3));
			                    positionCaret(getText().length());
			                }
			                if(getText().length()==8){
			                    setText(getText().substring(0,7));
			                    positionCaret(getText().length());
			                }
			                if(getText().length()==12){
			                    setText(getText().substring(0,11));
			                    positionCaret(getText().length());
			                }

			            }else{ // escrevendo

			                if(getText().length()==14) event.consume();

			                if(getText().length()==3){
			                    setText(getText()+".");
			                    positionCaret(getText().length());
			                }
			                if(getText().length()==7){
			                    setText(getText()+".");
			                    positionCaret(getText().length());
			                }
			                if(getText().length()==11){
			                    setText(getText()+"-");
			                    positionCaret(getText().length());
			                }

			            }
			        });

			        setOnKeyReleased((KeyEvent evt) -> {

			            if(!getText().matches("\\d.-*")){
			                setText(getText().replaceAll("[^\\d.-]", ""));
			                positionCaret(getText().length());
			            }
			        });
				}
			}
		});

		cnpj.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					cep.set(oldValue);
					telefone.set(oldValue);
					email.set(oldValue);
					cpf.set(oldValue);
					
					setOnKeyTyped((KeyEvent event) -> {
						if ("0123456789".contains(event.getCharacter()) == false) {
							event.consume();
						}

						if (event.getCharacter().trim().length() == 0) { // apagando

							if (getText().length() == 3) {
								setText(getText().substring(0, 2));
								positionCaret(getText().length());
							}
							if (getText().length() == 7) {
								setText(getText().substring(0, 6));
								positionCaret(getText().length());
							}
							if (getText().length() == 11) {
								setText(getText().substring(0, 10));
								positionCaret(getText().length());
							}
							if (getText().length() == 16) {
								setText(getText().substring(0, 15));
								positionCaret(getText().length());
							}

						} else { // escrevendo

							if (getText().length() == 18)
								event.consume();

							if (getText().length() == 2) {
								setText(getText() + ".");
								positionCaret(getText().length());
							}
							if (getText().length() == 6) {
								setText(getText() + ".");
								positionCaret(getText().length());
							}
							if (getText().length() == 10) {
								setText(getText() + "/");
								positionCaret(getText().length());
							}
							if (getText().length() == 15) {
								setText(getText() + "-");
								positionCaret(getText().length());
							}

						}
					});
					setOnKeyReleased((KeyEvent evt) -> {

						if (!getText().matches("\\d./-*")) {
							setText(getText().replaceAll("[^\\d./-]", ""));
							positionCaret(getText().length());
						}
					});
				}

			}

		});

		cep.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					cnpj.set(oldValue);
					telefone.set(oldValue);
					email.set(oldValue);
					cpf.set(oldValue);
					
					setOnKeyTyped((KeyEvent event) -> {
						if ("0123456789".contains(event.getCharacter()) == false) {
							event.consume();
						}

						if (event.getCharacter().trim().length() == 0) { // apagando

							if (getText().length() == 6) {
								setText(getText().substring(0, 5));
								positionCaret(getText().length());
							}

						} else { // escrevendo

							if (getText().length() == 9)
								event.consume();

							if (getText().length() == 5) {
								setText(getText() + "-");
								positionCaret(getText().length());
							}

						}
					});
					setOnKeyReleased((KeyEvent evt) -> {

						if (!getText().matches("\\d-*")) {
							setText(getText().replaceAll("[^\\d-]", ""));
							positionCaret(getText().length());
						}
					});
				}
			}

		});

		telefone.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					cnpj.set(oldValue);
					cep.set(oldValue);
					email.set(oldValue);
					cpf.set(oldValue);
					
					setOnKeyTyped((KeyEvent event) -> {
						if ("0123456789".contains(event.getCharacter()) == false) {
							event.consume();
						}

						if (event.getCharacter().trim().length() == 0) { // apagando

							if (getText().length() == 10 && getText().substring(9, 10).equals("-")) {
								setText(getText().substring(0, 9));
								positionCaret(getText().length());
							}
							if (getText().length() == 9 && getText().substring(8, 9).equals("-")) {
								setText(getText().substring(0, 8));
								positionCaret(getText().length());
							}
							if (getText().length() == 4) {
								setText(getText().substring(0, 3));
								positionCaret(getText().length());
							}
							if (getText().length() == 1) {
								setText("");
							}

						} else { // escrevendo

							if (getText().length() == 14)
								event.consume();

							if (getText().length() == 0) {
								setText("(" + event.getCharacter());
								positionCaret(getText().length());
								event.consume();
							}
							if (getText().length() == 3) {
								setText(getText() + ")" + event.getCharacter());
								positionCaret(getText().length());
								event.consume();
							}
							if (getText().length() == 8) {
								setText(getText() + "-" + event.getCharacter());
								positionCaret(getText().length());
								event.consume();
							}
							if (getText().length() == 9 && getText().substring(8, 9) != "-") {
								setText(getText() + "-" + event.getCharacter());
								positionCaret(getText().length());
								event.consume();
							}
							if (getText().length() == 13 && getText().substring(8, 9).equals("-")) {
								setText(getText().substring(0, 8) + getText().substring(9, 10) + "-"
										+ getText().substring(10, 13) + event.getCharacter());
								positionCaret(getText().length());
								event.consume();
							}

						}

					});
					setOnKeyReleased((KeyEvent evt) -> {

						if (!getText().matches("\\d()-*")) {
							setText(getText().replaceAll("[^\\d()-]", ""));
							positionCaret(getText().length());
						}
					});
				}
			}

		});

		email.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					cnpj.set(oldValue);
					cep.set(oldValue);
					telefone.set(oldValue);
					cpf.set(oldValue);
					
					setOnKeyTyped((KeyEvent event) -> {
						if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz._-@"
								.contains(event.getCharacter()) == false) {
							event.consume();
						}

						if ("@".equals(event.getCharacter()) && getText().contains("@")) {
							event.consume();
						}

						if ("@".equals(event.getCharacter()) && getText().length() == 0) {
							event.consume();
						}
					});
				}
			}

		});
	}

	public boolean getCnpj() {
		return cnpj.get();
	}

	public void setCnpj(boolean cnpj) {
		this.cnpj.set(cnpj);
	}

	public boolean getCep() {
		return cep.get();
	}

	public void setCep(boolean cep) {
		this.cep.set(cep);
	}

	public boolean getEmail() {
		return email.get();
	}

	public void setEmail(boolean email) {
		this.email.set(email);
	}

	public boolean getTelefone() {
		return telefone.get();
	}

	public void setTelefone(boolean telefone) {
		this.telefone.set(telefone);
	}
	
	public String getValue() {
		String value = getText();
		if(cnpj.get() || telefone.get() || cep.get()) {
			value = value.replaceAll("[\\D]", "");
		} 
		return value;
	}
	
	public void setValue(String value) {
		setText(value);
	}

	public boolean getCpf() {
		return cpf.get();
	}

	public void setCpf(boolean cpf) {
		this.cpf.set(cpf);
	}

}
