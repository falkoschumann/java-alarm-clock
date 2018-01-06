/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.util;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Event<T> {

    private List<Consumer<T>> observers = new CopyOnWriteArrayList<>();

    public void addObserver(Consumer<T> o) {
        observers.add(o);
    }

    public void removeObserver(Consumer<T> o) {
        observers.remove(o);
    }

    public void send(T data) {
        observers.forEach(o -> o.accept(data));
    }

}
