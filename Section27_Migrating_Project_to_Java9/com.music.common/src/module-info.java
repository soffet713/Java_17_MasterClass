module com.music.common {
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;

	opens com.music.common to javafx.base;
	exports com.music.common;
}