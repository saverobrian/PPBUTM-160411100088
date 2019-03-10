package com.terset.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    EditText Act1No1Edit;
    Button Act1No1Button;
    TextView Act1No1Hasil;

    EditText Act1No2Edit1;
    EditText Act1No2Edit2;
    Button Act1No2Button;
    TextView Act1No2Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Act1No1Edit = findViewById(R.id.Act1No1Edit);
        Act1No1Button = findViewById(R.id.Act1No1Button);
        Act1No1Hasil = findViewById(R.id.Act1No1Hasil);

        Act1No2Edit1 = findViewById(R.id.Act1No2Edit1);
        Act1No2Edit2 = findViewById(R.id.Act1No2Edit2);
        Act1No2Button = findViewById(R.id.Act1No2Button);
        Act1No2Hasil = findViewById(R.id.Act1No2Hasil);

    }

    public void Act1No1Button(View view){
        Act1No1Hasil.setText(Act1No1Edit.getText().toString());
    }
    public void Act1No2Button(View view){
        float bmi = 0;
        String status;
        try
        {
            bmi =  Float.valueOf(Act1No2Edit1.getText().toString()) / ((Float.valueOf(Act1No2Edit2.getText().toString())/100) * (Float.valueOf(Act1No2Edit2.getText().toString())/100));

            if(bmi < 18.5){
                status = "Under weight";
            }
            else if(bmi < 24.9){
                status = "Normal Weight";
            }
            else if(bmi < 29.9){
                status = "Over Weight";
            }
            else if(bmi < 34.9){
                status = "Obesity 1";
            }
            else if (bmi < 39.9){
                status = "Obesity 2";
            }
            else{
                status = "Obesity 3";
            }
        }
        catch (Exception e)
        {
            status = "input salah";
        }

        Act1No2Hasil.setText(String.valueOf(bmi) + " " + status);
    }
}
