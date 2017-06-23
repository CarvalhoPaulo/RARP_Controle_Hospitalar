package br.com.rarp.view.scnComponents;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IntegerTextField extends TextField {
	
	public IntegerTextField() {
		this.setOnKeyTyped(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					setText(String.valueOf(Integer.parseInt(event.getText()) + 1));
					break;
				case DOWN:
					setText(String.valueOf(Integer.parseInt(event.getText()) - 1));
					break;
				default:
					try {
						Integer.parseInt(event.getCharacter());
					} catch (NumberFormatException e) {
						if(!event.getCharacter().contains("-"))
							event.consume();
					}
					break;
				}
			}
		});
	}
	
	public Integer getValue() {
		return Integer.parseInt(getText());
	}
	
	public void setValue(Integer value) {
		setText(value.toString());
	}
}
