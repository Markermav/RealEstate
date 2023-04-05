package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.Condo;
import com.example.model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddCondoController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<Property.propertyState> choiceBox;


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
    private Button submit;

    @FXML
    private TextField city;

    @FXML
    private TextField condoNumber;

    @FXML
    private TextField numberOfBathrooms;

    @FXML
    private TextField numberOfBedrooms;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField streetName;

    @FXML
    private TextField streetNumber;
    @FXML
    private TextField totalSquareFt;
    @FXML
    void bOnBack(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
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
    void bOnSubmit(ActionEvent event) {
        CompletableFuture.runAsync( () ->
        {
            AccountingSystem model = AccountingSystem.getInstance();
            String[] condoDataArray = {
                    streetName.getText(),
                    city.getText(),
                    postalCode.getText(),
                    condoNumber.getText(),
                    numberOfBedrooms.getText(),
                    numberOfBathrooms.getText(),
                    totalSquareFt.getText(),
                    streetNumber.getText()
            };
            Property condoProp = new Condo(condoDataArray[0],
                    condoDataArray[1], condoDataArray[2],
                    parseInt(condoDataArray[3]), parseInt(condoDataArray[4]),
                    parseInt(condoDataArray[5]), parseDouble(condoDataArray[6]),
                    parseInt(condoDataArray[7]));
            condoProp.setState(choiceBox.getValue());
            model.addCondo(condoProp);
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Property.propertyState optionList[] = {Property.propertyState.VacantReady, Property.propertyState.VacantRenovation, Property.propertyState.Rented};
        list = FXCollections.observableArrayList(optionList);
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(Property.propertyState.VacantReady);
    }
}
