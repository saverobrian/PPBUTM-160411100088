package com.terset.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText LoginUserName;
    EditText LoginPassword;
    Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginUserName = (EditText) findViewById(R.id.LoginUserName);
        LoginPassword = (EditText) findViewById(R.id.LoginPassword);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginUserName.getText().toString().equals("Brian Savero") && LoginPassword.getText().toString().equals("160411100088")){
                    Toast.makeText(getBaseContext(), "bisa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Activity2.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "ga bisa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
