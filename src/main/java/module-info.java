module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires transitive javafx.graphics;
// test
//try

    opens com.example to javafx.fxml;
    exports com.example;
}
