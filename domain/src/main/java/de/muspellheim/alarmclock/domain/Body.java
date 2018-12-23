/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import de.muspellheim.alarmclock.provider.*;

import java.time.*;

/**
 * The body integrates all features, but without user interaction.
 */
public class Body {

    private final AlarmBell alarmBell;
    private final Watchdog watchdog;
    private final AlarmClockModel alarmClockModel;

    public Body(AlarmBell alarmBell) {
        this.alarmBell = alarmBell;
        watchdog = new Watchdog();
        alarmClockModel = new AlarmClockModel();

        alarmClockModel.onStart().addHandler(watchdog::startWatchingFor);
        alarmClockModel.onStop().addHandler(watchdog::stopWatching);
    }

    public AlarmClockModel getAlarmClockModel() {
        return alarmClockModel;
    }

    public void currentTimeUpdated(LocalDateTime currentTime) {
        watchdog.check(currentTime, this::remainingTimeUpdated, this::wakeUpTimeReached);
        alarmClockModel.currentTimeUpdated(currentTime);
    }

    private void remainingTimeUpdated(Duration remainingTime) {
        alarmClockModel.remainingTimeUpdated(remainingTime);
    }

    private void wakeUpTimeReached() {
        alarmClockModel.wakeUpTimeReached();
        alarmBell.ring();
    }

}
