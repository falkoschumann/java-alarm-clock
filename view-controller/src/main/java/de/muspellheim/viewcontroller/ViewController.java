/*
 * Alarm Clock
 * Copyright (c) 2018 Falko Schumann
 */

package de.muspellheim.viewcontroller;

import javafx.fxml.*;
import javafx.scene.*;

import java.util.*;

/**
 * Optional base class for FXML based view controllers.
 * <p>
 * Conventions:
 * </p>
 * <ul>
 * <li>The root node of FXML must have the id <code>view</code>.</li>
 * </ul>
 */
public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Parent view;

    /**
     * Replaced the constructor and is called after the fields, annotated with <code>@FXML</code>,
     * have been initialized.
     */
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
