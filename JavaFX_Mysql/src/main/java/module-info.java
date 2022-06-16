module com.example.project.javafxp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project.javafxp to javafx.fxml;
    exports com.example.project.javafxp;
//    exports com.example.project.javafxp.Controller;
//    opens com.example.project.javafxp.Controller to javafx.fxml;
//    exports com.example.project.javafxp.Model;
//    opens com.example.project.javafxp.Model to javafx.fxml;
//    exports com.example.project.javafxp;
}