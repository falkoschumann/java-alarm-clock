/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.util;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public final class Event<T> {

    private final List<Consumer<T>> handlers = new CopyOnWriteArrayList<>();

    public void addHandler(Consumer<T> handler) {
        handlers.add(handler);
    }

    public void removeHandler(Consumer<T> handler) {
        handlers.remove(handler);
    }

    public void send(T message) {
        handlers.forEach(h -> {
            try {
                h.accept(message);
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
