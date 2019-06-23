package com.terset.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity4_1 extends AppCompatActivity {

    TextView act4_1Kopi;
    EditText act4_1Jumlah;
    EditText act4_1Sajian;
    Button act4_1Tombol;

    private OnItemClick mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_1);

        act4_1Kopi = findViewById(R.id.act4_1Kopi);
        act4_1Jumlah = findViewById(R.id.act4_1Jumlah);
        act4_1Sajian = findViewById(R.id.act4_1Sajian);
        act4_1Tombol = findViewById(R.id.act4_1Tombol);

        Intent intent = getIntent();
        final int index = intent.getIntExtra("index", 0);
        String judul = intent.getStringExtra("judul");
        String jumlah = intent.getStringExtra("jumlah");
        String sajian = intent.getStringExtra("sajian");

        act4_1Kopi.setText(judul);
        act4_1Jumlah.setText(jumlah);
        act4_1Sajian.setText(sajian);

        act4_1Tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("index", index);
                //resultIntent.putExtra("judul", judul);
                resultIntent.putExtra("jumlah", act4_1Jumlah.getText().toString());
                resultIntent.putExtra("sajian", act4_1Sajian.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
