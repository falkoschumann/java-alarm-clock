/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import java.util.concurrent.*;

public class ClockApp {

    public static void main(String[] args) throws Exception {
        Clock clock = new Clock();
        clock.onCurrentTime.addObserver(System.out::println);

        TimeUnit.SECONDS.sleep(10);
    }

}
