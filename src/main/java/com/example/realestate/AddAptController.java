package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.Apartment;
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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddAptController implements Initializable {

    @FXML
    private TextField aptNumber;

    @FXML
    private Button back;

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<Property.propertyState> choiceBox;

    @FXML
    private TextField city;

    @FXML
    private TextField numberOfBathrooms;

    @FXML
    private TextField numberOfBedrooms;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField streetName;

    @FXML
    private Button submit;

    @FXML
    private TextField totalSquareFt;
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
    void bOnAddTenant(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addTenant.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnAddUnit(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addProperty2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void bOnRentUnit(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rentUnit.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowLeases(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showLeases.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowTenants(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showTenants.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bOnShowUnits(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showUnits.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetForm() {
        streetName.clear();
        city.clear();
        postalCode.clear();
        aptNumber.clear();
        numberOfBedrooms.clear();
        numberOfBathrooms.clear();
        totalSquareFt.clear();
        //choiceBox.getSelectionModel().clearSelection();
    }

    @FXML
    void bOnSubmit(ActionEvent event) {
        try {
            String[] apartmentDataArray = {streetName.getText(), city.getText(), postalCode.getText(), aptNumber.getText(), numberOfBedrooms.getText(), numberOfBathrooms.getText(), totalSquareFt.getText()};
            Property aptProp = new Apartment(apartmentDataArray[0],
                    apartmentDataArray[1], apartmentDataArray[2],
                    parseInt(apartmentDataArray[3]), parseInt(apartmentDataArray[4]),
                    parseInt(apartmentDataArray[5]), parseDouble(apartmentDataArray[6]));
            CompletableFuture.runAsync(() -> {
                AccountingSystem model = AccountingSystem.getInstance();
                aptProp.setState(choiceBox.getValue());
                aptProp.setType(Property.propertyType.Apartment);
                model.addApartment(aptProp);
                Platform.runLater(() -> showSuccessMessage());
            });
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while submitting the form.");
            alert.initOwner(submit.getScene().getWindow());
            alert.showAndWait();
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Apartment added successfully!");
        alert.initOwner(submit.getScene().getWindow());
        alert.showAndWait();
        resetForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Property.propertyState optionList[] = {Property.propertyState.VacantReady, Property.propertyState.VacantRenovation, Property.propertyState.Rented};
        list = FXCollections.observableArrayList(optionList);
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(Property.propertyState.VacantReady);
    }
}
