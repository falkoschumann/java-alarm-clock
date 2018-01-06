/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import de.muspellheim.util.*;

import java.time.*;
import java.util.*;

public class Clock {

    public final Event<LocalDateTime> onCurrentTime = new Event<>();

    public Clock() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new Tick(), 0, 1000);
    }

    private class Tick extends TimerTask {

        @Override
        public void run() {
            onCurrentTime.send(LocalDateTime.now());
        }

    }

}
