package com.example.project.javafxp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for an Employee
 */
public class Employees {
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

    public Employees(IntegerProperty employeeNumber, StringProperty lastName, StringProperty firstName,
                     StringProperty extension, StringProperty email, StringProperty officeCode,
                     IntegerProperty reportTo, StringProperty title) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.reportTo = reportTo;
        this.title = title;
    }

    public int getEmployeeNumber() {
        return employeeNumber.get();
    }

    public IntegerProperty employeeNumberProperty() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public String getLastName() {
        return lastName.get();
    }

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

    public IntegerProperty reportToProperty() {
        return reportTo;
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
