package com.example.project.javafxp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Controller {

    @FXML
    private TextField txtName; //Campo "Nombre" del formulario
    @FXML
    private PasswordField txtPassword; //Campo "Password" del formulario

    private TableView<Employees> tvEmployees;
    @FXML
    private TableView tableview;

    private ObservableList<Employees> data;

    @FXML
    private TableColumn<Employees, Integer> employeeNumberCol;
    @FXML
    private TableColumn<Employees, String> lastNameCol;
    @FXML
    private TableColumn<Employees, String> firstNameCol;
    @FXML
    private TableColumn<Employees, String> extensionCol;
    @FXML
    private TableColumn<Employees, String> emailCol;
    @FXML
    private TableColumn<Employees, String> officeCodeCol;
    @FXML
    private TableColumn<Employees, Integer> reportsToCol;
    @FXML
    private TableColumn<Employees, String> titleCol;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * Supuestamente deberia inicializar las columnas. Comentado porque casca con NullPointerException
     */

    /*@FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        employeeNumberCol.setCellValueFactory(cellData -> cellData.getValue().employeeNumberProperty());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        extensionCol.setCellValueFactory(cellData -> cellData.getValue().extensionProperty());
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        officeCodeCol.setCellValueFactory(cellData -> cellData.getValue().officeCodeProperty());
        reportsToCol.setCellValueFactory(cellData -> cellData.getValue().reportToProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    }*/

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
            builDataEmployees2();

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void builDataEmployees1(){
        Connection connection = ConnectionDB.getConnection();
        data = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM employees;";
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory(
                        (Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                                param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void builDataEmployees2(){
        Connection connection = ConnectionDB.getConnection();
        data = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM employees;";
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++) {
                    //Iterate Column
                    row.add(rs.getString(k));
                }
                System.out.println("Row [1] added " + row);
                Employees emp = new Employees();
                emp = emp.getEmployee(row);
                //data.add(row);
                data.add(emp);
            }
            //FINALLY ADDED TO TableView. CASCA porque dice que esta a null
            tvEmployees.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void btOfficesClick(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    Controller.class.getResource("offices.fxml"));
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