package com.terset.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity5 extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText act5_nama, act5_harga;
    Button act5_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        myDB = new DatabaseHelper(this);
        act5_nama = (EditText) findViewById(R.id.act5_nama);
        act5_harga = (EditText) findViewById(R.id.act5_harga);
        act5_insert = (Button) findViewById(R.id.act5_insert);
        act5_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(act5_nama.getText().toString(), Integer.valueOf(act5_harga.getText().toString()));
                //boolean isInserted = myDB.insertData("black", 4000);
                if(isInserted == true){
                    Toast.makeText(Activity5.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Activity5.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
