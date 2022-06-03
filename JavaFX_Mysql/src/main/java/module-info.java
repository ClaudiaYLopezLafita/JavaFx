module com.example.project.javafxp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project.javafxp to javafx.fxml;
    exports com.example.project.javafxp;
}