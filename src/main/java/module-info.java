module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.lang3;
    requires org.jfree.jfreechart;
    opens com.example to javafx.fxml;
    exports com.example;
}
