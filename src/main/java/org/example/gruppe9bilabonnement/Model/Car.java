package org.example.gruppe9bilabonnement.Model;

/**
 * Car class used to create Car objects from the mysql database using RowMapper to be displayed in the html code
 * Author - Hans Erritzøe
 */
public class Car {

    private int id_vehicle;
    private String VIN;
    private String brand;
    private String model;
    private int year;
    private String owner;
    private int km_driven;
    private double km_price;
    private double monthly_price;
    private boolean available;

    public Car() {
    }

    public int getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(int km_driven) {
        this.km_driven = km_driven;
    }

    public double getKm_price() {
        return km_price;
    }

    public void setKm_price(double km_price) {
        this.km_price = km_price;
    }

    public double getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(double monthly_price) {
        this.monthly_price = monthly_price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
