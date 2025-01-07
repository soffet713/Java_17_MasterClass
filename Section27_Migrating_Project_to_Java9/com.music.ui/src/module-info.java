module com.music.ui {
	requires javafx.swt;
	requires com.music.db;
	requires javafx.fxml;
	requires javafx.controls;

	exports com.music.ui to javafx.graphics, javafx.fxml, com.music.db, com.music.common;
	opens com.music.ui to javafx.fxml;
}