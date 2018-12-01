/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.application;

import de.muspellheim.alarmclock.portal.*;
import de.muspellheim.fxml.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class AlarmClock extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AlarmClockDialogController controller = ViewControllerFactory.create(AlarmClockDialogController.class);

        primaryStage.setTitle("Alarm Clock");
        primaryStage.setScene(new Scene(controller.getView()));
        primaryStage.show();
    }

}
