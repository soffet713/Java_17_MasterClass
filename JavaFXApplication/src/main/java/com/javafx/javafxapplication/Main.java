package com.javafx.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		Scene scene = new Scene(fxmlLoader.load(), 1250, 650);
		stage.setTitle("JavaFX Application");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}