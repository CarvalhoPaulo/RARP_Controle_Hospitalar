package br.com.rarp.view.scnComponents;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AutoCompleteComboBox<T> extends ComboBox<T> {
	String filter = "";
	private ObservableList<T> originalItems = FXCollections.emptyObservableList();
	Node self = this;

	public AutoCompleteComboBox() {
		setTooltip(new Tooltip());
		itemsProperty().addListener(changeListener);
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				try {
					itemsProperty().removeListener(changeListener);
					ObservableList<T> filteredList = FXCollections.observableArrayList();
					KeyCode code = event.getCode();

					if (code.isLetterKey()) 
						filter += event.getText();
					
					if (code == KeyCode.BACK_SPACE && filter.length() > 0)
						filter = filter.substring(0, filter.length()-1);
					
					if (code == KeyCode.ESCAPE) 
						filter = "";
					
					getTooltip().setText(filter);
					if(filter.isEmpty())
						getTooltip().hide();
					else
						getTooltip().show(self, 0, 0);
					
					if (filter.length() == 0) {
						filteredList = originalItems;
					} else {
						String txtUsr = filter.toString().toLowerCase();
						filteredList.filtered(t -> t.toString().substring(0, txtUsr.length()).toLowerCase().contains(txtUsr));
					}
					setItems(filteredList);
					getSelectionModel().select(0);
				} catch (Exception e) {
					getItems().setAll(originalItems);
				} finally {
					itemsProperty().addListener(changeListener);
				}
			}
		});
	}
	
	ChangeListener<ObservableList<T>> changeListener = new ChangeListener<ObservableList<T>>() {

		@Override
		public void changed(ObservableValue<? extends ObservableList<T>> observable, ObservableList<T> oldValue,
				ObservableList<T> newValue) {
			originalItems = newValue;
		}
	};

}
