module com.javafx.events {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;


	opens com.javafx.events to javafx.fxml;
	exports com.javafx.events;
}