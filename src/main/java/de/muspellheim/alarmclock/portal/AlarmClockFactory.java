/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import javafx.fxml.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class AlarmClockFactory {

    public AlarmClockController create() {
        URL url = getClass().getResource("/de/muspellheim/alarmclock/portal/AlarmClock.fxml");
        ResourceBundle resources = ResourceBundle.getBundle("de.muspellheim.alarmclock.portal.AlarmClock");
        FXMLLoader loader = new FXMLLoader(url, resources);
        try {
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new IllegalStateException("unreachable code", e);
        }
    }

}
