/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import org.junit.jupiter.api.*;

import java.time.*;
import java.util.concurrent.atomic.*;

import static org.junit.jupiter.api.Assertions.*;

public class WatchdogTests {

    @Test
    public void nothingDiscovered() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 9, 55, 35), remainingTime::set, () -> {
        });

        assertEquals(Duration.ofMinutes(4).plusSeconds(25), remainingTime.get(), "remainingTime");
    }

    @Test
    public void wakeUpTimeDiscovered() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 0, 0), remainingTime::set, () -> wakeUpTimeDiscovered.set(true));

        assertEquals(Duration.ZERO, remainingTime.get(), "remaining time");
        assertTrue(wakeUpTimeDiscovered.get(), "wake up time discovered");
    }

    @Test
    public void wakeUpTimeDiscoveredEvenIfAlredyPast() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 1, 0), remainingTime::set, () -> wakeUpTimeDiscovered.set(true));

        assertEquals(Duration.ZERO, remainingTime.get(), "remaining time");
        assertTrue(wakeUpTimeDiscovered.get(), "wake up time discovered");
    }

    @Test
    public void noMoreDiscoveriesAfterTheFirstOne() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 0, 0), remainingTime::set, () -> wakeUpTimeDiscovered.set(true));

        remainingTime.set(null);
        wakeUpTimeDiscovered.set(false);

        assertNull(remainingTime.get(), "remaining time");
        assertFalse(wakeUpTimeDiscovered.get(), "wake up time discovered");
    }

}
