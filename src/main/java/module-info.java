module com.example.realestate {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.realestate to javafx.fxml;
    exports com.example.realestate;
}