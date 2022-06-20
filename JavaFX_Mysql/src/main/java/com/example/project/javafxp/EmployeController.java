package com.example.project.javafxp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeController {

    @FXML
    private TableView<Employe> tvEmployees;
    @FXML
    private TableColumn employeeNumberCol;
    @FXML
    private TableColumn lastNameCol;
    @FXML
    private TableColumn firstNameCol;
    @FXML
    private TableColumn extensionCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn officeCodeCol;
    @FXML
    private TableColumn bossCol;
    @FXML
    private TableColumn titleCol;

    private ObservableList<Employe> observableList;

    @FXML
    private TextField addNumEmp;
    @FXML
    private TextField addLastName;
    @FXML
    private TextField addFirstName;
    @FXML
    private TextField addExtension;
    @FXML
    private TextField addEmail;
    @FXML
    private TextField addOffice;
    @FXML
    private TextField addBoss;
    @FXML
    private TextField addTitle;

    public static List<Employe> getEmployees(){

        Connection connection = ConnectionDB.getConnection();
        List<Employe> list = new ArrayList<>();

        String sql = "SELECT e.*\n" +
                "FROM employees e;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Employe emp = new Employe();

                emp.setEmpNum(rs.getInt(1));
                emp.setLastName(rs.getString(2));
                emp.setFirstName(rs.getString(3));
                emp.setExtension(rs.getString(4));
                emp.setEmail(rs.getString(5));
                emp.setOfficeCode(rs.getString(6));
                emp.setBoss(rs.getInt(7));
                emp.setTitle(rs.getString(8));

                list.add(emp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void btCargarClick(ActionEvent actionEvent) {

        observableList = FXCollections.observableList(getEmployees());
        this.tvEmployees.setItems(observableList);

        this.employeeNumberCol.setCellValueFactory(new PropertyValueFactory("empNum"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.extensionCol.setCellValueFactory(new PropertyValueFactory("extension"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        this.officeCodeCol.setCellValueFactory(new PropertyValueFactory("officeCode"));
        this.bossCol.setCellValueFactory(new PropertyValueFactory("boss"));
        this.titleCol.setCellValueFactory(new PropertyValueFactory("title"));
    }
    public void btNuevoClick(ActionEvent actionEvent) {

        try{
            insertEmployee();
            btCargarClick(actionEvent);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public boolean insertEmployee(){

        Connection connection = ConnectionDB.getConnection();

        int numEmp = Integer.parseInt(addNumEmp.getText());
        String lastName = addLastName.getText();
        String firstName = addFirstName.getText();
        String extension = addExtension.getText();
        String email = addEmail.getText();
        String office = addOffice.getText();
        int boss = Integer.parseInt(addBoss.getText());
        String title = addTitle.getText();

        Employe employe = new Employe(numEmp,lastName,firstName,extension,email,office,boss,title);

        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?);";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,employe.getEmpNum());
            ps.setString(2,employe.getLastName());
            ps.setString(3,employe.getFirstName());
            ps.setString(4,employe.getExtension());
            ps.setString(5,employe.getEmail());
            ps.setString(6,employe.getOfficeCode());
            ps.setInt(7,employe.getBoss());
            ps.setString(8,employe.getTitle());

            ps.executeUpdate();
            connection.commit();

            System.out.println("Empleado insertado");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void btBorrarClick(ActionEvent actionEvent) {

        Connection connection = ConnectionDB.getConnection();

        int numEmp = tvEmployees.getSelectionModel().getSelectedItem().getEmpNum();

        String sql = "DELETE FROM employees WHERE employeeNumber= ?";

        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,numEmp);
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("error: "+e.getMessage());
            try {
                if (connection!=null) {
                    connection.rollback();
                    System.out.println("No se modifico la base de datos");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        tvEmployees.getItems().removeAll(tvEmployees.getSelectionModel().getSelectedItem());

    }
    public void btModificarClick(ActionEvent actionEvent) {

        Connection connection = ConnectionDB.getConnection();

        int numEmp = tvEmployees.getSelectionModel().getSelectedItem().getEmpNum();

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE employees SET lastName = '"+addLastName.getText()+
                    "', firstName = '"+ addFirstName.getText()+
                    "', extension = '"+addExtension.getText()+
                    "', email = '"+addEmail.getText()+
                    "', officeCode = '"+addOffice.getText()+
                    "', reportsTo = '"+Integer.parseInt(addBoss.getText())+
                    "', jobTitle = '"+addTitle.getText()+
                    "' WHERE employeeNumber = "+numEmp+"";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
            connection.commit();
            btCargarClick(actionEvent);
            System.out.println("Empleado actualizado");

        } catch (SQLException e) {
            System.out.println("error: "+e.getMessage());
            try {
                if (connection!=null) {
                    connection.rollback();
                    System.out.println("No se modifico la base de datos");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
