module de.muspellheim.alarmclock.application {
    requires javafx.controls;
    requires de.muspellheim.base;
    requires de.muspellheim.alarmclock.domain;
    requires de.muspellheim.alarmclock.portals;
    requires de.muspellheim.alarmclock.providers.api;

    uses de.muspellheim.alarmclock.provider.AlarmBell;

    opens de.muspellheim.alarmclock.application to javafx.graphics;
}
