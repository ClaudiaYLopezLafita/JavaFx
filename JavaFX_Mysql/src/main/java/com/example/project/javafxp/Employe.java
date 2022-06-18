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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employe employe = (Employe) o;

        if (empNum != employe.empNum) return false;
        if (boss != employe.boss) return false;
        if (!lastName.equals(employe.lastName)) return false;
        if (!firstName.equals(employe.firstName)) return false;
        if (!extension.equals(employe.extension)) return false;
        if (!email.equals(employe.email)) return false;
        if (!officeCode.equals(employe.officeCode)) return false;
        return title.equals(employe.title);
    }

    @Override
    public int hashCode() {
        int result = empNum;
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + extension.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + officeCode.hashCode();
        result = 31 * result + boss;
        result = 31 * result + title.hashCode();
        return result;
    }
}
