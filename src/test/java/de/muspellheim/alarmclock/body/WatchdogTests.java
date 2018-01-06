/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.body;

import org.junit.*;

import java.time.*;

import static org.junit.Assert.*;

public class WatchdogTests {

    private Watchdog watchdog;

    private Duration remainingTime;

    private boolean wakeupTimeReached;

    @Before
    public void setUp() {
        watchdog = new Watchdog();
        watchdog.onRemainingTime.addObserver(t -> remainingTime = t);
        watchdog.onWakeupTimeReached.addObserver(() -> wakeupTimeReached = true);
    }

    @Test
    public void calculateRemainingTime() {
        watchdog.start(LocalDateTime.of(2018, 1, 6, 18, 30));
        watchdog.check(LocalDateTime.of(2018, 1, 6, 18, 13, 27));
        assertEquals(Duration.ofSeconds(16 * 60 + 33), remainingTime);
    }

    @Test
    public void checkOnlyIfActive() {
        watchdog.check(LocalDateTime.now());
        assertNull("Not started", remainingTime);

        watchdog.start(LocalDateTime.now());
        watchdog.check(LocalDateTime.now());
        assertNotNull("Started", remainingTime);

        remainingTime = null;
        watchdog.stop();
        watchdog.check(LocalDateTime.now());
        assertNull("Stopped", remainingTime);
    }

    @Test
    public void checkIfWakeupTimeIsReached() {
        watchdog.start(LocalDateTime.of(2018, 1, 6, 18, 30));

        watchdog.check(LocalDateTime.of(2018, 1, 6, 18, 13, 27));
        assertEquals(Duration.ofSeconds(16 * 60 + 33), remainingTime);
        assertFalse("Wakeup time not reached", wakeupTimeReached);

        watchdog.check(LocalDateTime.of(2018, 1, 6, 18, 30, 0));
        assertEquals(Duration.ofSeconds(0), remainingTime);
        assertTrue("Wakeup time reached", wakeupTimeReached);

        remainingTime = null;
        wakeupTimeReached = false;
        watchdog.check(LocalDateTime.of(2018, 1, 6, 19, 0, 0));
        assertNull("Stopped", remainingTime);
        assertFalse("Stopped", wakeupTimeReached);
    }

    @Test
    public void checkWakeupTimeAtNextDay() {
    }

    @Test
    public void checkWakeupTimeEarlierThisDay() {
    }

}
