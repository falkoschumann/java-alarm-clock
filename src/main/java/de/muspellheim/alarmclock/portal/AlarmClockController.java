/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AlarmClockController {

    @FXML
    private Pane view;

    @FXML
    private Label currentTime;

    @FXML
    private Label remainingTime;

    @FXML
    private TextField wakeupTime;

    @FXML
    private ToggleButton onOff;

    public Pane getView() {
        return view;
    }

    @FXML
    private void toggleOnOff() {
        if (onOff.isSelected()) {
            onOff.setStyle("-fx-base: '#ff4141'");
            remainingTime.setVisible(true);
        } else {
            onOff.setStyle(null);
            remainingTime.setVisible(false);
        }
    }

}
