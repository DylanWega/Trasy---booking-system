package com.example.trasy.data;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasy.DatabaseHelper;
import com.example.trasy.Homepage;
import com.example.trasy.MainActivity;
import com.example.trasy.R;
import com.example.trasy.flightList;

import java.sql.Date;
import java.util.List;

import Model.Flight;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {


    //create instance of a session
    private SessionManager session;
    private final Context context;
    private List<Flight> theFlightList;
    //create instance of databaseHelper
    DatabaseHelper myDB;
    
    //constructor for the postAdaper
    public postAdapter(Context context, List<Flight> theFlightList) {
        this.context = context;
        this.theFlightList = theFlightList;
        myDB = new DatabaseHelper(context);
        session = new SessionManager(context);
    }


    @NonNull
    @Override
    public postAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemflight, parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight = theFlightList.get(position);
        holder.origin.setText(flight.getDepCountry());
        holder.arrive.setText(flight.getArrivalCountry());
        holder.flightPrice.setText("£ " + flight.getPrice());

        //set onclick listener for book
        holder.bookFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailValue = session.getEmail("email");
                Boolean insert = myDB.InsertFlight(flight.getDepCountry(),flight.getArrivalCountry(), emailValue , flight.getPrice());
                Boolean insertBookings = myDB.InsertBookings(emailValue);
                if(insert && insertBookings == true) {
                    Toast.makeText(context, "Flight Booked Successfully", Toast.LENGTH_SHORT).show();
                    //show home page after creation of account
                    Intent intent = new Intent(context, Homepage.class);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Booking fail", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return theFlightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView origin, arrive, flightPrice;
        Button bookFlight;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            origin = itemView.findViewById(R.id.txtOrigin);
            arrive = itemView.findViewById(R.id.txtArrive);
            flightPrice = itemView.findViewById(R.id.txtFlightPrice);
            bookFlight = itemView.findViewById(R.id.bookBtn);
        }



    }

    public interface OnBookClickListener{
        void onBookClick(int position);
    }
}
