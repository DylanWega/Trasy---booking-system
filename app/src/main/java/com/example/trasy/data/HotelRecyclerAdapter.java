package com.example.trasy.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasy.R;
import com.example.trasy.SearchHotel;

import java.util.List;

import Model.Hotel;

// HotelViewHolder is a public internal class
public class HotelRecyclerAdapter extends RecyclerView.Adapter<HotelRecyclerAdapter.HotelViewHolder>{

    private Context mContext;
    private final List<Hotel> Hotels;

    public HotelRecyclerAdapter(List<Hotel> hotels) {
        this.Hotels = hotels;
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
        hotelViewHolder.hotelName.setText(thisHotelName);
        String thisHotelPrice = String.valueOf(Hotels.get(position).getPrice());
        hotelViewHolder.hotelPrice.setText(thisHotelPrice);
        System.out.println("***********************It came inside the bind viewHolder");
        System.out.println("what is in the text view: " + hotelViewHolder.hotelName.getText());
    }


    @Override
    public int getItemCount() {
        return Hotels.size();
    }

    // public class for the view holder
    public class HotelViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName;
        private TextView hotelPrice;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.textViewHotelName);
            hotelPrice = itemView.findViewById(R.id.textViewHotelPrice);
        }
    }
}
