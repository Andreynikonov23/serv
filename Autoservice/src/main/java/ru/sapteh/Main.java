package ru.sapteh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
        stage.setTitle("Автосервис");
        Image image = new Image(String.valueOf(getClass().getResource("/image/service_logo.png")));
        stage.getIcons().add(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
