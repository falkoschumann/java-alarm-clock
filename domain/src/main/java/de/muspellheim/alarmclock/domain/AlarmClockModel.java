/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import de.muspellheim.event.*;

import java.time.*;
import java.time.format.*;

public class AlarmClockModel {

    private final Event<LocalDateTime> onStart = new Event<>();
    private final Action onStop = new Action();
    private final Event<String> onCurrentTime = new Event<>();
    private final Event<String> onRemainingTime = new Event<>();
    private final Action onWakeUpTimeReached = new Action();

    public void start(String wakeUpTime) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        var time = LocalTime.parse(wakeUpTime, formatter);
        var dateTime = LocalDateTime.of(LocalDate.now(), time);
        onStart.send(dateTime);
    }

    public void stop() {
        onStop.trigger();
    }

    void currentTimeUpdated(LocalDateTime currentTime) {
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        onCurrentTime.send(currentTime.format(formatter));
    }

    void remainingTimeUpdated(Duration remainingTime) {
        var time = LocalTime.of(remainingTime.toHoursPart(), remainingTime.toMinutesPart(), remainingTime.toSecondsPart());
        var formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        var text = time.format(formatter);
        onRemainingTime.send(text);
    }

    void wakeUpTimeReached() {
        onWakeUpTimeReached.trigger();
    }

    Event<LocalDateTime> onStart() {
        return onStart;
    }

    Action onStop() {
        return onStop;
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
