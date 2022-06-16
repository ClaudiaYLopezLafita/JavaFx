package com.example.project.javafxp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

        ObservableList<Employe> observableList = FXCollections.observableList(getEmployees());
        this.tvEmployees.setItems(observableList);

        this.employeeNumberCol.setCellValueFactory(new PropertyValueFactory("empNum"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.extensionCol.setCellValueFactory(new PropertyValueFactory("extension"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        this.bossCol.setCellValueFactory(new PropertyValueFactory("boss"));
        this.titleCol.setCellValueFactory(new PropertyValueFactory("title"));
    }
}
