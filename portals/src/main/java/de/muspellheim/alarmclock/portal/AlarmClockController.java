/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.alarmclock.domain.*;
import de.muspellheim.event.*;
import de.muspellheim.viewcontroller.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class AlarmClockController extends ViewController {

    private final Event<String> onStartRequested = new Event<>();
    private final Action onStopRequested = new Action();

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label remainingTimeLabel;

    @FXML
    private TextField wakeUpTimeTextField;

    @FXML
    private ToggleButton startStopButton;

    public static AlarmClockController load(Body body) {
        // TODO add parameter body
        return load(AlarmClockController.class);
    }

    public void updateCurrentTime(String text) {
        Platform.runLater(() -> currentTimeLabel.setText(text));
    }

    public void updateRemainingTime(String text) {
        Platform.runLater(() -> remainingTimeLabel.setText(text));
    }

    public void wakeUpTimeReached() {
        Platform.runLater(() -> {
            remainingTimeLabel.setVisible(false);
            startStopButton.setSelected(false);
        });
    }

    public Event<String> onStartRequested() {
        return onStartRequested;
    }

    public Action onStopRequested() {
        return onStopRequested;
    }

    @FXML
    private void startStopButtonClicked() {
        if (startStopButton.isSelected()) {
            remainingTimeLabel.setVisible(true);
            remainingTimeLabel.setText("");
            onStartRequested.send(wakeUpTimeTextField.getText());
        } else {
            remainingTimeLabel.setVisible(false);
            onStopRequested.trigger();
        }
    }

}
