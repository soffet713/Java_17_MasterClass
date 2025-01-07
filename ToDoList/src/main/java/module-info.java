module com.javafx.todolist {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.javafx.todolist to javafx.fxml;
	exports com.javafx.todolist;
}