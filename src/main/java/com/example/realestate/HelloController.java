package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.House;
import com.example.model.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class HelloController {

    @FXML
    private Button addUnit;

    @FXML
    private Button addTenant;

    @FXML
    private Button rentUnit;

    @FXML
    private Button showTenants;

    @FXML
    private Button showUnits;

    @FXML
    private Button showLeases;

    @FXML
    void bOnAddUnit(ActionEvent event)   {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProperty2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void bOnAddTenant(ActionEvent event) {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTenant.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnRentUnit(ActionEvent event) {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rentUnit.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowUnits(ActionEvent event) {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showUnits.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowTenants(ActionEvent event) {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showTenants.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowLeases(ActionEvent event) {
        Stage stage = (Stage) addUnit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showLeases.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnCheckUpdate(ActionEvent event) {
        CompletableFuture.runAsync( () -> {
            AccountingSystem model = AccountingSystem.getInstance();
            model.checkLeaseStates();
        });
    }




}
