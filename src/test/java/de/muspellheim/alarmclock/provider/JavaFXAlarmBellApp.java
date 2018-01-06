/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.provider;

public class JavaFXAlarmBellApp {

    public static void main(String[] args) throws Exception {
        JavaFXAlarmBell bell = new JavaFXAlarmBell();
        bell.ring();

        Thread.sleep(3000);
    }

}
