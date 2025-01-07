package com.javafx.todolist;

import com.javafx.todolist.datamodel.ToDoData;
import com.javafx.todolist.datamodel.ToDoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
	@FXML
	public ToggleButton filterToggleButton;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private Label deadlineLabel;
	@FXML
	private ListView<ToDoItem> toDoListView;
	@FXML
	private TextArea detailTextArea;
	@FXML
	private List<ToDoItem> toDoItems;
	@FXML
	private ContextMenu listContextMenu;
	private FilteredList<ToDoItem> filteredList;
	private Predicate<ToDoItem> wantAllItems;
	private Predicate<ToDoItem> wantTodaysItems;

	public void initialize() {

		listContextMenu = new ContextMenu();
		MenuItem deleteMenuItem = new MenuItem("Delete");

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
				deleteItem(item);
			}
		});

		listContextMenu.getItems().addAll(deleteMenuItem);
		toDoItems = ToDoData.getInstance().getToDoItems();
		//ToDoItem item1 = new ToDoItem("Mail Birthday Card", "Buy 30th birthday card for John",
		//		LocalDate.of(2024, Month.OCTOBER, 12));
		//ToDoItem item2 = new ToDoItem("Doctor's Appointment", "See Dr. Smith at 123 Main Street. Bring Paperwork.",
		//		LocalDate.of(2024, Month.OCTOBER, 17));
		//ToDoItem item3 = new ToDoItem("Finish Design Proposal", "I promised Mike I'd email website mockups by Friday, 25th October.",
		//		LocalDate.of(2024, Month.OCTOBER, 25));
		//ToDoItem item4 = new ToDoItem("Pickup Doug at the Train Station", "Doug's Arriving on September 29th, 2024 on the 5:00 train.",
		//		LocalDate.of(2024, Month.OCTOBER, 12));
		//ToDoItem item5 = new ToDoItem("Pick Up Dry Cleaning", "The clothes should be ready by Wednesday",
		//		LocalDate.of(2024, Month.OCTOBER, 2));

		//toDoItems = new ArrayList<ToDoItem>();
		//toDoItems.add(item1);
		//toDoItems.add(item2);
		//toDoItems.add(item3);
		//toDoItems.add(item4);
		//toDoItems.add(item5);

		//ToDoData.getInstance().setToDoItems(toDoItems);

		toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
			@Override
			public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem toDoItem, ToDoItem t1) {
				if (t1 != null) {
					ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
					detailTextArea.setText(item.getDetails());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
					deadlineLabel.setText(df.format(item.getDeadline()));
				}
			}
		});

		wantAllItems = new Predicate<ToDoItem>() {
			@Override
			public boolean test(ToDoItem toDoItem) {
				return true;
			}
		};

		wantTodaysItems = new Predicate<ToDoItem>() {
			@Override
			public boolean test(ToDoItem toDoItem) {
				return (toDoItem.getDeadline().equals(LocalDate.now()));
			}
		};

		filteredList = new FilteredList<ToDoItem>(ToDoData.getInstance().getToDoItems(), wantAllItems);

		SortedList<ToDoItem> sortedList = new SortedList<ToDoItem>(filteredList, new Comparator<ToDoItem>() {
			@Override
			public int compare(ToDoItem o1, ToDoItem o2) {
				return o1.getDeadline().compareTo(o2.getDeadline());
			}
		});

		//toDoListView.getItems().setAll(toDoItems);
		//toDoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
		//toDoListView.setItems(ToDoData.getInstance().getToDoItems());
		toDoListView.setItems(sortedList);
		toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		toDoListView.getSelectionModel().selectFirst();

		toDoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
			@Override
			public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {
				ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {

					@Override
					protected void updateItem(ToDoItem item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						} else {
							setText(item.getShortDescription());
							if(item.getDeadline().isBefore(LocalDate.now())) {
								setTextFill(Color.RED);
							} else if (item.getDeadline().equals(LocalDate.now())) {
								setTextFill(Color.GREEN);
							} else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
								setTextFill(Color.ORANGE);
							}
						}
					}
				};

				cell.emptyProperty().addListener(
						(obx, wasEmpty, isNowEmpty) -> {
							if (isNowEmpty) {
								cell.setContextMenu(null);
							} else {
								cell.setContextMenu(listContextMenu);
							}
						}
				);

				return cell;
			}
		});
	}

	@FXML
	public void showNewItemDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add New To Do Item");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("toDoItemDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (Exception e) {
			System.out.println("Couldn't load the dialog.");
			System.out.println(e.getMessage());
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fxmlLoader.getController();
			ToDoItem newItem = controller.processResults();
			//toDoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
			toDoListView.getSelectionModel().select(newItem);
			//System.out.println("OK Button Pressed.");
		//} else {
		//	System.out.println("Cancel Button Pressed.");
		}
	}

	@FXML
	public void showEditItemDialog() {
		ToDoItem currItem = toDoListView.getSelectionModel().getSelectedItem();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Edit To Do Item");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("toDoItemDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
			dialog.getDialogPane().setContentText(currItem.getDetails());
		} catch (Exception e) {
			System.out.println("Couldn't load the dialog.");
			System.out.println(e.getMessage());
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fxmlLoader.getController();
			ToDoItem newItem = controller.processResults();
			//toDoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
			toDoListView.getSelectionModel().select(newItem);
			//System.out.println("OK Button Pressed.");
			//} else {
			//	System.out.println("Cancel Button Pressed.");
		}
	}

	@FXML
	public void handleKeyPressed(KeyEvent keyEvent) {
		ToDoItem selectedItem = toDoListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			if (keyEvent.getCode().equals(KeyCode.DELETE)) {
				deleteItem(selectedItem);
			}
		}
	}

	@FXML
	public void handleClickListView() {
		ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
		detailTextArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadline().toString());
		//System.out.printf("The selected item is %s%n", item);
		//StringBuilder sb = new StringBuilder(item.getDetails());
		//sb.append("\n\n\n\n");
		//sb.append("Due: ");
		//sb.append(item.getDeadline().toString());
		//detailTextArea.setText(sb.toString());
	}

	@FXML
	public void exitApplication() {
		try {
			Platform.exit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void deleteSelectedItem() {
		ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
		deleteItem(item);
	}

	@FXML
	public void deleteItem (ToDoItem item) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete To Do Item");
		alert.setHeaderText("Delete item: " + item.getShortDescription());
		alert.setContentText("Are you sure? Press OK to confirm, or Cancel to back out.");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && (result.get() == ButtonType.OK)) {
			ToDoData.getInstance().deleteToDoItem(item);
		}
	}

	@FXML
	public void handleFilterButton() {
		ToDoItem selectedItem = toDoListView.getSelectionModel().getSelectedItem();
		if(filterToggleButton.isSelected()) {
			filteredList.setPredicate(wantTodaysItems);
			if(filteredList.isEmpty()) {
				detailTextArea.clear();
				deadlineLabel.setText("");
			} else if (filteredList.contains(selectedItem)) {
				toDoListView.getSelectionModel().select(selectedItem);
			} else {
				toDoListView.getSelectionModel().selectFirst();
			}
		} else {
			filteredList.setPredicate(wantAllItems);
			toDoListView.getSelectionModel().select(selectedItem);
		}
	}
}