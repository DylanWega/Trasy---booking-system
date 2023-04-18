package com.example.trasy;

import java.sql.Date;
import java.sql.Time;

public class Car {
    String carReg;
    String make;
    String model;
    String miles;
    String transmission;
    String fuel;
    String color;
    Double price;
    Time drop_off_time;
    Time pick_up_time;
    Date drop_off_date;
    Date pick_up_date;

    public Car(){

    }

    public Car(String carReg, String make, String model, String miles, String transmission, String fuel, String color, Double price, Time drop_off_time, Time pick_up_time, Date drop_off_date, Date pick_up_date) {
        this.carReg = carReg;
        this.make = make;
        this.model = model;
        this.miles = miles;
        this.transmission = transmission;
        this.fuel = fuel;
        this.color = color;
        this.price = price;
        this.drop_off_time = drop_off_time;
        this.pick_up_time = pick_up_time;
        this.drop_off_date = drop_off_date;
        this.pick_up_date = pick_up_date;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public Time getDrop_off_time() {
        return drop_off_time;
    }

    public void setDrop_off_time(Time drop_off_time) {
        this.drop_off_time = drop_off_time;
    }

    public Time getPick_up_time() {
        return pick_up_time;
    }

    public void setPick_up_time(Time pick_up_time) {
        this.pick_up_time = pick_up_time;
    }

    public Date getDrop_off_date() {
        return drop_off_date;
    }

    public void setDrop_off_date(Date drop_off_date) {
        this.drop_off_date = drop_off_date;
    }

    public Date getPick_up_date() {
        return pick_up_date;
    }

    public void setPick_up_date(Date pick_up_date) {
        this.pick_up_date = pick_up_date;
    }
}

