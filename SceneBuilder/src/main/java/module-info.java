module com.javafx.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafx.scenebuilder to javafx.fxml;
    exports com.javafx.scenebuilder;
}