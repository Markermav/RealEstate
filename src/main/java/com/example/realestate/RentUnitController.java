package com.example.realestate;

import com.example.db.AccountingSystem;
import com.example.model.Lease;
import com.example.model.Property;
import com.example.model.Tenant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static java.lang.Integer.parseInt;

public class RentUnitController implements Initializable {

    @FXML
    private Button addTenant;

    @FXML
    private Button addUnit;

    @FXML
    private Button back;

    @FXML
    private DatePicker leaseEndDate;

    @FXML
    private DatePicker leaseStartDate;

    @FXML
    private TextField propertyID;

    @FXML
    private TextField rentPrice;

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
    private TextField tenantID;

    @FXML
    private ToggleButton noToggle;

    @FXML
    private ToggleButton yesToggle;
    private ToggleGroup toggleGroup;



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

    @FXML
    void bOnSubmit(ActionEvent event) {
        String[] rentDataArray = {
                tenantID.getText(),
                propertyID.getText()
        };
        CompletableFuture.runAsync( () -> {
            AccountingSystem model = AccountingSystem.getInstance();
            int propertyId = parseInt(rentDataArray[1]);
            Property property = (Property) model.getProperty(propertyId);
            Tenant tenant = model.getTenant(Integer.parseInt(tenantID.getText()));
            boolean available = model.canLease(property);
            if(!available) {
                ToggleButton selectedToggleButton = (ToggleButton) toggleGroup.getSelectedToggle();
                System.out.println(selectedToggleButton.getText());
                if(selectedToggleButton.getText().compareTo("Yes") == 0) {
                    model.addSubscription(tenant, property);
                }
            }
            if(available) {
                model.addLease(new Lease(tenant,property,leaseStartDate.getValue(), leaseEndDate.getValue(),Double.parseDouble(rentPrice.getText())));
            }
            Platform.runLater(() -> showSuccessMessage());
        });


    }

    @FXML
    void resetForm() {
        tenantID.clear();
        propertyID.clear();
        leaseStartDate.setValue(null);
        leaseEndDate.setValue(null);
        rentPrice.clear();

    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Lease created successfully!");
        alert.initOwner(submit.getScene().getWindow());
        alert.showAndWait();
        resetForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        yesToggle.setToggleGroup(toggleGroup);
        noToggle.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(yesToggle);
    }
}

