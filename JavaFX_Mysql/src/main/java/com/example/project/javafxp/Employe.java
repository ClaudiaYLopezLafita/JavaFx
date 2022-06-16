package com.example.project.javafxp;

public class Employe {

    private int empNum;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private int boss;
    private String title;

    public Employe(int empNum, String lastName, String firstName, String extension, String email,
                   String officeCode, int boss, String title) {
        this.empNum = empNum;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.boss = boss;
        this.title = title;
    }

    public Employe() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employe employees = (Employe) o;

        return empNum == employees.empNum;
    }

    @Override
    public int hashCode() {
        return empNum;
    }

    public int getEmpNum() {
        return empNum;
    }

    public void setEmpNum(int empNum) {
        this.empNum = empNum;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "empNum=" + empNum +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", extension='" + extension + '\'' +
                ", email='" + email + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", boss=" + boss +
                ", title='" + title + '\'' +
                '}'+'\n';
    }
}
