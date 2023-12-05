package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.api.GetUserData; 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
public class LoginController implements Initializable{
    
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
    
     
    public void login() throws IOException {
        // Just for debugging
        this.username.setText("admin");
        this.password.setText("admin123");
        // calling our database api
        
        GetUserData db = new GetUserData();
        String username = this.username.getText(); 
        String password = this.password.getText();
         
        Alert alert;
        // check if username and password are empty
        if(username.isEmpty() || password.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Username or Password field is blank");
            alert.showAndWait();
        }else{
            // check if username and password are correct
                // for(User user : db.getUserList()) {
                //     if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                //         return;
                //     }
                // }
            
            // check if user is authenticated
            // more secure way to check if user is authenticated 
            if(db.authenticateUser(username, password).isPresent()) {
                // IF user is authenticated
                 
                // // debug
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("You are logged in");
                alert.showAndWait();
                
                // get username form textfield which we need to call this.username
                getData.username = this.username.getText(); // since user are authenticated we can now get the username but not need to load it from database just get form textfield so faster loading
                // Hide the current window (login form)
                // loginBtn.getScene().getWindow().hide();
                
                // no-hide just close
                Window window = loginBtn.getScene().getWindow();
                ((Stage) window).close();

                // Loading DASHBOARD Form
                // Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                // Stage stage = new Stage();
                // Scene scene = new Scene(root);
                
                // // grab the root of the scene
                // root.setOnMousePressed(event -> {
                //     x = event.getSceneX();
                //     y = event.getSceneY();
                // });
                // // if dragged when mouse is pressed on root of scene it will move the window and set opacity to .8 
                // root.setOnMouseDragged(event -> {
                //     stage.setX(event.getScreenX() - x);
                //     stage.setY(event.getScreenY() - y);
                    
                //     // stage.setOpacity(.8);
                // });
                
                // // // on release of mouse set opacity back to 1
                // // root.setOnMouseReleased(event -> {
                // //     stage.setOpacity(1);
                // // });
                
                // // set window to border-less
                // stage.initStyle(StageStyle.TRANSPARENT);
                // stage.setScene(scene);
                // stage.show();
                
                // re-using the setRoot method from App.java
                Stage stage = new Stage();
                App.setRoot(stage, "dashboard");
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username or Password is Invalid!\n\tPlease try again!");
                alert.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}
