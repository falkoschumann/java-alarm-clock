module de.muspellheim.alarmclock.portals {
    requires transitive de.muspellheim.viewcontroller;
    requires transitive de.muspellheim.alarmclock.domain;

    exports de.muspellheim.alarmclock.portal;

    opens de.muspellheim.alarmclock.portal to javafx.fxml, de.muspellheim.viewcontroller;
}
