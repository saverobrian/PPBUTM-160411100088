package com.terset.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity4_2 extends AppCompatActivity {

    TextView Act4_2TView1;
    LinearLayout Act4_2LLayout2_2;
    TextView Act4_2TView5;
    TextView Act4_2TView6;
    TextView Act4_2TView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        Act4_2TView1 = findViewById(R.id.Act4_2TView1);
        Act4_2TView1.setText(""+intent.getStringExtra("nama"));

        Act4_2LLayout2_2 = findViewById(R.id.Act4_2LLayout2_2);
        for (int i = 0; i < intent.getCharSequenceArrayListExtra("judul").size(); i++) {
            if((intent.getCharSequenceArrayListExtra("checkbox").get(i)+"").equals("true")){
                TextView textd = new TextView(this);
                textd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                textd.setText(intent.getCharSequenceArrayListExtra("judul").get(i)+" "+intent.getCharSequenceArrayListExtra("jumlah").get(i)+" "+intent.getCharSequenceArrayListExtra("sajian").get(i));
                textd.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                Act4_2LLayout2_2.addView(textd);
            }
        }
        Act4_2TView5 = findViewById(R.id.Act4_2TView5);
        Act4_2TView5.setText(intent.getIntExtra("total",0)+"");
        Act4_2TView6 = findViewById(R.id.Act4_2TView6);
        Act4_2TView6.setText(intent.getIntExtra("bayar",0)+"");
        Act4_2TView7 = findViewById(R.id.Act4_2TView7);
        Act4_2TView7.setText(intent.getIntExtra("bayar",0)-intent.getIntExtra("total",0)+"");
    }
}
