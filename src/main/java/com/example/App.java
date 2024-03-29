
package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    // assign mouse coordinates
    private static double x = 0;
    private static double y = 0;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        setRoot(stage, "Login");
    }

    static void setRoot(Stage stage, String fxml) throws IOException {
        // scene.setRoot(loadFXML(fxml));
        Parent root = loadFXML(fxml);
        scene = new Scene(root);
        /// Interaction with the window
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
             /// Interaction with the window
            // if dragged on-pressed the root of scene it will move the scene and set opacity to .8 
            if("Login".equals(fxml))
                stage.setOpacity(.8);
        });
        
        root.setOnMouseReleased(event -> stage.setOpacity(1));
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
