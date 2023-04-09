package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.Property;
import com.example.model.PropertyComponent;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowUnitsController implements Initializable {

    @FXML
    private TableView<PropertyComponent> table;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<PropertyComponent, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<PropertyComponent, String> typeColumn = new TableColumn<>("Type");
        TableColumn<PropertyComponent, String> stateColumn = new TableColumn<>("State");;
        TableColumn<PropertyComponent, String> sNameColumn = new TableColumn<>("StreetName");
        TableColumn<PropertyComponent, String> cityColumn = new TableColumn<>("City");;
        TableColumn<PropertyComponent, String> postalCodeColumn = new TableColumn<>("Postal Code");;

        table.getColumns().addAll(idColumn,typeColumn, stateColumn, cityColumn, sNameColumn,postalCodeColumn);

        AccountingSystem model = AccountingSystem.getInstance();
        ObservableList<PropertyComponent> propertyObservableList = FXCollections.observableArrayList(model.getPropertyList());
        table.setItems(propertyObservableList);

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(((Property) cellData.getValue()).getID()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Property) cellData.getValue()).getType().toString()));
        sNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Property) cellData.getValue()).getstreetName()));
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Property) cellData.getValue()).getCity()));
        postalCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Property) cellData.getValue()).getpostalCode()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty( ((Property) cellData.getValue()).getState().name()) );
        table.refresh();

    }
}
