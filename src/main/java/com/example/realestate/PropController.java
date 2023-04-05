package com.example.realestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PropController {

    @FXML
    private Button addApt;

    @FXML
    private Button addCondo;

    @FXML
    private Button addHouse;

    @FXML
    private Button addTenant;

    @FXML
    private Button addUnit;

    @FXML
    private Button back;

    @FXML
    private Button rentUnit;

    @FXML
    private Button showLeases;

    @FXML
    private Button showTenants;

    @FXML
    private Button showUnits;

    @FXML
    void bOnAddHouse(ActionEvent event) {
        Stage stage = (Stage) addHouse.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addPropertyHouse.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void bOnAddApt(ActionEvent event) {
        Stage stage = (Stage) addApt.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addPropertyApt.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnAddCondo(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addPropertyCondo.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnAddTenant(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTenant.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnBack(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnRentUnit(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rentUnit.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowLeases(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showLeases.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowTenants(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showTenants.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowUnits(ActionEvent event) {
        Stage stage = (Stage) addCondo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showUnits.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
