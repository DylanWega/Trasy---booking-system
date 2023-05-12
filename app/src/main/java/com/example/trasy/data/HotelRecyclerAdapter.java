package com.example.trasy.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasy.DatabaseHelper;
import com.example.trasy.Homepage;
import com.example.trasy.R;
import com.example.trasy.SearchHotel;

import java.util.List;

import Model.Hotel;

// HotelViewHolder is a public internal class
public class HotelRecyclerAdapter extends RecyclerView.Adapter<HotelRecyclerAdapter.HotelViewHolder>{

    private Context mContext;
    private final List<Hotel> Hotels;
    //create instance of databaseHelper
    DatabaseHelper myDB;
    //create instance of a session
    private SessionManager session;

    public HotelRecyclerAdapter(Context context, List<Hotel> hotels) {
        this.mContext = context;
        this.Hotels = hotels;
        myDB = new DatabaseHelper(context);
        session = new SessionManager(context);
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eachRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_hotel_row, parent, false);
        System.out.println("*********************** It came inside the onCreate viewHolder");
        return new HotelViewHolder(eachRow);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder hotelViewHolder, int position) {
        System.out.println("############### the content of hotelViewHolder is " + hotelViewHolder);
        System.out.println("Before the set******************** " + Hotels.get(position).getHotelName());
        String thisHotelName = Hotels.get(position).getHotelName();
        Hotel hotel = Hotels.get(position);
        hotelViewHolder.hotelName.setText(thisHotelName);
        String thisHotelPrice = String.valueOf(Hotels.get(position).getPrice());
        hotelViewHolder.hotelPrice.setText(thisHotelPrice);
        System.out.println("***********************It came inside the bind viewHolder");
        System.out.println("what is in the text view: " + hotelViewHolder.hotelName.getText());

        // set onClickListener to book a hotel
        hotelViewHolder.bookHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = session.getEmail("email");
                Boolean insert = myDB.InsertHotel(hotel.getHotelName(), emailValue, hotel.getPrice());
                Boolean insertBookings = myDB.InsertBookings(emailValue);
                if(insert && insertBookings == true) {
                    Toast.makeText(mContext, "Hotel Booked Successfully", Toast.LENGTH_SHORT).show();
                    //show home page after creation of account
                    Intent intent = new Intent(mContext, Homepage.class);
                    mContext.startActivity(intent);
                }
                else{
                    Toast.makeText(mContext, "Booking fail", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return Hotels.size();
    }

    // public class for the view holder
    public class HotelViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName;
        private TextView hotelPrice;

        private Button bookHotel;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.textViewHotelName);
            hotelPrice = itemView.findViewById(R.id.textViewHotelPrice);
            bookHotel = itemView.findViewById(R.id.bookHotelBtn);
        }
    }
}
