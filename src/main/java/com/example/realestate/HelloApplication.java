package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.House;
import com.example.model.Property;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class HelloApplication extends Application {
    public String mainString = "TEST";

    @Override
    public void start(Stage stage) throws IOException {
        CompletableFuture.runAsync( () -> AccountingSystem.getInstance().initSampleData());
        EventView eventHandler = new EventView();
        AccountingSystem model = AccountingSystem.getInstance();
        model.addObserver(eventHandler);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}