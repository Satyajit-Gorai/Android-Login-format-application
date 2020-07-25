package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextpassword;
    Button mButtonLogin;
    TextView mButtonSignup;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextpassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonSignup = (TextView) findViewById(R.id.button_signup);

        mButtonSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(MainActivity.this, SignupActivity.class);
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }

        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = mTextUsername.getText().toString().trim();
                Boolean chkml = android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches();

                if (chkml == true) {
                    String password = mTextpassword.getText().toString().trim();
                    Boolean result = db.checkUser(user, password);

                    if (result == true) {
                        Toast.makeText(MainActivity.this, "Successfully logged in...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login Error!!!", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Please Enter a valid email address.", Toast.LENGTH_SHORT).show();

                }

            }

        });
    }
}
