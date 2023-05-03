package com.example.trasy.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasy.R;

import java.sql.Date;
import java.util.List;

import Model.Flight;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {

    private List<Flight> theFlightList;

    public postAdapter(List<Flight> theFlightList) {
        this.theFlightList = theFlightList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemflight, parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.origin.setText(theFlightList.get(position).getDepCountry());
        holder.arrive.setText(theFlightList.get(position).getArrivalCountry());
        holder.flightPrice.setText(theFlightList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return theFlightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView origin, arrive, flightPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            origin = itemView.findViewById(R.id.txtOrigin);
            arrive = itemView.findViewById(R.id.txtArrive);
            flightPrice = itemView.findViewById(R.id.txtFlightPrice);



        }
    }
}
