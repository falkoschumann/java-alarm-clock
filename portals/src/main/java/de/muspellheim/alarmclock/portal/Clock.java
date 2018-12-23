/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.event.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The clock offers current time.
 */
public class Clock {

    private final Event<LocalDateTime> onCurrentTime = new Event<>();

    public Clock() {
        var timer = new Timer("Clock", true);
        var task = new ClockTask();
        timer.scheduleAtFixedRate(task, 0, TimeUnit.SECONDS.toMillis(1));
    }

    public Event<LocalDateTime> onCurrentTime() {
        return onCurrentTime;
    }

    private class ClockTask extends TimerTask {

        @Override
        public void run() {
            onCurrentTime.send(LocalDateTime.now());
        }

    }

}
