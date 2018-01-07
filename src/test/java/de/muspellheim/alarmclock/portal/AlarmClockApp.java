/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.portal;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AlarmClockApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        AlarmClockController controller = new AlarmClockFactory().create();
        Scene scene = new Scene(controller.getView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
