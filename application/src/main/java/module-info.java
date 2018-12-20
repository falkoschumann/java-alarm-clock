module de.muspellheim.alarmclock.application {
    requires de.muspellheim.alarmclock.portals;

    uses de.muspellheim.alarmclock.provider.AlarmBell;

    opens de.muspellheim.alarmclock.application to javafx.graphics;
}
