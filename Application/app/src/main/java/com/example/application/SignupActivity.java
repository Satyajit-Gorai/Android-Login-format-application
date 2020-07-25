package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextUseremail;
    EditText mTextpassword;
    EditText mTextcnfpassword;
    Button mButtonregister;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);
        mTextUseremail = (EditText)findViewById(R.id.edittext_new_useremail);
        mTextUsername = (EditText)findViewById(R.id.edittext_new_username);
        mTextpassword = (EditText)findViewById(R.id.edittext_new_password);
        mTextcnfpassword = (EditText)findViewById(R.id.edittext_new_cnf_password);
        mButtonregister = (Button) findViewById(R.id.button_register);


        mButtonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  mTextUseremail.getText().toString().trim();
                Boolean chkml = android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches();
                if (chkml == true) {
                    String password =  mTextpassword.getText().toString().trim();
                    String cnf_password =  mTextcnfpassword.getText().toString().trim();

                    if(password.equals(cnf_password)) {
                        long val = db.AddUser(user,password);
                        if(val > 0){
                            Toast.makeText(SignupActivity.this,"You Have Been Registered... Enjoy!!!",Toast.LENGTH_SHORT).show();
                            Intent movetologin = new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(movetologin);
                        }

                        else {

                            Toast.makeText(SignupActivity.this,"oops!!! Registration Error!!!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    else {
                        Toast.makeText(SignupActivity.this,"Password is not matching!!!",Toast.LENGTH_SHORT).show();

                    }

                }
                else {
                    Toast.makeText(SignupActivity.this, "Please Enter a valid email address.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

