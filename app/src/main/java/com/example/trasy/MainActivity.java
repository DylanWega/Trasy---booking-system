package com.example.trasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText email, password;
    Button signup, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        signup = (Button) findViewById(R.id.signupBtn);
        login = (Button) findViewById(R.id.loginBtn);
        DatabaseHelper DB = new DatabaseHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
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