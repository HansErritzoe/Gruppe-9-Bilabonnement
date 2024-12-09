package org.example.gruppe9bilabonnement.Model;

import java.time.LocalDate;

//TODO
public class Damage_report {

    private int id_damage_report;
    private int car_id_vehicle;
    private double price_total;
    private LocalDate date;
    private String filled_by;
    private String payment_status;

    public Damage_report() {
    }

    public int getId_damage_report() {
        return id_damage_report;
    }

    public void setId_damage_report(int id_damage_report) {
        this.id_damage_report = id_damage_report;
    }

    public int getCar_id_vehicle() {
        return car_id_vehicle;
    }

    public void setCar_id_vehicle(int car_id_vehicle) {
        this.car_id_vehicle = car_id_vehicle;
    }

    public double getPrice_total() {
        return price_total;
    }

    public void setPrice_total(double price_total) {
        this.price_total = price_total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFilled_by() {
        return filled_by;
    }

    public void setFilled_by(String filled_by) {
        this.filled_by = filled_by;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
}
