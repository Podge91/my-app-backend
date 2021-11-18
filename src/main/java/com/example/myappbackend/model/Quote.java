package com.example.myappbackend.model;

import javax.persistence.*;

@Entity
@Table(name="quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String prefix;
    private String firstName;
    private String lastName;
    private String telephone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String vehicleType;
    private String engineSize;
    private String additionalDrivers;
    private String commercial;
    private String registeredState;
    private String currentValue;
    private String dateRegistered;

    public Quote(){
    }

    public Quote(String prefix, String firstName, String lastName, String telephone, String addressLine1,
                 String addressLine2, String city, String vehicleType, String engineSize, String additionalDrivers,
                 String commercial, String registeredState, String currentValue, String dateRegistered) {

        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.vehicleType = vehicleType;
        this.engineSize = engineSize;
        this.additionalDrivers = additionalDrivers;
        this.commercial = commercial;
        this.registeredState = registeredState;
        this.currentValue = currentValue;
        this.dateRegistered = dateRegistered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getAdditionalDrivers() {
        return additionalDrivers;
    }

    public void setAdditionalDrivers(String additionalDrivers) {
        this.additionalDrivers = additionalDrivers;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getRegisteredState() {
        return registeredState;
    }

    public void setRegisteredState(String registeredState) {
        this.registeredState = registeredState;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
