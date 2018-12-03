/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.fxml;

import javafx.fxml.*;

import java.io.*;
import java.net.*;

public class ViewControllerFactory {

    public static <T> T create(Class<T> viewControllerType) throws IOException {
        String viewName = getViewFilename(viewControllerType);
        URL viewLocation = viewControllerType.getResource(viewName);
        if (viewLocation == null) {
            throw new FileNotFoundException(viewName);
        }
        FXMLLoader loader = new FXMLLoader(viewLocation);
        loader.load();
        return loader.getController();
    }

    private static String getViewFilename(Class<?> viewController) {
        String name = viewController.getName();
        if (name.endsWith("Controller"))
            name = name.substring(0, name.length() - "Controller".length());
        name = name.replace(".", "/");
        name = "/" + name + ".fxml";
        return name;
    }

}
