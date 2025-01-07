module com.javafx.scenebuilderchallenge {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.xml;


	opens com.javafx.scenebuilderchallenge to javafx.fxml;
	exports com.javafx.scenebuilderchallenge;
}