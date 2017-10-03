package br.com.rarp.view.scnComponents;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class SelectionNode<T extends Node> extends FlowPane {
	
	private boolean editable = true;
	private boolean multipleSelection = false;

	private SingleMultipleSelectionModel<T> selectionModel = new SingleMultipleSelectionModel<>();
	
	public SelectionNode() {
		super();
		setWidth(400);
		setHeight(400);

		selectionModel.getItems().addListener(new ListChangeListener<T>() {
			@Override
			public void onChanged(Change<? extends T> c) {
				getChildren().clear();
				for (Node node : c.getList()) {
					node.setOnMouseClicked(onClick);
					node.setStyle("fx-padding: 14px");
					getChildren().setAll(selectionModel.getItems());
				}
			}
		});
	}
	
	public void removeSelected() {
		selectionModel.getItems().remove(selectionModel.getSelectedItem());
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	EventHandler<MouseEvent> onClick = (event) -> {
		if(editable) {
			if(selectionModel.getItems().contains(event.getSource())) {
				if(!multipleSelection)
					selectionModel.clearSelection();
				if(!selectionModel.getSelectedItems().contains(event.getSource()))
					selectionModel.select(selectionModel.getItems().indexOf(event.getSource()));
				else
					selectionModel.clearSelection(selectionModel.getItems().indexOf(event.getSource()));
			}
			upgradeSelection();
		}
	};
	
	private void upgradeSelection() {
		for(T t: selectionModel.getItems()) {
			if (t != null) {
				if (selectionModel.getSelectedItems().contains(t))
					t.setStyle("fx-padding: 14px;-fx-effect: dropshadow(three-pass-box, rgba(0,154,255,100), 10, 0, 0, 0)");
				else
					t.setStyle("fx-padding: 14px");
			}
		}
	}

	public SingleMultipleSelectionModel<T> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleMultipleSelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}

	public ObservableList<T> getItems() {
		return selectionModel.getItems();
	}

	public void setItems(ObservableList<T> items) {
		this.selectionModel.setItems(items);
	}
	
	public T getValue() {
		return selectionModel.getSelectedItem();
	}

	public boolean isMultipleSelection() {
		return multipleSelection;
	}

	public void setMultipleSelection(boolean multipleSelection) {
		this.multipleSelection = multipleSelection;
		selectionModel.setSelectionMode(multipleSelection ? SelectionMode.MULTIPLE : SelectionMode.SINGLE);
	}

}
