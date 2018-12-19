/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import de.muspellheim.alarmclock.domain.*;
import de.muspellheim.alarmclock.provider.*;
import de.muspellheim.util.*;

import java.time.*;
import java.time.format.*;

/**
 * The body integrates all features, but without user interaction.
 */
class Body {

    // TODO Cucumber tests

    private final Event<String> onCurrentTime = new Event<>();
    private final Event<String> onRemainingTime = new Event<>();
    private final Action onWakeUpTimeReached = new Action();

    private final AlarmBell alarmBell;
    private final Watchdog watchdog;

    Body(AlarmBell alarmBell) {
        this.alarmBell = alarmBell;
        watchdog = new Watchdog();
    }

    void start(String wakeUpTime) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        var time = LocalTime.parse(wakeUpTime, formatter);
        var dateTime = LocalDateTime.of(LocalDate.now(), time);
        watchdog.startWatchingFor(dateTime);
    }

    void stop() {
        watchdog.stopWatching();
    }

    void currentTimeUpdated(LocalDateTime currentTime) {
        watchdog.check(currentTime, this::remainingTimeUpdated, this::wakeUpTimeReached);

        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        onCurrentTime.send(currentTime.format(formatter));
    }

    private void remainingTimeUpdated(Duration remainingTime) {
        var time = LocalTime.of(remainingTime.toHoursPart(), remainingTime.toMinutesPart(), remainingTime.toSecondsPart());
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        var text = time.format(formatter);
        onRemainingTime.send(text);
    }

    private void wakeUpTimeReached() {
        onWakeUpTimeReached.trigger();
        alarmBell.ring();
    }

    Event<String> onCurrentTime() {
        return onCurrentTime;
    }

    Event<String> onRemainingTime() {
        return onRemainingTime;
    }

    Action onWakeUpTimeReached() {
        return onWakeUpTimeReached;
    }

}
