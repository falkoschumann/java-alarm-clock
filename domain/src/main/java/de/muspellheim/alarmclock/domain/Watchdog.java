/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import java.time.*;
import java.util.function.*;

/**
 * If active, the watchdog check if wake up time reached.
 */
public class Watchdog {

    private LocalDateTime wakeUpTime;
    private boolean watching;

    public void startWatchingFor(LocalDateTime wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
        watching = true;
    }

    public void stopWatching() {
        watching = false;
    }

    public void check(LocalDateTime currentTime, Consumer<Duration> onRemainingTime, Runnable onWakeUpTimeDiscovered) {
        if (!watching) {
            return;
        }

        var remainingTime = Duration.between(currentTime, wakeUpTime);
        if (remainingTime.isNegative() || remainingTime.isZero()) {
            watching = false;
            onRemainingTime.accept(Duration.ZERO);
            onWakeUpTimeDiscovered.run();
        } else {
            onRemainingTime.accept(remainingTime);
        }
    }

}
