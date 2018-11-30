/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.*;

public class AlarmClock extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("/AlarmClockDialog.fxml");
        Pane pane = FXMLLoader.load(url);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Alarm Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
