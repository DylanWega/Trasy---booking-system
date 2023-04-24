package com.example.trasy.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trasy.DatabaseHelper;
import com.example.trasy.Homepage;
import com.example.trasy.MainActivity;
import com.example.trasy.R;

import java.sql.Date;

public class CreateAccountActivity extends AppCompatActivity {
    //declaring database helper
    DatabaseHelper databaseHelper;
    //declaring the variable
    EditText firstname, lastname, password, email, passportno, date;
    //declaring buttons
    Button addCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstname = (EditText) findViewById(R.id.txtFname);
        lastname = (EditText) findViewById(R.id.txtLname);
        password = (EditText) findViewById(R.id.txtPwd);
        email = (EditText) findViewById(R.id.txtEmailAd);
        passportno = (EditText) findViewById(R.id.txtPassportNo);
        date = (EditText) findViewById(R.id.txtDate);

        DatabaseHelper myDB = new DatabaseHelper(this);

        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values from text filed in the view/interface
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String pwd = password.getText().toString();
                String passno = passportno.getText().toString();
                String emailValue = email.getText().toString();
                String dateValue = date.getText().toString();

                //check if all fields are entered
                if(fname.equals("") || lname.equals("") || pwd.equals("") ||passno.equals("")|| emailValue.equals("") || dateValue.equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUser = myDB.checkEmail(emailValue);
                    //check of user exist
                    if (checkUser == true) {
                        Toast.makeText(CreateAccountActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                    }
                    //insert customer to the database
                        else{
                                Boolean insert = myDB.Insertdata(lname,fname,pwd,emailValue,dateValue,passno);
                                if(insert == true) {
                                    Toast.makeText(CreateAccountActivity.this, "Welcome to Tasy", Toast.LENGTH_SHORT).show();
                                    //show home page after creation of account
                                    Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(CreateAccountActivity.this, "Registration fail", Toast.LENGTH_SHORT).show();

                                }
                    }
                }
            }
        });

    }
}