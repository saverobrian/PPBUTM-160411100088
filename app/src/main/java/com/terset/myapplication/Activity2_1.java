package com.terset.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2_1 extends AppCompatActivity {

    TextView Act2_2TView1;
    LinearLayout Act2_2LLayout2_2;
    TextView Act2_2LLayoutTview1;
    TextView Act2_2TView3;
    TextView Act2_2TView4;
    TextView Act2_2TView5;
    TextView Act2_2TView6;
    TextView Act2_2TView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_1);

        Intent intent = getIntent();
        Act2_2TView1 = (TextView) findViewById(R.id.Act2_2TView1);
        Act2_2TView1.setText(""+intent.getStringExtra("nama"));

        ArrayList<String> pesan = intent.getStringArrayListExtra("pesan");
        Act2_2LLayoutTview1 = (TextView) findViewById(R.id.Act2_2LLayoutTview1Judul);
        Act2_2LLayout2_2 = (LinearLayout) findViewById(R.id.Act2_2LLayout2_2);
        for (int i = 0; i < pesan.size(); i++) {
            TextView textd = new TextView(this);
            textd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            textd.setText(pesan.get(i));
            textd.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            Act2_2LLayout2_2.addView(textd);
        }

        Act2_2TView3 = (TextView) findViewById(R.id.Act2_2TView3);
        Act2_2TView3.setText(""+intent.getStringExtra("sajian"));

        Act2_2TView4 = (TextView) findViewById(R.id.Act2_2TView4);
        Act2_2TView4.setText(""+intent.getIntExtra("jumlah",0));

        Act2_2TView5 = (TextView) findViewById(R.id.Act2_2TView5);
        int total = intent.getIntExtra("total",0);
        Act2_2TView5.setText(""+total);

        Act2_2TView6 = (TextView) findViewById(R.id.Act2_2TView6);
        int bayar = intent.getIntExtra("bayar",0);
        Act2_2TView6.setText(""+bayar);

        int kembalian = bayar - total;
        Act2_2TView7 = (TextView) findViewById(R.id.Act2_2TView7);
        Act2_2TView7.setText(""+kembalian);
    }
}
