package com.example.android_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    // creating objects for the designing elements
    EditText etUsername, etPassword, etStdName;
    Button btnLogin;
    public static String stdName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // assigning value to the objects by finding the view by id
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etStdName = findViewById(R.id.etStdName);
        btnLogin = findViewById(R.id.btnLogin);

        // click listener event for login button
        btnLogin.setOnClickListener(this);
    }

    // the logic that will execute on the click of login button
    @Override
    public void onClick(View v) {
        // validations on the click of login button
        if(etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etStdName.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Please provide the value for required fields.", Toast.LENGTH_LONG).show();
        } else if (!(etUsername.getText().toString().equals("student1") && etPassword.getText().toString().equals("123456"))){
            Toast.makeText(this,"Invalid username or password", Toast.LENGTH_LONG).show();
        } else {
            // set the student name and redirect to new activity
            stdName = etStdName.getText().toString();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}