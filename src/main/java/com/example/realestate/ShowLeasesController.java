package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.Lease;
import com.example.model.Property;
import com.example.model.PropertyComponent;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowLeasesController implements Initializable {

    @FXML
    private TableView<Lease> table;

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

        TableColumn<Lease, Integer> unitIDColumn = new TableColumn<>("Unit ID");
        TableColumn<Lease, Integer> tenantIDColumn = new TableColumn<>("Tenant ID");
        TableColumn<Lease, String> startDateColumn = new TableColumn<>("Start Date");
        TableColumn<Lease, String> endDateColumn = new TableColumn<>("End Date");
        TableColumn<Lease, Double> rentColumn = new TableColumn<>("Rent");
        TableColumn<Lease, Boolean> isRentedColumn = new TableColumn<>("Rented");
        table.getColumns().addAll(unitIDColumn,tenantIDColumn,isRentedColumn,rentColumn,startDateColumn,endDateColumn);

        AccountingSystem model = AccountingSystem.getInstance();
        ObservableList<Lease> LeaseObservableList = FXCollections.observableArrayList(model.getLeaseList());
        table.setItems(LeaseObservableList);

        tenantIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(((Lease) cellData.getValue()).getTenant().getTenantId()).asObject());
        unitIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(((Lease) cellData.getValue()).getUnit().getID()).asObject());
        startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Lease) cellData.getValue()).getStartDate().toString()));
        endDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(((Lease) cellData.getValue()).getEndDate().toString()));
        rentColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(((Lease) cellData.getValue()).getRent()).asObject());
        isRentedColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(((Lease) cellData.getValue()).monthPaid()).asObject());

        table.refresh();
    }
}
