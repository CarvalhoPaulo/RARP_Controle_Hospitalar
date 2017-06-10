package br.com.rarp.view.scnComponents;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButton extends Label
{
    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(true);

    public SwitchButton()
    {
        Button switchBtn = new Button();
        switchBtn.setPrefWidth(35);
        switchBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                switchedOn.set(!switchedOn.get());
            }
        });

        setGraphic(switchBtn);

        switchedOn.addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                Boolean t, Boolean t1)
            {
                if (t1)
                {
                    setText("SIM");
                    setStyle( "-fx-background-color: green;"
                    		+ "-fx-text-fill: white;"
                    		+ "-fx-background-radius: 4px;"
                    		+ "-fx-font-weight: bold;"
                    		+ "-fx-label-padding: 2px 3px 3px 3px;");
                    setContentDisplay(ContentDisplay.RIGHT);
                }
                else
                {
                    setText("NÃO");
                    setStyle( "-fx-background-color: red;"
                    		+ "-fx-text-fill: white;"
                    		+ "-fx-background-radius: 4px;"
                    		+ "-fx-font-weight: bold;"
                    		+ "-fx-label-padding: 2px 3px 3px 2px;");
                    setContentDisplay(ContentDisplay.LEFT);
                }
            }
        });

        switchedOn.set(false);
    }

    public SimpleBooleanProperty switchOnProperty() { 
    	return switchedOn; 
    }
}
