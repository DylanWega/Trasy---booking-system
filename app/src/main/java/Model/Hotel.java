package Model;

public class Hotel {
    String hotel_id;
    String hotelName;
    String check_in;
    String check_out;
    String country;
    String city;
    String address;
    String price;
    int guest;
    String rating;

    public Hotel(){

    }

    public Hotel(String hotel_id, String hotelName, String check_in, String check_out, String country, String city, String address, String price, int guest, String rating) {
        this.hotel_id = hotel_id;
        this.hotelName = hotelName;
        this.check_in = check_in;
        this.check_out = check_out;
        this.country = country;
        this.city = city;
        this.address = address;
        this.price = price;
        this.guest = guest;
        this.rating = rating;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
