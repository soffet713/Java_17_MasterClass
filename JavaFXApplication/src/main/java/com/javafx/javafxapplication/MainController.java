package com.javafx.javafxapplication;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;

public class MainController {
	@FXML
	private WebView myWebView;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button buttonOne;
	@FXML
	private Button buttonTwo;
	@FXML
	private Button buttonThree;
	@FXML
	private Button buttonFour;
	@FXML
	private Button buttonFive;
	@FXML
	private Button exitButton;
	@FXML
	private Label effectsLabel;
	@FXML
	private Label welcomeText;
	int lastClicked = -1;

	public void initialize() {
		buttonFour.setEffect(new DropShadow());
	}

	@FXML
	public void buttonOneClick() { lastClicked = 1; onHelloButtonClick(lastClicked); }
	@FXML
	public void buttonTwoClick() { lastClicked = 2; onHelloButtonClick(lastClicked); }
	@FXML
	public void buttonThreeClick() { lastClicked = 3; onHelloButtonClick(lastClicked); }
	@FXML
	public void buttonFourClick() { lastClicked = 4; onHelloButtonClick(lastClicked); }
	@FXML
	public void buttonFiveClick() { lastClicked = 5; onHelloButtonClick(lastClicked); }

	@FXML
	protected void onHelloButtonClick(int clickedNumber) {
		switch (clickedNumber) {
			case 1 : welcomeText.setText("Welcome to JavaFX Application! Thank you for pressing Button One");
				break;
			case 2 : welcomeText.setText("Welcome to JavaFX Application! Thank you for pressing Button Two");
				break;
			case 3 : welcomeText.setText("Welcome to JavaFX Application! Thank you for pressing Button Three");
				break;
			case 4 : welcomeText.setText("Welcome to JavaFX Application! Thank you for pressing Button Four");
				break;
			case 5 : welcomeText.setText("Welcome to JavaFX Application! Thank you for pressing Button Five");
				break;
			default : welcomeText.setText("Welcome to JavaFX Application!");
				break;
		}
	}

	@FXML
	public void closeApplication(ActionEvent actionEvent) {
		Platform.exit();
	}

	@FXML
	public void handleMouseEnter() {
		effectsLabel.setScaleX(2.0);
		effectsLabel.setScaleY(2.0);
		effectsLabel.setRotate(45);
	}

	@FXML
	public void handleMouseExit() {
		effectsLabel.setScaleX(1.0);
		effectsLabel.setScaleY(1.0);
		effectsLabel.setRotate(0);
	}

	@FXML
	public void handleClick() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save Application File");
		chooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Text", "*.txt"),
				new FileChooser.ExtensionFilter("PDF", "*.pdf")
		);

		//chooser.showOpenDialog(gridPane.getScene().getWindow());
		File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
		//DirectoryChooser chooser = new DirectoryChooser();
		//File file = chooser.showDialog(gridPane.getScene().getWindow());
		if (file != null) {
			welcomeText.setText(file.getPath());
			System.out.println(file.getPath());
		} else {
			welcomeText.setText("Chooser was cancelled.");
			System.out.println("Chooser was cancelled.");
		}
	}

	@FXML
	public void handleLinkClick() {
		//welcomeText.setText("The link openjfx.io was clicked");
		//System.out.println("The link openjfx.io was clicked");

		//try {
		//	Desktop.getDesktop().browse(new URI("https://openjfx.io/"));
		//} catch (IOException | URISyntaxException e) {
		//	e.printStackTrace();
		//}

		WebEngine engine = myWebView.getEngine();
		engine.load("https://openjfx.io/");
	}
}