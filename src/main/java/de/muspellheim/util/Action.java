/*
 * Copyright (c) 2018 Falko Schumann
 * Licensed under the terms of the MIT License.
 */

package de.muspellheim.util;

import java.util.*;
import java.util.concurrent.*;

public class Action {

    private List<Runnable> observers = new CopyOnWriteArrayList<>();

    public void addObserver(Runnable o) {
        observers.add(o);
    }

    public void removeObserver(Runnable o) {
        observers.remove(o);
    }

    public void trigger() {
        observers.forEach(Runnable::run);
    }

}
