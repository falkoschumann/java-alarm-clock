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
            String viewFile = viewFile(controllerClass);
            URL viewLocation = controllerClass.getResource(viewFile);
            if (viewLocation == null) {
                throw new IllegalStateException("The controller " + controllerClass + " can not found its view: " + viewFile);
            }
            FXMLLoader loader = new FXMLLoader(viewLocation);
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new IllegalStateException("The controller " + controllerClass + " can not load its view: " + e, e);
        }
    }

    private static String viewFile(Class<? extends ViewController> controllerClass) {
        String name = controllerClass.getName();
        if (name.endsWith("Controller"))
            name = name.substring(0, name.length() - "Controller".length());
        name = name.replace(".", "/");
        name = "/" + name + ".fxml";
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
