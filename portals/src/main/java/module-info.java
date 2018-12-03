module de.muspellheim.alarmclock.portals {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.muspellheim.base;

    exports de.muspellheim.alarmclock.portal;

    opens de.muspellheim.alarmclock.portal to javafx.fxml, de.muspellheim.base;
}
