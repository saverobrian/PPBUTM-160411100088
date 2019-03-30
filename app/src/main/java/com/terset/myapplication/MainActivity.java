package com.terset.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button buttonAct1;
    Button buttonAct2;
    Button buttonAct4;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(MainActivity.this);
        setContentView(R.layout.activity_main);

        buttonAct1 = (Button) findViewById(R.id.buttonAct1);
        buttonAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        buttonAct2 = (Button) findViewById(R.id.buttonAct2);
        buttonAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        buttonAct4 = (Button) findViewById(R.id.buttonAct4);
        buttonAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });

        nama = findViewById(R.id.nama);
    }

    public void openActivity1(){
        Intent intent = new Intent(this, Activity1.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
    public void openActivity4(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume(){
        super.onResume();
        loadLocale(MainActivity.this);
    }
    public Context setLocale(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        //Configuration config = new Configuration();
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            //context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

        return context;
    }

    public void loadLocale(Context context){
        SharedPreferences prefs = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String languange = prefs.getString("My_Lang", "");
        setLocale(context, ""+languange);
    }
}
