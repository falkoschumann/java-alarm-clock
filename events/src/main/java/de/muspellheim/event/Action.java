/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.event;

import java.util.*;
import java.util.concurrent.*;

/**
 * An action is an {@link Event} without message.
 */
public final class Action {

    private final List<Runnable> handlers = new CopyOnWriteArrayList<>();

    public void addHandler(Runnable handler) {
        handlers.add(handler);
    }

    public void removeHandler(Runnable handler) {
        handlers.remove(handler);
    }

    public void trigger() {
        handlers.forEach(h -> {
            try {
                h.run();
            } catch (RuntimeException e) {
                handleException(e);
            }
        });
    }

    private static void handleException(RuntimeException e) {
        var exceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
        if (exceptionHandler != null) {
            exceptionHandler.uncaughtException(Thread.currentThread(), e);
        }
    }

}
