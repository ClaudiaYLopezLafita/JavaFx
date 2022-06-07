package com.example.project.javafxp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Model class for an Employee
 */
public class Employees{
/*
usar Propiedades para todos los atributos de un clase usada como modelo.
Una Propiedad permite, entre otras cosas, recibir notificaciones automáticamente cuando
el valor de una variable cambia.
Esto ayuda a mantener sincronizados la vista y los datos.
 */
    private IntegerProperty employeeNumber;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty extension;
    private StringProperty email;
    private StringProperty officeCode;
    private IntegerProperty reportTo;
    private StringProperty title;

    public Employees() {
    }

    public Employees(Integer employeeNumber, String lastName, String firstName,
                     String extension, String email, String officeCode,
                     Integer reportTo, String title) {
        this.employeeNumber =  new SimpleIntegerProperty(employeeNumber);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.extension = new SimpleStringProperty(extension);
        this.email = new SimpleStringProperty(email);
        this.officeCode = new SimpleStringProperty(officeCode);
        this.reportTo =  new SimpleIntegerProperty(reportTo);
        this.title = new SimpleStringProperty(title);
    }

    public Employees getEmployee (ObservableList<String> olEmployees)
    {
        Employees emp = new Employees();
        int i = 0;

        for (String str : olEmployees)
        {
            switch (i) {
                case 0: {
                    if(str != null && !str.isBlank())
                        emp.employeeNumber = new SimpleIntegerProperty(Integer.parseInt(str));

                    break;
                }
                case 1:{
                    emp.lastName = new SimpleStringProperty(str);
                    break;
                }
                case 2:{
                    emp.firstName = new SimpleStringProperty(str);
                    break;
                }
                case 3:{
                    emp.extension = new SimpleStringProperty(str);
                    break;
                }
                case 4:{
                    emp.email = new SimpleStringProperty(str);
                    break;
                }
                case 5:{
                    emp.officeCode = new SimpleStringProperty(str);
                    break;
                }
                case 6:{
                    if(str != null && !str.isBlank())
                        emp.reportTo = new SimpleIntegerProperty(Integer.parseInt(str));
                    break;
                }
                case 7:{
                    emp.title = new SimpleStringProperty(str);
                    break;
                }
            }
            i++;
        }
        return emp;
    }

    public int getEmployeeNumber() {
        return employeeNumber.get();
    }

    // Para añadir el Integer get al TableCol del TableView como propiedad tiene que devolver
    // un ObservableValue<Integer> y se devuelve un SimpleInteerProperty (al cual hay que pasarle un int) y finalmente
    // llamar a asObject
    public ObservableValue<Integer> employeeNumberProperty() {
        return new SimpleIntegerProperty(getEmployeeNumber()).asObject();
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public String getLastName() {
        return lastName.get();
    }

    // Para añadir el String get al TableCol del TableView como propiedad tiene que devolver un StringProperty y no se pone el get()
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getExtension() {
        return extension.get();
    }

    public StringProperty extensionProperty() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension.set(extension);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getOfficeCode() {
        return officeCode.get();
    }

    public StringProperty officeCodeProperty() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode.set(officeCode);
    }

    public int getReportTo() {
        return reportTo.get();
    }

    public ObservableValue<Integer> reportToProperty() {
        return new SimpleIntegerProperty(reportTo.get()).asObject();
    }

    public void setReportTo(int reportTo) {
        this.reportTo.set(reportTo);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public static Employees[] dataEmployee(){
        Connection connection = ConnectionDB.getConnection();
        Employees[] employees = null;
        String sql = "Select * From employees";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            int numEmployees=0;
            if (rs.next()){

                Integer employeeNumber = rs.getInt(1);
                String lastName = rs.getString(2);
                String firstName = rs.getString(3);
                String extension = rs.getString(4);
                String email = rs.getString(5);
                String officeCode = rs.getString(6);
                Integer reportTo = rs.getInt(7);
                String title = rs.getString(8);

                employees[numEmployees] = new Employees(employeeNumber, lastName, firstName, extension,
                        email, officeCode, reportTo, title);
                numEmployees++;
            }

        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

        return employees;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeNumber=" + employeeNumber +
                ", lastName=" + lastName +
                ", firstName=" + firstName +
                ", extension=" + extension +
                ", email=" + email +
                ", officeCode=" + officeCode +
                ", reportTo=" + reportTo +
                ", title=" + title +
                '}'+'\n';
    }
}
