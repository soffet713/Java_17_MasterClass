module com.javafx.section25_javafx_jdk21 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.javafx.section25_javafx_jdk21 to javafx.fxml;
	exports com.javafx.section25_javafx_jdk21;
}