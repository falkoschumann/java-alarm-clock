/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import de.muspellheim.alarmclock.provider.*;
import de.muspellheim.event.*;

import java.time.*;
import java.time.format.*;

/**
 * The body integrates all features, but without user interaction.
 */
public class Body {

    // TODO Cucumber tests for domain design, see readme file

    private final Event<String> onCurrentTime = new Event<>();
    private final Event<String> onRemainingTime = new Event<>();
    private final Action onWakeUpTimeReached = new Action();

    private final AlarmBell alarmBell;
    private final Watchdog watchdog;

    public Body(AlarmBell alarmBell) {
        this.alarmBell = alarmBell;
        watchdog = new Watchdog();
    }

    public void start(String wakeUpTime) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        var time = LocalTime.parse(wakeUpTime, formatter);
        var dateTime = LocalDateTime.of(LocalDate.now(), time);
        watchdog.startWatchingFor(dateTime);
    }

    public void stop() {
        watchdog.stopWatching();
    }

    public void currentTimeUpdated(LocalDateTime currentTime) {
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

    public Event<String> onCurrentTime() {
        return onCurrentTime;
    }

    public Event<String> onRemainingTime() {
        return onRemainingTime;
    }

    public Action onWakeUpTimeReached() {
        return onWakeUpTimeReached;
    }

}
