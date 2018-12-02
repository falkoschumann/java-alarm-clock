module de.muspellheim.alarmclock {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens de.muspellheim.alarmclock.application to javafx.graphics;
    opens de.muspellheim.alarmclock.portal to javafx.fxml;
}
