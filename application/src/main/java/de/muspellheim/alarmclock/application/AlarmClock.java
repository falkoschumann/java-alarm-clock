/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import de.muspellheim.alarmclock.domain.*;
import de.muspellheim.alarmclock.portal.*;
import de.muspellheim.alarmclock.provider.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import java.util.*;

/**
 * The application aka head integrates the {@link Body}, portals and providers and run the app.
 */
public class AlarmClock extends Application {

    private AlarmClockController alarmClockController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        var alarmBell = createAlarmBell();
        var body = new Body(alarmBell);

        alarmClockController = AlarmClockController.load(body);
        Clock clock = new Clock();
        clock.onCurrentTime().addHandler(body::currentTimeUpdated);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alarm Clock");
        Scene scene = new Scene(alarmClockController.getView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private AlarmBell createAlarmBell() {
        return ServiceLoader.load(AlarmBell.class).findFirst().get();
    }

}
