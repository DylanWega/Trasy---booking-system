package Model;

import java.sql.Date;
import java.sql.Time;

public class Flight {
    String flight_no;
    Date depDate;
    Time depTime;
    String depCountry;
    String arrivalCountry;
    Date arrivalDate;
    Time arrivalTime;
    Double price;

    public Flight(){

    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Time getDepTime() {
        return depTime;
    }

    public void setDepTime(Time depTime) {
        this.depTime = depTime;
    }

    public String getDepCountry() {
        return depCountry;
    }

    public void setDepCountry(String depCountry) {
        this.depCountry = depCountry;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Flight(String flight_no, Date depDate, Time depTime, String depCountry, String arrivalCountry, Date arrivalDate, Time arrivalTime, Double price) {
        this.flight_no = flight_no;
        this.depDate = depDate;
        this.depTime = depTime;
        this.depCountry = depCountry;
        this.arrivalCountry = arrivalCountry;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;


    }
}
