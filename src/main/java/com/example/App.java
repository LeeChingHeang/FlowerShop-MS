package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private double x =0;
    private double y =0;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // loading fxml login
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        // when run the program, it will load the login.fxml
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        // on click the mouse, it will get the x and y position and set opacity to 0.8
        root.setOnMousePressed(event -> {
            // get the x and y position of the mouse
            x = event.getSceneX();
            y = event.getSceneY();

            stage.setOpacity(.8);
        });
        
        // on release the mouse, it will set the opacity to 1
        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });
        
        // on drag the mouse, it will set to new position of x and y 
        root.setOnMouseDragged(event -> {
            // get the screen previous x and y position and set the stage to new position
            stage.setX(event.getScreenX() - x); 
            stage.setY(event.getScreenY() - y);
        });
        // set the stage to transparent (border-less)
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


}