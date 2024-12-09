package org.example.gruppe9bilabonnement.Model;

//TODO
public class Damage {

    private int id_damage;
    private int id_damage_report;
    private String type;
    private String placement;
    private double price;

    public Damage() {
    }

    public int getId_damage() {
        return id_damage;
    }

    public void setId_damage(int id_damage) {
        this.id_damage = id_damage;
    }

    public int getId_damage_report() {
        return id_damage_report;
    }

    public void setId_damage_report(int id_damage_report) {
        this.id_damage_report = id_damage_report;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
