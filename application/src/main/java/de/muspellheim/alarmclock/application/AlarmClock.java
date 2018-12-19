/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import de.muspellheim.alarmclock.domain.*;
import de.muspellheim.alarmclock.portal.Clock;
import de.muspellheim.alarmclock.portal.*;
import de.muspellheim.alarmclock.provider.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import java.time.*;
import java.util.*;

public class AlarmClock extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // TODO Extract head and body

        Watchdog watchdog = new Watchdog();
        Clock clock = new Clock();
        AlarmClockDialogController ui = AlarmClockDialogController.load();
        AlarmBell alarmBell = ServiceLoader.load(AlarmBell.class).findFirst().get();

        clock.onCurrentTime().addHandler(t -> {
            ui.updateCurrentTime(t);
            watchdog.check(t);
        });
        watchdog.onRemainingTime().addHandler(ui::updateRemainingTime);
        watchdog.onWakeUpTimeDiscovered().addHandler(() -> {
            ui.wakeUpTimeReached();
            alarmBell.ring();
        });
        ui.onStartRequested().addHandler(t -> {
            LocalDateTime dt = LocalDateTime.of(LocalDate.now(), t);
            watchdog.startWatchingFor(dt);
        });
        ui.onStopRequested().addHandler(watchdog::stopWatching);

        primaryStage.setTitle("Alarm Clock");
        primaryStage.setScene(new Scene(ui.getView()));
        primaryStage.show();
    }

}
