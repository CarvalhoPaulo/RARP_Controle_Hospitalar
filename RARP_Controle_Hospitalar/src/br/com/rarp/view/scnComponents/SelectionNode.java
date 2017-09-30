package br.com.rarp.view.scnComponents;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class SelectionNode<T extends Node> extends FlowPane {
	
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	
	private boolean editable = true;
	private ObservableList<T> items = FXCollections.observableArrayList();
	private SingleSelectionModel<T> selectionModel = new SingleSelectionModel<T>() {
		@Override
		protected int getItemCount() {
			return items.size();
		}

		@Override
		protected T getModelItem(int index) {
			return (T) items.get(index);
		}
	};
	
	public SelectionNode() {
		super();
		getStylesheets().add(getClass().getResource("selection.css").toExternalForm());
		setWidth(532);
		setHeight(400);

		items.addListener(new ListChangeListener<T>() {
			@Override
			public void onChanged(Change<? extends T> c) {
				for (Node node : c.getList()) {
					node.setOnMouseClicked(onClick);
					if(!node.getStyleClass().contains("node.getStyleClass()"))
						node.getStyleClass().add("draggable");
					getChildren().setAll(items);
				}
			}
		});
	}
	
	public void removeSelected() {
		items.remove(selectionModel.getSelectedItem());
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	EventHandler<MouseEvent> onClick = (event) -> {
		if(editable)
			for(Node n: items) {
				if(n.equals(event.getSource())) {
					selectionModel.select(items.indexOf(n));
					n.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
				} else {
					n.getStyleClass().removeAll("selected");
					n.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
				}
			}
	};

	public ObservableList<T> getItems() {
		return items;
	}

	public void setItems(ObservableList<T> items) {
		this.items = items;
	}

	public SingleSelectionModel<T> getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SingleSelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	public T getValue() {
		return getItems().get(selectionModel.getSelectedIndex());
	}

}
