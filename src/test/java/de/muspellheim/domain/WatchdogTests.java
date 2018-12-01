/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.domain;

import org.junit.*;

import java.time.*;
import java.util.concurrent.atomic.*;

import static org.junit.Assert.*;

public class WatchdogTests {

    @Test
    public void nothingDiscovered() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        sut.onRemainingTime().addHandler(remainingTime::set);

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 9, 55, 35));

        assertEquals("remainingTime", Duration.ofMinutes(4).plusSeconds(25), remainingTime.get());
    }

    @Test
    public void wakeUpTimeDiscovered() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);
        sut.onRemainingTime().addHandler(remainingTime::set);
        sut.onWakeUpTimeDiscovered().addHandler(() -> wakeUpTimeDiscovered.set(true));

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 0, 0));

        assertEquals("remaining time", Duration.ZERO, remainingTime.get());
        assertTrue("wake up time discovered", wakeUpTimeDiscovered.get());
    }

    @Test
    public void wakeUpTimeDiscoveredEvenIfAlredyPast() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);
        sut.onRemainingTime().addHandler(remainingTime::set);
        sut.onWakeUpTimeDiscovered().addHandler(() -> wakeUpTimeDiscovered.set(true));

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 1, 0));

        assertEquals("remaining time", Duration.ZERO, remainingTime.get());
        assertTrue("wake up time discovered", wakeUpTimeDiscovered.get());
    }

    @Test
    public void noMoreDiscoveriesAfterTheFirstOne() {
        var sut = new Watchdog();
        var remainingTime = new AtomicReference<Duration>();
        var wakeUpTimeDiscovered = new AtomicReference<>(false);
        sut.onRemainingTime().addHandler(remainingTime::set);
        sut.onWakeUpTimeDiscovered().addHandler(() -> wakeUpTimeDiscovered.set(true));

        sut.startWatchingFor(LocalDateTime.of(2015, 1, 1, 10, 0));
        sut.check(LocalDateTime.of(2015, 1, 1, 10, 0, 0));

        remainingTime.set(null);
        wakeUpTimeDiscovered.set(false);

        assertNull("remaining time", remainingTime.get());
        assertFalse("wake up time discovered", wakeUpTimeDiscovered.get());
    }

}
