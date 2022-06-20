package com.example.project.javafxp;

public class Office {

    private String code;
    private String city;
    private String phone;
    private String address1;
    private String address2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;

    public Office(String code, String city, String phone, String address1, String address2,
                  String state, String country, String postalCode, String territory) {
        this.code = code;
        this.city = city;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.territory = territory;
    }

    public Office() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "Office{" +
                "code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", territory='" + territory + '\'' +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Office office = (Office) o;

        if (!code.equals(office.code)) return false;
        if (!city.equals(office.city)) return false;
        if (!phone.equals(office.phone)) return false;
        if (!address1.equals(office.address1)) return false;
        if (!address2.equals(office.address2)) return false;
        if (!state.equals(office.state)) return false;
        if (!country.equals(office.country)) return false;
        if (!postalCode.equals(office.postalCode)) return false;
        return territory.equals(office.territory);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address1.hashCode();
        result = 31 * result + address2.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + territory.hashCode();
        return result;
    }
}
