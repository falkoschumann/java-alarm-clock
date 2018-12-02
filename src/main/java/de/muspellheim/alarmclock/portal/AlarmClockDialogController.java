/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.util.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.*;
import java.time.format.*;

public class AlarmClockDialogController {

    private final Event<LocalTime> onStartRequested = new Event<>();
    private final Action onStopRequested = new Action();

    @FXML
    private Pane view;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label remainingTimeLabel;

    @FXML
    private TextField wakeUpTimeTextField;

    @FXML
    private ToggleButton startStopButton;

    public Parent getView() {
        return view;
    }

    public void updateCurrentTime(LocalDateTime time) {
        String s = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
        currentTimeLabel.setText(s);
    }

    public void updateRemainingTime(Duration time) {
        LocalTime t = LocalTime.of(time.toHoursPart(), time.toMinutesPart(), time.toSecondsPart());
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        String s = t.format(formatter);
        currentTimeLabel.setText(s);
    }

    public void wakeUpTimeReached() {
        remainingTimeLabel.setVisible(false);
        startStopButton.setSelected(false);
    }

    @FXML
    private void startStopButtonClicked() {
        if (startStopButton.isSelected()) {
            remainingTimeLabel.setVisible(true);
            remainingTimeLabel.setText("");

            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            LocalTime time = LocalTime.parse(wakeUpTimeTextField.getText(), formatter);
            onStartRequested.send(time);
        } else {
            remainingTimeLabel.setVisible(false);
            onStopRequested.trigger();
        }
    }

    public Event<LocalTime> onStartRequested() {
        return onStartRequested;
    }

    public Action onStopRequested() {
        return onStopRequested;
    }

}
