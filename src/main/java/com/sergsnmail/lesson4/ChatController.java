package com.sergsnmail.lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatController {
    @FXML
    private TextArea output;
    @FXML
    private TextField input;

    public void inputMessage(ActionEvent actionEvent) {
        setOutput(input.getText());
    }

    private void setOutput(String message) {
        if (!message.equals("")) {
            output.appendText(message + "\n");
            input.clear();
        }
    }

    public void keyListener(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            setOutput(input.getText());
        }
    }
}
