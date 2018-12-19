/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import de.muspellheim.alarmclock.portal.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 * The head integrates the {@link Body} with input from and output to the user.
 */
class Head {

    private final Body body;
    private final Stage primaryStage;
    private final AlarmClockController alarmClockController;
    private final Clock clock;

    Head(Body body, Stage primaryStage) {
        this.body = body;
        this.primaryStage = primaryStage;
        alarmClockController = AlarmClockController.load();
        clock = new Clock();

        bind();
    }

    private void bind() {
        clock.onCurrentTime().addHandler(body::currentTimeUpdated);
        alarmClockController.onStartRequested().addHandler(body::start);
        alarmClockController.onStopRequested().addHandler(body::stop);
        body.onCurrentTime().addHandler(alarmClockController::updateCurrentTime);
        body.onRemainingTime().addHandler(alarmClockController::updateRemainingTime);
        body.onWakeUpTimeReached().addHandler(alarmClockController::wakeUpTimeReached);
    }

    void run() {
        primaryStage.setTitle("Alarm Clock");
        primaryStage.setScene(new Scene(alarmClockController.getView()));
        primaryStage.show();
    }

}
