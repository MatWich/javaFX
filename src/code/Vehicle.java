package code;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Vehicle implements Serializable {

    //pola
    String brand = "";
    String model = "";
    String name = "";
    double price = 0.0;
    Integer prod_year = 1999;
    double eng_capacity = 0.0;
    String status;
    String salonName;
    ItemCondition stan;


    public Vehicle() {
    }

    public void setStan(ItemCondition stan) {
        this.stan = stan;
    }

    public ItemCondition getStan() {
        return stan;
    }

    public String getSalonName() {
        return salonName;
    }

    public Vehicle(String name, double price, int prod_year, double eng_capacity, String status) {
        super();
        this.name = name;
        this.price = price;
        this.prod_year = prod_year;
        this.eng_capacity = eng_capacity;
        this.status = status;
        this.stan = ItemCondition.NEW;

    }

    public void setProd_year(Integer prod_year) {
        this.prod_year = prod_year;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public Vehicle(String b, String m, double p, int prod, double eng_c) {
        this.brand = b;
        this.model = m;
        this.price = p;
        this.prod_year = prod;
        this.eng_capacity = eng_c;
        this.createNames();
    }

    public String getStatus() {
        return status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    void setPrice(Integer integer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getProd_year() {
        return prod_year;
    }

    public void setProd_year(int prod_year) {
        this.prod_year = prod_year;
    }

    public double getEng_capacity() {
        return eng_capacity;
    }


//methods

    public void setEng_capacity(double eng_capacity) {
        this.eng_capacity = eng_capacity;
    }

    public void createNames() {
        this.name = this.brand + " " + this.model;
    }

    public void print() {
        System.out.println("Name: " + this.name);
        System.out.println("Price: " + this.price + " ojro");
        System.out.println("Production Year: " + this.prod_year);
        System.out.println("Engine Capacity: " + this.eng_capacity);
    }

    //@Override
    public boolean compare(Object v) {
        return (this.model.equals(((Vehicle) v).model) && (this.brand.equals(((Vehicle) v).brand)));
    }
}
