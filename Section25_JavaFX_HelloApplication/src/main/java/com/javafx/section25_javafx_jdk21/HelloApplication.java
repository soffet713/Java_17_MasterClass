package com.javafx.section25_javafx_jdk21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controls-view.fxml"));
		//GridPane root = new GridPane();
		//root.setAlignment(Pos.CENTER);
		//root.setVgap(20);
		//root.setHgap(20);
		//root.setPadding(new Insets(20));

		//Label greeting = new Label("Welcome to JavaFX Application!");
		//greeting.setTextFill(Color.GREEN);
		//greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
		//greeting.setPadding(new Insets(30));

		//Button displayGreeting = new Button("Hello JavaFX!");
		//root.getChildren().add(displayGreeting);
		//displayGreeting.setOnAction(new EventHandler<ActionEvent>() {
			//@Override
			//public void handle(ActionEvent actionEvent) {
				//root.getChildren().addFirst(greeting);
				//stage.setWidth(1500);
				//displayGreeting.setAlignment(Pos.BOTTOM_CENTER);
			//}
		//});

		stage.setTitle("Hello JavaFX!");
		//stage.setScene(new Scene(root, 300, 275));
		stage.setScene(new Scene(fxmlLoader.load(), 500, 275));
		stage.setResizable(true);
		stage.show();

		//if(javafx.fxml.FXML)
	}

	public static void main(String[] args) {
		launch();
	}
}