package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class PrimaryController implements Initializable{
    
    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane main_form;
    
    public void close() {
        System.exit(0);
    }
    
    public void login() {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
