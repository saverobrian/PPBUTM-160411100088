package com.terset.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //baru...
    EditText main1No1Edit;
    Button main1No1Button;
    TextView main1No1Hasil;

    EditText main1No2Edit1;
    EditText main1No2Edit2;
    Button main1No2Button;
    TextView main1No2Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void home(View view){
        setContentView(R.layout.activity_main);
    }
    public void klikButtonMain1(View view){
        setContentView(R.layout.activity_main1);

        main1No1Edit = findViewById(R.id.main1No1Edit);
        main1No1Button = findViewById(R.id.main1No1Button);
        main1No1Hasil = findViewById(R.id.main1No1Hasil);

        main1No2Edit1 = findViewById(R.id.main1No2Edit1);
        main1No2Edit2 = findViewById(R.id.main1No2Edit2);
        main1No2Button = findViewById(R.id.main1No2Button);
        main1No2Hasil = findViewById(R.id.main1No2Hasil);
    }
    public void main1No1Button(View view){
        main1No1Hasil.setText(main1No1Edit.getText().toString());
    }
    public void main1No2Button(View view){
        float bmi = 0;
        String status;
        try
        {
            bmi =  Float.valueOf(main1No2Edit1.getText().toString()) / ((Float.valueOf(main1No2Edit2.getText().toString())/100) * (Float.valueOf(main1No2Edit2.getText().toString())/100));

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

        main1No2Hasil.setText(String.valueOf(bmi) + " " + status);
    }
}
