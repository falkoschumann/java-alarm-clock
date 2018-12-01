/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.provider;

import javafx.scene.media.*;

import java.net.*;

public class AlarmBell {

    public void ring() {
        URL url = getClass().getResource("Alarm.mp3");
        var media = new Media(url.toString());
        var player = new MediaPlayer(media);
        player.play();
    }

}
