/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.util.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.*;
import java.time.format.*;

public class AlarmClockController {

    public final Event<LocalTime> onStart = new Event<>();

    public final Action onStop = new Action();

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
    private void validateWakeupTime() {
        try {
            LocalTime.parse(wakeupTime.getText());
            onOff.setDisable(false);
        } catch (DateTimeParseException e) {
            onOff.setDisable(true);
        }
    }

    @FXML
    private void toggleOnOff() {
        if (onOff.isSelected())
            start();
        else
            stop();
    }

    public void updateCurrentTime(LocalDateTime time) {
        Platform.runLater(() -> currentTime.setText(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))));
    }

    public void updateRemainingTime(Duration duration) {
        LocalTime time = LocalTime.ofSecondOfDay(duration.getSeconds());
        Platform.runLater(() -> remainingTime.setText(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))));
    }

    public void start() {
        remainingTime.setVisible(true);
        wakeupTime.setDisable(true);
        onOff.setSelected(true);
        onOff.setStyle("-fx-base: '#ff4141'");

        onStart.send(LocalTime.parse(wakeupTime.getText()));
    }

    public void stop() {
        remainingTime.setVisible(false);
        wakeupTime.setDisable(false);
        onOff.setSelected(false);
        onOff.setStyle(null);

        onStop.trigger();
    }

}
