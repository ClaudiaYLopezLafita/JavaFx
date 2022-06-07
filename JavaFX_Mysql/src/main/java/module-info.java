module com.example.project.javafxp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project.javafxp to javafx.fxml;
    exports com.example.project.javafxp;
}