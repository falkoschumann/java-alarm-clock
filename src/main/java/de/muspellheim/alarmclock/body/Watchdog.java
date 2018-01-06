/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.body;

import de.muspellheim.util.*;

import java.time.*;

public class Watchdog {

    public final Event<Duration> onRemainingTime = new Event<>();
    public final Action onWakeupTimeReached = new Action();

    private LocalTime wakeupTime;
    private boolean active;

    public void start(LocalTime wakeupTime) {
        this.wakeupTime = wakeupTime;
        active = true;
    }

    public void stop() {
        active = false;
    }

    public void check(LocalDateTime currentTime) {
        if (wakeupTime == null || !active)
            return;

        LocalDate wakeupDate = currentTime.toLocalDate();
        if (currentTime.getHour() > wakeupTime.getHour())
            wakeupDate = wakeupDate.plusDays(1);
        LocalDateTime wakeupDateTime = LocalDateTime.of(wakeupDate, wakeupTime);

        Duration remainingTime = Duration.between(currentTime, wakeupDateTime);
        if (remainingTime.getSeconds() >= 0)
            onRemainingTime.send(remainingTime);
        if (remainingTime.getSeconds() <= 0) {
            active = false;
            onWakeupTimeReached.trigger();
        }
    }

}
