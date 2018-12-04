module de.muspellheim.alarmclock.providers {
    requires javafx.media;
    requires de.muspellheim.alarmclock.providers.api;

    provides de.muspellheim.alarmclock.provider.AlarmBell with de.muspellheim.alarmclock.provider.impl.AlarmBellImpl;
}
