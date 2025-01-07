module com.javafx.javafxapplication {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires java.desktop;


	opens com.javafx.javafxapplication to javafx.fxml;
	exports com.javafx.javafxapplication;
}