package com.javafx.scenebuilderchallenge;

import com.javafx.scenebuilderchallenge.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
	@FXML
	private BorderPane mainPanel;

	@FXML
	private TableView<Contact> contactsTable;

	private ContactData data;

	public void initialize() {
		data = new ContactData();
		data.loadContacts();

		contactsTable.setItems(data.getContacts());
	}

	@FXML
	public void closeApplication() {
		Platform.exit();
	}

	@FXML
	public void showAddContactDialog() {
		boolean addContact = false;
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.initOwner(mainPanel.getScene().getWindow());
		dialog.setTitle("Add New Contact");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml"));

		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("ERROR: Couldn't load the dialog.");
			e.printStackTrace();
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		Optional<ButtonType> result = dialog.showAndWait();

		if(result.isPresent() && result.get() == ButtonType.OK) {
			ContactController contactController = fxmlLoader.getController();
			String firstNameTxt = contactController.firstNameField.getText();
			String lastNameTxt = contactController.lastNameField.getText();

			if (firstNameTxt == null || firstNameTxt.isBlank()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("First Name Field Is Blank");
				alert.setHeaderText(null);
				alert.setContentText("First Name cannot be left blank. Please fill in the First Name field.");
				alert.showAndWait();
			}

			if (lastNameTxt == null || lastNameTxt.isBlank()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("No Contact Selected");
				alert.setHeaderText(null);
				alert.setContentText("Last Name cannot be left blank. Last fill in the First Name field.");
				alert.showAndWait();
			}

			if (firstNameTxt != null && !firstNameTxt.isBlank() && lastNameTxt != null && !lastNameTxt.isBlank()) {
				Contact newContact = contactController.getNewContact();
				data.addContact(newContact);
				data.saveContacts();
			} else {
				showAddContactDialog();
			}
		}
	}

	@FXML
	public void showEditContactDialog() {
		boolean saveEdits = false;
		Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();

		if(selectedContact == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("No Contact Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select the contact you want to edit.");
			alert.showAndWait();
			return;
		}

		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainPanel.getScene().getWindow());
		dialog.setTitle("Edit Contact");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("ERROR: Couldn't load the dialog.");
			e.printStackTrace();
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		ContactController contactController = fxmlLoader.getController();
		contactController.editContact(selectedContact);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			String firstNameTxt = contactController.firstNameField.getText();
			String lastNameTxt = contactController.lastNameField.getText();

			if (firstNameTxt == null || firstNameTxt.isBlank()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("First Name Field Is Blank");
				alert.setHeaderText(null);
				alert.setContentText("First Name cannot be left blank. Please fill in the First Name field.");
				alert.showAndWait();
			}

			if (lastNameTxt == null || lastNameTxt.isBlank()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Last Name Field is Blank");
				alert.setHeaderText(null);
				alert.setContentText("Last Name cannot be left blank. Last fill in the First Name field.");
				alert.showAndWait();
			}

			if(firstNameTxt != null && !firstNameTxt.isBlank() && lastNameTxt != null && !lastNameTxt.isBlank()) {
				saveEdits = true;
			}

			if(saveEdits) {
				contactController.updateContact(selectedContact);
				data.saveContacts();
			} else {
				showEditContactDialog();
			}
		}
	}

	@FXML
	public void deleteContact() {
		Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();

		if (selectedContact == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("No Contact Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select the contact you want to delete.");
			alert.showAndWait();
			return;
		}

		Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
		deleteAlert.setTitle("Delete Contact");
		deleteAlert.setHeaderText(null);
		deleteAlert.setContentText(String.format("Are you sure you want to delete the selected contact: %s %s",
				selectedContact.getFirstName(), selectedContact.getLastName()));

		Optional<ButtonType> result = deleteAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			data.deleteContact(selectedContact);
			data.saveContacts();
		}
	}
}