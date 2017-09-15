package br.com.rarp.view.scnComponents;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
	private boolean literal = true;
	private boolean negativeIndex = true;
	private boolean hideOutside = true;
	private boolean space = false;
	
	public boolean isNegativeIndex() {
		return negativeIndex;
	}

	public void setNegativeIndex(boolean negativeIndex) {
		this.negativeIndex = negativeIndex;
	}

	public AutoCompleteComboBox() {
		new StringBuilder();
		data = getItems();
		setEditable(true);
		addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			space = event.getCode() == KeyCode.SPACE;
			if(event.getCode() == KeyCode.ENTER)
				hide();
		});
		setOnHidden(onHide);
		
		focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(!newValue && oldValue && literal && getItems().size() > 0) {
				getEditor().setText(getItems().get(0).toString());
				if(!negativeIndex && getItems() != null && getItems().size() > 0 && getEditor().getText().isEmpty())
					getSelectionModel().selectFirst();
			}
			
    	    Platform.runLater(() -> {
    	        if ((getEditor().isFocused() || isFocused()) && !getEditor().getText().isEmpty() && !space) {
    	            getEditor().selectAll();
    	            hide();
    	        }
    	    });
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
	
	public EventHandler<Event> onHide = new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			Platform.runLater(() -> {
		        if ((getEditor().isFocused() || isFocused()) && !getEditor().getText().isEmpty() && !space) {
		            getEditor().selectAll();
		        }
		    });
		}
	};

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			if(!hideOutside)
				caretPos = -1;
			moveCaret(getEditor().getText().length());
			return;
		} else if (event.getCode() == KeyCode.DOWN) {
			if (!isShowing()) {
				show();
			}
			if(!hideOutside)
				caretPos = -1;;
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
		ObservableList<T> hideList = FXCollections.observableArrayList();
		if (data == null || data.size() == 0) {
			data = getItems();
		}
		
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).toString().toLowerCase()
					.contains(getEditor().getText().toLowerCase())) {
				list.add(data.get(i));
			} else {
				hideList.add(data.get(i));
			}
		}
		
		String t = getEditor().getText();
		if(list.size() <= 0 && literal && t.length() > 0) {
			t = t.substring(0, t.length() - 1);
			getEditor().setText(t);
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).toString().toLowerCase()
						.contains(getEditor().getText().toLowerCase())) {
					list.add(data.get(i));
				}
			}
		}
		
		if(!hideOutside)
			list.addAll(hideList);
		
		if(list.size() > 0)
			getSelectionModel().select(list.get(0));

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

	public boolean isLiteral() {
		return literal;
	}

	public void setLiteral(boolean literal) {
		this.literal = literal;
	}

	public boolean isHideOutside() {
		return hideOutside;
	}

	public void setHideOutside(boolean hideOutside) {
		this.hideOutside = hideOutside;
	}

}
