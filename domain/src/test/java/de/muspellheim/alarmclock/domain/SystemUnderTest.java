/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import de.muspellheim.alarmclock.provider.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Facade for the body and providers in user acceptance tests.
 */
class SystemUnderTest {

    private final Body body;
    private final AlarmBellMock alarmBell;

    private String actualCurrentTime;
    private String actualRemainingTime;

    SystemUnderTest() {
        alarmBell = new AlarmBellMock();
        body = new Body(alarmBell);

        body.onCurrentTimeUpdated().addHandler(s -> actualCurrentTime = s);
        body.onRemainingTimeUpdated().addHandler(s -> actualRemainingTime = s);
    }

    void userSwitchesOnAlarm(String wakeUpTime) {
        body.setAlarmClockFor(wakeUpTime);
    }

    void userSwitchesOffAlarm() {
        body.turnOffAlarmClock();
    }

    void systemClockTicks(String currentTime) {
        body.currentTimeUpdated(LocalDateTime.of(LocalDate.now(), LocalTime.parse(currentTime)));
    }

    void alarmClockDisplaysCurrentTime(String currentTime) {
        assertEquals(currentTime, actualCurrentTime, "Alarm clock displays current time");
    }

    void alarmClockDisplaysRemainingTime(String remainingTime) {
        assertEquals(remainingTime, actualRemainingTime, "Alarm clock displays remaining time");
    }

    void alarmBellHasRung() {
        alarmBell.verifyRung();
    }

    private static class AlarmBellMock implements AlarmBell {

        private boolean rung;

        void verifyRung() {
            assertTrue(rung, "Alarm bell has rung");
        }

        @Override
        public void ring() {
            rung = true;
        }

    }

}
