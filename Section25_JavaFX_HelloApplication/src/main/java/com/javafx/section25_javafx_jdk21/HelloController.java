package com.javafx.section25_javafx_jdk21;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.application.Platform.exit;

public class HelloController {
	@FXML
	private Label welcomeText;

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
		welcomeText.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));
		welcomeText.setAlignment(Pos.BOTTOM_CENTER);
		welcomeText.setStyle("-fx-border-color: blue; -fx-border-width: 3; -fx-border-style: dashed;");
	}

	@FXML
	protected void onCloseButtonClick() {
		exit();
	}
}