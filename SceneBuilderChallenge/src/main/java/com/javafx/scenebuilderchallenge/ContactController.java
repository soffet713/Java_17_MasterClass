package com.javafx.scenebuilderchallenge;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ContactController {
	@FXML
	protected TextField firstNameField;
	@FXML
	protected TextField lastNameField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField notesField;

	public Contact getNewContact() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();

		String phoneNumber = phoneNumberField.getText();
		String email = emailField.getText();
		String notes = notesField.getText();

		if (phoneNumber == null || phoneNumber.isBlank()) {
			phoneNumber = "###-###-####";
		}

		if (email == null || email.isBlank()) {
			email = "*";
		}

		if (notes == null || notes.isBlank()) {
			notes = "*";
		}

		return new Contact(firstName, lastName, phoneNumber, email, notes);
	}

	public void editContact(Contact contact) {
		firstNameField.setText(contact.getFirstName());
		lastNameField.setText(contact.getLastName());
		phoneNumberField.setText(contact.getPhoneNumber());
		emailField.setText(contact.getEmail());
		notesField.setText(contact.getNotes());
	}

	public void updateContact(Contact contact) {
		boolean updateInfoComplete = false;

		if (firstNameField.getText() == null || firstNameField.getText().isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("First Name Field is Blank");
			alert.setHeaderText(null);
			alert.setContentText("First Name cannot cannot be blank.");
			alert.showAndWait();
		}

		if (lastNameField.getText() == null || lastNameField.getText().isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Last Name Field is Blank");
			alert.setHeaderText(null);
			alert.setContentText("Last Name cannot be blank.");
			alert.showAndWait();
		}

		if (phoneNumberField.getText() == null || phoneNumberField.getText().isBlank()) {
			phoneNumberField.setText("###-###-####");
		}

		if (emailField.getText() == null || emailField.getText().isBlank()) {
			emailField.setText("*");
		}

		if (notesField.getText() == null || notesField.getText().isBlank()) {
			notesField.setText("*");
		}

		contact.setFirstName(firstNameField.getText());
		contact.setLastName(lastNameField.getText());
		contact.setPhoneNumber(phoneNumberField.getText());
		contact.setEmail(emailField.getText());
		contact.setNotes(notesField.getText());
	}
}
