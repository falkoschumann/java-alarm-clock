/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.alarmclock.domain.*;
import de.muspellheim.viewcontroller.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * The controller interacts with the user and use {@link Body} as presentation model.
 */
public class AlarmClockController extends ViewController {

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label remainingTimeLabel;

    @FXML
    private TextField wakeUpTimeTextField;

    @FXML
    private ToggleButton startStopButton;

    private Body body;

    public static AlarmClockController load(Body body) {
        AlarmClockController controller = ViewControllerFactory.load(AlarmClockController.class);
        controller.body = body;

        body.onCurrentTimeUpdated().addHandler(controller::currentTimeUpdated);
        body.onRemainingTimeUpdated().addHandler(controller::remainingTimeUpdated);
        body.onWakeUpTimeReached().addHandler(controller::wakeUpTimeReached);

        return controller;
    }

    private void currentTimeUpdated(String text) {
        Platform.runLater(() -> currentTimeLabel.setText(text));
    }

    private void remainingTimeUpdated(String text) {
        Platform.runLater(() -> remainingTimeLabel.setText(text));
    }

    private void wakeUpTimeReached() {
        Platform.runLater(() -> {
            remainingTimeLabel.setVisible(false);
            startStopButton.setSelected(false);
        });
    }

    @FXML
    private void startStopButtonClicked() {
        if (startStopButton.isSelected()) {
            remainingTimeLabel.setVisible(true);
            remainingTimeLabel.setText("");
            body.setAlarmClockFor(wakeUpTimeTextField.getText());
        } else {
            remainingTimeLabel.setVisible(false);
            body.turnOffAlarmClock();
        }
    }

}
