module com.javafx.controls {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;


	opens com.javafx.controls to javafx.fxml;
	exports com.javafx.controls;
}