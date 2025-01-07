package com.javafx.events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
	@FXML
	public TextField nameField;
	@FXML
	public Button helloButton;
	@FXML
	public Button byeButton;
	@FXML
	public CheckBox ourCheckBox;
	@FXML
	public Label eventLabel1;
	@FXML
	private Label welcomeText;

	@FXML
	public void initialize() {
		helloButton.setDisable(true);
		byeButton.setDisable(true);
	}

	@FXML
	protected void onButtonClicked(ActionEvent e) {
		Object currObject = e.getSource();
		if (currObject.equals(helloButton)) {
			System.out.printf("こんにちは, %sさん%n".formatted(nameField.getText()));
			System.out.printf("The following button was pressed: %s%n", e.getSource());
		} else if (currObject.equals(byeButton)) {
			System.out.printf("じゃあまたね, %sさん%n".formatted(nameField.getText()));
			System.out.printf("The following button was pressed: %s%n", e.getSource());
		}

		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
					System.out.printf("I'm going to sleep on the: %s%n", s);
					Thread.sleep(10000);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
							System.out.printf("I'm updating the label on the: %s%n", s);
							eventLabel1.setText("We did something!");
						}
					});
				} catch(InterruptedException event) {
					// do nothing for now
				}
			}
		};

		new Thread(task).start();

		if(ourCheckBox.isSelected()) {
			nameField.clear();
			helloButton.setDisable(true);
			byeButton.setDisable(true);
		}
	}

	@FXML
	public void handleKeyReleased() {
		String text = nameField.getText();
		boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
		helloButton.setDisable(disableButtons);
		byeButton.setDisable(disableButtons);
	}

	@FXML
	public void handleChange() {
		System.out.printf("The checkbox is %s.%n", (ourCheckBox.isSelected()) ? "checked" : "not checked");
	}
}