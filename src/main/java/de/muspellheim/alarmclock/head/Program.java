/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.alarmclock.head;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Hello World!");
        Scene scene = new Scene(label, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
