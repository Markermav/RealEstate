package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.House;
import com.example.model.Property;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class AddHouseController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<Property.propertyState> choiceBox;

    @FXML
    private TextField city;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField streetName;

    @FXML
    private TextField streetNumber;

    @FXML
    private Button submit;

    @FXML
    void bOnAddTenant(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTenant.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnAddUnit(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProperty2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void bOnRentUnit(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rentUnit.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowLeases(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showLeases.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowTenants(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showTenants.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowUnits(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showUnits.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Property.propertyState optionList[] = {Property.propertyState.VacantReady, Property.propertyState.VacantRenovation, Property.propertyState.Rented};
        list = FXCollections.observableArrayList(optionList);
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(Property.propertyState.VacantReady);
    }
    @FXML
    void bOnBack(ActionEvent event) {
        Stage stage = (Stage) city.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProperty2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void bOnSubmit(ActionEvent event) {
        try {
            String[] houseDataArray = {streetName.getText(), city.getText(), postalCode.getText(), streetNumber.getText()};
            Property house = new House(houseDataArray[0], houseDataArray[1], houseDataArray[2], Integer.parseInt(houseDataArray[3]));
            CompletableFuture.runAsync(() -> {
                AccountingSystem model = AccountingSystem.getInstance();
                house.setState(choiceBox.getValue());
                house.setType(Property.propertyType.House);
                model.addHouse(house);
                Platform.runLater(() -> showSuccessMessage());
            });
        } catch (Exception e){
            // move it to alert when error happens
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while submitting the form.");
            alert.initOwner(submit.getScene().getWindow());
            alert.showAndWait();
        }
    }

    @FXML
    void resetForm() {
        streetName.clear();
        city.clear();
        postalCode.clear();
        streetNumber.clear();

    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("House added successfully!");
        alert.initOwner(submit.getScene().getWindow());
        alert.showAndWait();
        resetForm();
    }


}
