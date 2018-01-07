/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.head;

import de.muspellheim.alarmclock.body.*;
import de.muspellheim.alarmclock.portal.*;
import de.muspellheim.alarmclock.provider.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage) {
        Watchdog watchdog = new Watchdog();
        Clock clock = new Clock();
        AlarmClockController alarmClock = new AlarmClockFactory().create();
        AlarmBell bell = new JavaFXAlarmBell();

        alarmClock.onStart.addObserver(t -> watchdog.start(t));
        alarmClock.onStop.addObserver(() -> watchdog.stop());

        clock.onCurrentTime.addObserver(t -> {
            watchdog.check(t);
            alarmClock.updateCurrentTime(t);
        });

        watchdog.onRemainingTime.addObserver(t -> alarmClock.updateRemainingTime(t));
        watchdog.onWakeupTimeReached.addObserver(() -> {
            alarmClock.stop();
            bell.ring();
        });

        Scene scene = new Scene(alarmClock.getView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
