/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.fxml;

import javafx.fxml.*;
import javafx.scene.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Parent view;

    public static <T extends ViewController> T load(Class<T> controllerClass) {
        try {
            URL viewLocation = getViewLocation(controllerClass);
            ResourceBundle resources = getResourceBundle(controllerClass);
            FXMLLoader loader = new FXMLLoader(viewLocation, resources);
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new IllegalStateException("The controller " + controllerClass + " can not load its view: " + e, e);
        }
    }

    private static <T extends ViewController> URL getViewLocation(Class<T> controllerClass) {
        String viewFile = "/" + getBaseName(controllerClass) + ".fxml";
        URL viewLocation = controllerClass.getResource(viewFile);
        if (viewLocation == null) {
            throw new IllegalStateException("The controller " + controllerClass + " can not found its view: " + viewFile);
        }
        return viewLocation;
    }

    private static <T extends ViewController> ResourceBundle getResourceBundle(Class<T> controllerClass) {
        try {
            return ResourceBundle.getBundle(getClassName(controllerClass), controllerClass.getModule());
        } catch (MissingResourceException e) {
            return null;
        }
    }

    private static String getBaseName(Class<? extends ViewController> controllerClass) {
        return getClassName(controllerClass).replace(".", "/");
    }

    private static String getClassName(Class<? extends ViewController> controllerClass) {
        String name = controllerClass.getName();
        if (name.endsWith("Controller")) {
            name = name.substring(0, name.length() - "Controller".length());
        }
        return name;
    }

    @FXML
    protected void initialize() {
    }

    public final ResourceBundle getResources() {
        return resources;
    }

    public final Parent getView() {
        return view;
    }

}
