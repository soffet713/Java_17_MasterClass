package com.javafx.todolist;

import com.javafx.todolist.datamodel.ToDoData;
import com.javafx.todolist.datamodel.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

	@FXML
	private TextField shortDescription;

	@FXML
	private TextArea detailsArea;

	@FXML
	private DatePicker deadlinePicker;

	public ToDoItem processResults() {

		String shortDesc = shortDescription.getText().trim();
		String details = detailsArea.getText().trim();
		LocalDate deadlineDate = deadlinePicker.getValue();

		ToDoItem newItem = new ToDoItem(shortDesc, details, deadlineDate);
		ToDoData.getInstance().addToDoItem(newItem);
		return newItem;
	}
}
