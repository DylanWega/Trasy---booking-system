package Model;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    String booking_id;
    String customer_id;
    String carReg;
    String hotel_id;
    String flight_no;
    Date depDate;
    Time depTime;
    String FirstName;
    String LastName;
    Double totalPrice;
    String check_in;
    String check_out;
    Time drop_off_time;
    Time pick_up_time;
    Date drop_off_date;
    Date pick_up_date;

    public Booking(){

    }

    public Booking(String booking_id, String customer_id, String carReg, String hotel_id, String flight_no, Date depDate, Time depTime, String firstName, String lastName, Double totalPrice, String check_in, String check_out, Time drop_off_time, Time pick_up_time, Date drop_off_date, Date pick_up_date) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.carReg = carReg;
        this.hotel_id = hotel_id;
        this.flight_no = flight_no;
        this.depDate = depDate;
        this.depTime = depTime;
        FirstName = firstName;
        LastName = lastName;
        this.totalPrice = totalPrice;
        this.check_in = check_in;
        this.check_out = check_out;
        this.drop_off_time = drop_off_time;
        this.pick_up_time = pick_up_time;
        this.drop_off_date = drop_off_date;
        this.pick_up_date = pick_up_date;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
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
