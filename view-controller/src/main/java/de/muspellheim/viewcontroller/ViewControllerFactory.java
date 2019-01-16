/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.viewcontroller;

import javafx.fxml.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Loads a FXML view controller by convention.
 * <p>
 * Conventions:
 * </p>
 * <ul>
 * <li>The controller should have the suffix <code>Controller</code>l</li>
 * <li>The view FXML file must have the same name as the controller class without the suffix <code>Controller</code>.</li>
 * </ul>
 */
public class ViewControllerFactory {

    public static <T> T load(Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getViewLocation(controllerClass), getResourceBundle(controllerClass));
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new IllegalStateException("The controller " + controllerClass + " can not load its view: " + e, e);
        }
    }

    private static <T> URL getViewLocation(Class<T> controllerClass) {
        String viewFile = "/" + getClassName(controllerClass).replace(".", "/") + ".fxml";
        URL viewLocation = controllerClass.getResource(viewFile);
        if (viewLocation == null) {
            throw new IllegalStateException("The controller " + controllerClass + " can not found its view: " + viewFile);
        }
        return viewLocation;
    }

    private static <T> ResourceBundle getResourceBundle(Class<T> controllerClass) {
        try {
            return ResourceBundle.getBundle(getClassName(controllerClass), controllerClass.getModule());
        } catch (MissingResourceException e) {
            return null;
        }
    }

    private static String getClassName(Class<?> controllerClass) {
        String name = controllerClass.getName();
        if (name.endsWith("Controller")) {
            name = name.substring(0, name.length() - "Controller".length());
        }
        return name;
    }

}
