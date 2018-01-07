/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import javafx.fxml.*;

import java.io.*;
import java.net.*;

public class AlarmClockFactory {

    public AlarmClockController create() {
        URL url = getClass().getResource("/de/muspellheim/alarmclock/portal/AlarmClock.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new IllegalStateException("unreachable code", e);
        }
    }

}
