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

    private final Event<String> onCurrentTimeUpdated = new Event<>();
    private final Event<String> onRemainingTimeUpdated = new Event<>();
    private final Action onWakeUpTimeReached = new Action();

    private final AlarmBell alarmBell;
    private final Watchdog watchdog;

    public Body(AlarmBell alarmBell) {
        this.alarmBell = alarmBell;
        watchdog = new Watchdog();
    }

    public void setAlarmClockFor(String wakeUpTime) {
        LocalDateTime dateTime = parseWakeUpTime(wakeUpTime);
        watchdog.startWatchingFor(dateTime);
    }

    private static LocalDateTime parseWakeUpTime(String text) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        var time = LocalTime.parse(text, formatter);
        return LocalDateTime.of(LocalDate.now(), time);
    }

    public void turnOffAlarmClock() {
        watchdog.stopWatching();
    }

    public void currentTimeUpdated(LocalDateTime currentTime) {
        watchdog.check(currentTime, this::remainingTimeUpdated, this::wakeUpTimeReached);
        String text = currentTimeToString(currentTime);
        onCurrentTimeUpdated.send(text);
    }

    private static String currentTimeToString(LocalDateTime currentTime) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        return currentTime.format(formatter);
    }

    private void remainingTimeUpdated(Duration remainingTime) {
        String text = remainingTimeToString(remainingTime);
        onRemainingTimeUpdated.send(text);
    }

    private static String remainingTimeToString(Duration remainingTime) {
        var time = LocalTime.of(remainingTime.toHoursPart(), remainingTime.toMinutesPart(), remainingTime.toSecondsPart());
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        return time.format(formatter);
    }

    private void wakeUpTimeReached() {
        onWakeUpTimeReached.trigger();
        alarmBell.ring();
    }

    public Event<String> onCurrentTimeUpdated() {
        return onCurrentTimeUpdated;
    }

    public Event<String> onRemainingTimeUpdated() {
        return onRemainingTimeUpdated;
    }

    public Action onWakeUpTimeReached() {
        return onWakeUpTimeReached;
    }

}
