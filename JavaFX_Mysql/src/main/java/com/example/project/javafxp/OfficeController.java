package com.example.project.javafxp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeController {

    @FXML
    private TableView<Office> tvOffices;
    @FXML
    private TableColumn officeCodeCol;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn address1Col;
    @FXML
    private TableColumn address2Col;
    @FXML
    private TableColumn stateCol;
    @FXML
    private TableColumn countryCol;
    @FXML
    private TableColumn postalCodeCol;
    @FXML
    private TableColumn territoryCol;

    private ObservableList<Office> observableList;

    @FXML
    private TextField addCode;
    @FXML
    private TextField addCity;
    @FXML
    private TextField addPhone;
    @FXML
    private TextField addAddress1;
    @FXML
    private TextField addAddress2;
    @FXML
    private TextField addState;
    @FXML
    private TextField addCountry;
    @FXML
    private TextField addPC;
    @FXML
    private TextField addTerritory;

    public static List<Office> getOffice(){

        Connection connection = ConnectionDB.getConnection();
        List<Office> list = new ArrayList<>();

        String sql = "SELECT o.*\n" +
                "FROM offices o;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Office office = new Office();

                office.setCode(rs.getString(1));
                office.setCity(rs.getString(2));
                office.setPhone(rs.getString(3));
                office.setAddress1(rs.getString(4));
                office.setAddress2(rs.getString(5));
                office.setState(rs.getString(6));
                office.setCountry(rs.getString(7));
                office.setPostalCode(rs.getString(8));
                office.setTerritory(rs.getString(9));

                list.add(office);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void btCargarClick(ActionEvent actionEvent) {

        observableList = FXCollections.observableList(getOffice());
        this.tvOffices.setItems(observableList);

        this.officeCodeCol.setCellValueFactory(new PropertyValueFactory("Code"));
        this.cityCol.setCellValueFactory(new PropertyValueFactory("City"));
        this.phoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        this.address1Col.setCellValueFactory(new PropertyValueFactory("Address1"));
        this.address2Col.setCellValueFactory(new PropertyValueFactory("Address2"));
        this.stateCol.setCellValueFactory(new PropertyValueFactory("State"));
        this.countryCol.setCellValueFactory(new PropertyValueFactory("Country"));
        this.postalCodeCol.setCellValueFactory(new PropertyValueFactory("PostalCode"));
        this.territoryCol.setCellValueFactory(new PropertyValueFactory("Territory"));

    }

    public void btNuevoClick(ActionEvent actionEvent) {

        try{
            insertOffice();
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

    private boolean insertOffice() {

        Connection connection = ConnectionDB.getConnection();

        String code = addCode.getText();
        String city = addCity.getText();
        String phone = addPhone.getText();
        String address1 = addAddress1.getText();
        String address2 = addAddress2.getText();
        String state = addState.getText();
        String country = addCountry.getText();
        String postalCode = addPC.getText();
        String territory = addTerritory.getText();

        Office office = new Office(code, city, phone, address1, address2, state, country, postalCode, territory);

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO offices VALUES(?,?,?,?,?,?,?,?,?);";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, office.getCode());
            ps.setString(2, office.getCity());
            ps.setString(3, office.getPhone());
            ps.setString(4, office.getAddress1());
            ps.setString(5, office.getAddress2());
            ps.setString(6, office.getState());
            ps.setString(7, office.getCountry());
            ps.setString(8, office.getPostalCode());
            ps.setString(9, office.getTerritory());

            ps.executeUpdate();
            connection.commit();

            tvOffices.getItems().add(office);
            System.out.println("Oficina insertada");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btModificarClick(ActionEvent actionEvent) {

        Connection connection = ConnectionDB.getConnection();

        String code = tvOffices.getSelectionModel().getSelectedItem().getCode();

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE offices SET city = '"+addCity.getText()+
                    "', phone = '"+ addPhone.getText()+
                    "', addressLine1 = '"+addAddress1.getText()+
                    "', addressLine2 = '"+addAddress2.getText()+
                    "', state = '"+addState.getText()+
                    "', country = '"+addCountry.getText()+
                    "', postalCode = '"+addPC.getText()+
                    "', territory = '" +addTerritory.getText()+
                    "' WHERE officeCode = "+code+"";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
            connection.commit();
            btCargarClick(actionEvent);
            tvOffices.refresh();
            System.out.println("Oficina actualizada");

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

    public void btBorrarClick(ActionEvent actionEvent) {

        Connection connection = ConnectionDB.getConnection();

        String code = tvOffices.getSelectionModel().getSelectedItem().getCode();
        String sql = "DELETE FROM offices WHERE officeCode = ? ;";

        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,code);
            ps.executeUpdate();
            connection.commit();

            System.out.println("Oficina Borrada");

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
        tvOffices.getItems().removeAll(tvOffices.getSelectionModel().getSelectedItem());

    }
}
