package com.example.supermarket;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {


    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, Color.AQUAMARINE);

        Image icon = new Image("file:com/example/supermarket/Images/logo.png");
        stage.getIcons().add(icon);
        stage.setTitle("Supermercado");
        stage.setWidth(42);
        stage.setHeight(42);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("YOU CAN'T ESCAPE unless you press q");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        stage.setScene(scene);
        stage.show();
    }
}