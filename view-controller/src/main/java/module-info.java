module de.muspellheim.viewcontroller {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    exports de.muspellheim.viewcontroller;

    opens de.muspellheim.viewcontroller to javafx.fxml;
}
