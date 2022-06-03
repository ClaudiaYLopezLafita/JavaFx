package com.example.project.javafxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TextField txtName; //Campo "Nombre" del formulario
    @FXML
    private PasswordField txtPassword; //Campo "Password" del formulario

    public void btLimpiarClick(ActionEvent actionEvent) {
        txtPassword.setText("");
        txtName.setText("");
        txtName.requestFocus();
    }

    public void btEnviarClick(ActionEvent actionEvent) {

        String nombre = txtName.getText();
        String password = txtPassword.getText();

        Alert alert = null;

        if (nombre.isEmpty() || password.isEmpty()){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos Insuficientes...");
            alert.setHeaderText("Faltan datos por introducir");
            alert.setContentText("Compruebe que el nombre y la contraseña estan rellenos. ");
            txtName.requestFocus();
        } else if ( !nombre.equals("Claudia") || !password.equals("123456")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos...");
            alert.setHeaderText("El nombre y/o la contraseña son incorrectas.");
            alert.setContentText("Compruebe que los datos son correctos.");
            txtName.requestFocus();
        } else {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(
                        Controller.class.getResource("selectionTables.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Selección de tablas");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) actionEvent.getSource()).getScene().getWindow() );
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        alert.showAndWait();
    }

    public void btSalirClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar aplicación...");
        alert.setHeaderText(null);
        alert.setContentText("¿Confirma cerrar la aplicación?");
        Optional<ButtonType> result = alert.showAndWait();
        //Si se ha pulsado el botón "Aceptar"
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else { //Si se ha pulsado el botón "Cancelar" enfocamos en el TextField Nombre
            txtName.requestFocus();
        }
    }

    public void btEmployeesClick(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    Controller.class.getResource("employees.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Employees");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) actionEvent.getSource()).getScene().getWindow() );
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btOfficesClick(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    Controller.class.getResource("selectionTables.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Offices");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) actionEvent.getSource()).getScene().getWindow() );
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}