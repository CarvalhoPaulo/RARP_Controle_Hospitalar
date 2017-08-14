package br.com.rarp.view.scnComponents;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fcsalgado
 * new AutoCompleteComboBoxListener<>(suaCombo);
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class AutoCompleteComboBox<T> extends ComboBox<T> implements EventHandler<KeyEvent> {

	private ObservableList<T> data;
	private boolean moveCaretToPos = false;
	private boolean digited = false;
	private int caretPos;

	public AutoCompleteComboBox() {
		new StringBuilder();
		data = getItems();
		setEditable(true);
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent t) {
				hide();
			}
		});
		setOnKeyReleased(this);

		setOnAction(t -> {
			boolean valido = false;
			if (digited) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).toString().toLowerCase().contains(getEditor().getText().toLowerCase())) {
						valido = true;
					}
					if (valido) {
						setValue(data.get(i));
						break;
					}
				}
				digited = false;
				if (!valido) {
					setItems(data);
					setValue(null);
				}
			}
		});
		setConverter(new StringConverter<T>() {

			@Override
			public String toString(T arg0) {
				if (arg0 == null)
					return null;
				return arg0.toString();
			}

			@Override
			public T fromString(String string) {
				return getSelectionModel().getSelectedItem();
			}
		});

	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			caretPos = -1;
			moveCaret(getEditor().getText().length());
			return;
		} else if (event.getCode() == KeyCode.DOWN) {
			if (!isShowing()) {
				show();
			}
			caretPos = -1;
			moveCaret(getEditor().getText().length());
			return;
		} else if (event.getCode() == KeyCode.BACK_SPACE) {
			moveCaretToPos = true;
			caretPos = getEditor().getCaretPosition();
		} else if (event.getCode() == KeyCode.DELETE) {
			moveCaretToPos = true;
			caretPos = getEditor().getCaretPosition();
		}

		if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || event.isControlDown()
				|| event.getCode() == KeyCode.HOME || event.getCode() == KeyCode.END
				|| event.getCode() == KeyCode.TAB) {
			return;
		}

		ObservableList<T> list = FXCollections.observableArrayList();
		if (data == null || data.size() == 0) {
			data = getItems();
		}
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).toString().toLowerCase()
					.contains(getEditor().getText().toLowerCase())) {
				list.add(data.get(i));
			}
		}
		String t = getEditor().getText();
		if(list.size() <= 0)
			t = t.substring(0, t.length() - 1);

		setItems(list);
		getEditor().setText(t);
		if (!moveCaretToPos) {
			caretPos = -1;
		}
		moveCaret(t.length());
		if (!list.isEmpty()) {
			show();
		}
		digited = true;
	}

	private void moveCaret(int textLength) {
		if (caretPos == -1) {
			getEditor().positionCaret(textLength);
		} else {
			getEditor().positionCaret(caretPos);
		}
		moveCaretToPos = false;
	}

}
