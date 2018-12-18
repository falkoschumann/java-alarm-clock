module de.muspellheim.base {
    requires javafx.controls;
    requires javafx.fxml;

    exports de.muspellheim.fxml;
    exports de.muspellheim.util;

    opens de.muspellheim.fxml to javafx.fxml;
}
