package com.javafx.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ToDoData {

	private static ToDoData instance = new ToDoData();
	private static String fileName = "toDoListItems.txt";

	private ObservableList<ToDoItem> toDoItems;
	private DateTimeFormatter formatter;

	public static ToDoData getInstance() {
		return instance;
	}

	private ToDoData() {
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}

	public ObservableList<ToDoItem> getToDoItems() {
		return toDoItems;
	}

	public void addToDoItem(ToDoItem item) {
		toDoItems.add(item);
	}

	public void loadToDoItems() throws IOException {

		toDoItems = FXCollections.observableArrayList();
		Path path = Paths.get(fileName);
		BufferedReader br = Files.newBufferedReader(path);

		String input;

		try {
			while ((input = br.readLine()) != null) {
				String[] itemPieces = input.split("\t");
				String shortDescription = itemPieces[0];
				String details = itemPieces[1];
				String dateString = itemPieces[2];

				LocalDate date = LocalDate.parse(dateString, formatter);
				ToDoItem itemToAdd = new ToDoItem(shortDescription, details, date);
				toDoItems.add(itemToAdd);
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public void storeToDoItems() throws IOException {

		Path path = Paths.get(fileName);
		try (BufferedWriter bw = Files.newBufferedWriter(path);) {
			Iterator<ToDoItem> iterator = toDoItems.iterator();

			while(iterator.hasNext()) {
				ToDoItem currItem = iterator.next();
				bw.write(String.format("%s\t%s\t%s",currItem.getShortDescription(),currItem.getDetails(),
						currItem.getDeadline().format(formatter)));
				bw.newLine();
			}
		}
	}

	public void deleteToDoItem(ToDoItem item) {
		toDoItems.remove(item);
	}
}
