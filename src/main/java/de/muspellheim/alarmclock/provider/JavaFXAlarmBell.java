/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.provider;

import javafx.scene.media.*;

import java.net.*;

public class JavaFXAlarmBell implements AlarmBell {

    @Override
    public void ring() {
        URL url = getClass().getResource("/de/muspellheim/alarmclock/provider/Alarm.mp3");
        AudioClip clip = new AudioClip(url.toString());
        clip.play();
    }

}
