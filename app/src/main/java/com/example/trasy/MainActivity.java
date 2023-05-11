package com.example.trasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trasy.data.CreateAccountActivity;
import com.example.trasy.data.SessionManager;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText email, password;
    Button signup, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //create instance of a session
        SessionManager session = new SessionManager(getApplicationContext());

        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        signup = findViewById(R.id.btnSignup);
        login = (Button) findViewById(R.id.loginBtn);

        DatabaseHelper DB = new DatabaseHelper(this);
         signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pwd = password.getText().toString();
                // check if fields are empty and throws msg if empty
                if(em.equals("") || pwd.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all fields",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkEmailnPassword(em,pwd);
                    if(checkUser == true){
                        Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        //create session upon login
                        session.setLoggedIn("email",em);
                        Intent intent = new Intent(getApplicationContext(), Homepage.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
                        System.out.println("Invalid Credentials");
                    }
                }
            }
        });
    }
}