/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.domain;

import de.muspellheim.util.*;

import java.time.*;

public class Watchdog {

    private final Event<Duration> onRemainingTime = new Event<>();
    private final Action onWakeUpTimeDiscovered = new Action();

    private LocalDateTime wakeUpTime;
    private boolean watching;

    public void startWatchingFor(LocalDateTime wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
        watching = true;
    }

    public void stopWatching() {
        watching = false;
    }

    public void check(LocalDateTime currentTime) {
        if (!watching) {
            return;
        }

        var remainingTime = Duration.between(currentTime, wakeUpTime);
        if (remainingTime.isNegative() || remainingTime.isZero()) {
            watching = false;
            onRemainingTime.send(Duration.ZERO);
            onWakeUpTimeDiscovered.trigger();
        } else {
            onRemainingTime.send(remainingTime);
        }
    }

    public Event<Duration> onRemainingTime() {
        return onRemainingTime;
    }

    public Action onWakeUpTimeDiscovered() {
        return onWakeUpTimeDiscovered;
    }

}
