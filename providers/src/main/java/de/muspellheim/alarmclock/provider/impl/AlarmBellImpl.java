/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.provider.impl;

import de.muspellheim.alarmclock.provider.*;
import javafx.scene.media.*;

import java.net.*;

public class AlarmBellImpl implements AlarmBell {

    @Override
    public void ring() {
        URL url = getClass().getResource("Alarm.mp3");
        var media = new Media(url.toString());
        var player = new MediaPlayer(media);
        player.play();
    }

}
