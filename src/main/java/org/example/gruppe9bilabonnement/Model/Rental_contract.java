package org.example.gruppe9bilabonnement.Model;

import java.time.LocalDate;

/**
 * Rental Contract class used to create rental contract objects
 * @Author Jonas Jakobsen
 */
public class Rental_contract {
    private int id_rental_contract;
    private int customer_id;
    private int car_vehicle_id;
    private LocalDate start_date;
    private LocalDate end_date;

    private String status;

    private String irk_code;
    private String leasing_code;
    private int km_pr_month;
    private double price_per_month;

    private String payment_status;

    private String pickup_location;
    private String return_location;
    private int id_damage_report;

    public Rental_contract(){
    }

    public Rental_contract(int customer_id, int car_vehicle_id, LocalDate start_date, LocalDate end_date, String status,
                           String irk_code, String leasing_code, int km_pr_month, double price_per_month, String payment_status,
                           String pickup_location, String return_location, int id_damage_report) {
        this.customer_id = customer_id;
        this.car_vehicle_id = car_vehicle_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.irk_code = irk_code;
        this.leasing_code = leasing_code;
        this.km_pr_month = km_pr_month;
        this.price_per_month = price_per_month;
        this.payment_status = payment_status;
        this.pickup_location = pickup_location;
        this.return_location = return_location;
        this.id_damage_report = id_damage_report;
    }

    public int getId_rental_contract() {
        return id_rental_contract;
    }

    public void setId_rental_contract(int id_rental_contract) {
        this.id_rental_contract = id_rental_contract;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCar_vehicle_id() {
        return car_vehicle_id;
    }

    public void setCar_vehicle_id(int car_vehicle_id) {
        this.car_vehicle_id = car_vehicle_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIrk_code() {
        return irk_code;
    }

    public void setIrk_code(String irk_code) {
        this.irk_code = irk_code;
    }

    public String getLeasing_code() {
        return leasing_code;
    }

    public void setLeasing_code(String leasing_code) {
        this.leasing_code = leasing_code;
    }

    public int getKm_pr_month() {
        return km_pr_month;
    }

    public void setKm_pr_month(int km_pr_month) {
        this.km_pr_month = km_pr_month;
    }

    public double getPrice_per_month() {
        return price_per_month;
    }

    public void setPrice_per_month(double price_per_month) {
        this.price_per_month = price_per_month;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getReturn_location() {
        return return_location;
    }

    public void setReturn_location(String return_location) {
        this.return_location = return_location;
    }

    public int getId_damage_report() {
        return id_damage_report;
    }

    public void setId_damage_report(int id_damage_report) {
        this.id_damage_report = id_damage_report;
    }
}
