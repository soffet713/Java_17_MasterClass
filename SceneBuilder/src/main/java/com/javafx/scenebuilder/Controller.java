package com.javafx.scenebuilder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onOKButtonClick() {
        welcomeText.setText("OK Button pressed");
    }

    @FXML
    protected void onCancelButtonClick() {
        Platform.exit();
    }
}