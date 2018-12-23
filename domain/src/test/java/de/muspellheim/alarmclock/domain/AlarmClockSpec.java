/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.alarmclock.domain;

import org.junit.jupiter.api.*;

/**
 * User acceptance test for the app Alarm Clock.
 */
public class AlarmClockSpec {

    private SystemUnderTest sut;

    @BeforeEach
    public void setUp() {
        sut = new SystemUnderTest();
    }

    @Test
    public void scenarioClockTicksAndAlarmIsOff() {
        // Given
        sut.userSwitchesOffAlarm();

        // When
        sut.systemClockTicks("15:56:32");

        // Then
        sut.alarmClockDisplaysCurrentTime("15:56:32");
    }

    @Test
    public void scenarioClockTicksAndAlarmIsOn() {
        // Given
        sut.userSwitchesOnAlarm("17:00");

        // When
        sut.systemClockTicks("16:56:32");

        // Then
        sut.alarmClockDisplaysCurrentTime("16:56:32");
        sut.alarmClockDisplaysRemainingTime("00:03:28");
    }

    @Test
    public void scenarioRingAlarmBell() {
        // Given
        sut.userSwitchesOnAlarm("18:00");

        // When
        sut.systemClockTicks("18:00:01");

        // Then
        sut.alarmClockDisplaysCurrentTime("18:00:01");
        sut.alarmBellHasRung();
    }

}
