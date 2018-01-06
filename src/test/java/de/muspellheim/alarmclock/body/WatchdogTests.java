/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.body;

import org.junit.*;

import java.time.*;

import static org.junit.Assert.*;

public class WatchdogTests {

    private Duration remainingTime;

    private void setRemainingTime(Duration value) {
        remainingTime = value;
    }

    @Test
    public void calculateRemainingTime() {
        Watchdog watchdog = new Watchdog();
        watchdog.onRemainingTime.addObserver(this::setRemainingTime);

        watchdog.start(LocalDateTime.of(2018, 1, 6, 18, 30));
        watchdog.check(LocalDateTime.of(2018, 1, 6, 18, 13, 27));
        assertEquals(Duration.ofSeconds(16 * 60 + 33), remainingTime);
    }

    @Test
    public void checkOnlyIfActive() {
    }

    @Test
    public void checkIfWakeupTimeIsReached() {
    }

    @Test
    public void checkWakeupTimeAtNextDay() {
    }

    @Test
    public void checkWakeupTimeEarlierThisDay() {
    }

}
