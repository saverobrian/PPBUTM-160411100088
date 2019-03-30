package com.terset.myapplication;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Activity4 extends AppCompatActivity implements OnItemClick{
    private static  final int REQUEST_CALL = 1;

    EditText Act4EditNama;
    Button act4Pesan;
    Button act4Hubungi;
    TextView act4Total;
    EditText act4Bayar;
    TextView Act4BahasaJudul;
    RadioGroup Act4BahasaRGroup;
    RadioButton Act4BahasaRButton1;
    RadioButton Act4BahasaRButton2;

    private ArrayList<Boolean> mlist_checkbox = new ArrayList<>();
    private ArrayList<String> mlist_judul = new ArrayList<>();
    private ArrayList<String> mlist_jumlah = new ArrayList<>();
    private ArrayList<String> mlist_sajian = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(Activity4.this);
        setContentView(R.layout.activity4);

        Act4EditNama = findViewById(R.id.Act4EditNama);

        act4Total = findViewById(R.id.act4Total);
        act4Bayar = findViewById(R.id.act4Bayar);

        // BUTTON PESAN
        act4Pesan = findViewById(R.id.act4Pesan);
        act4Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4_2();
            }
        });

        // BUTTON CALL
        act4Hubungi = findViewById(R.id.act4Hubungi);
        act4Hubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakePhoneCall();
            }
        });

        Act4BahasaJudul = findViewById(R.id.Act4BahasaJudul);
        Act4BahasaRGroup = findViewById(R.id.Act4BahasaRGroup);
        Act4BahasaRButton1 = findViewById(R.id.Act4BahasaRButton1);
        Act4BahasaRButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale(getBaseContext(),"id");
                recreate();
            }
        });
        Act4BahasaRButton2 = findViewById(R.id.Act4BahasaRButton2);
        Act4BahasaRButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale(getBaseContext(),"en");
                recreate();
            }
        });
        String localeDefault = Locale.getDefault().toString();
        if(localeDefault.equals("in")){
            Act4BahasaRButton1.setChecked(true);
        }
        else if(localeDefault.equals("en")){
            Act4BahasaRButton2.setChecked(true);
        }

        mlist_checkbox.add(false);
        mlist_judul.add("Arabica");
        mlist_jumlah.add("0");
        mlist_sajian.add("-");

        mlist_checkbox.add(false);
        mlist_judul.add("American");
        mlist_jumlah.add("0");
        mlist_sajian.add("-");

        mlist_checkbox.add(false);
        mlist_judul.add("Robusta");
        mlist_jumlah.add("0");
        mlist_sajian.add("-");

        mRecyclerView = findViewById(R.id.Act4RecycleView);
        mAdapter = new RecycleViewAdapter(this, mlist_checkbox, mlist_judul, mlist_jumlah, mlist_sajian, this);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
    public void openActivity4_2(){
        Intent intent = new Intent(getBaseContext(), Activity4_2.class);
        if(!Act4EditNama.getText().toString().isEmpty()){
            intent.putExtra("nama", Act4EditNama.getText().toString());
            //intent.putExtra("nama", nama);
            if(mlist_checkbox.get(0) || mlist_checkbox.get(1) || mlist_checkbox.get(2)){
                intent.putExtra("checkbox", mlist_checkbox);
                intent.putExtra("judul", mlist_judul);
                intent.putExtra("jumlah", mlist_jumlah);
                intent.putExtra("sajian", mlist_sajian);

                int bayar;
                try{
                    bayar = Integer.valueOf(act4Bayar.getText().toString());
                }
                catch (Exception e){
                    bayar = 0;
                }
                if(getTotal() <= bayar && bayar != 0){
                    intent.putExtra("bayar", bayar);
                    intent.putExtra("total", getTotal());
                    startActivity(intent);
                    Toast.makeText(this, "Uang Cukup", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Uang Kurang", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Masukan Pesanan", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Masukan Nama", Toast.LENGTH_SHORT).show();
        }
    }
    public void setTotal(){
        act4Total.setText("TOTAL : "+ getTotal());
    }
    public int getTotal(){
        int total = 0;
        if(mlist_checkbox.get(0)){
            total += 10000 * Integer.valueOf(mlist_jumlah.get(0));
        }
        if(mlist_checkbox.get(1)){
            total += 15000 * Integer.valueOf(mlist_jumlah.get(1));
        }
        if(mlist_checkbox.get(2)){
            total += 20000 * Integer.valueOf(mlist_jumlah.get(2));
        }
        return total;
    }

    public void MakePhoneCall(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getBaseContext(), ""+Manifest.permission.CALL_PHONE, Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(Activity4.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        else{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:037777700"));
            startActivity(callIntent);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                MakePhoneCall();
            }
            else{
                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Context setLocale(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        //Configuration config = new Configuration();
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
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

    // ---

    @Override
    public void onClick(int index, Boolean checkBox, String jumlah, String sajian) {
        Act4EditNama.setText(index+"");
        //mlist_judul.set(0, "kk");
        mlist_checkbox.set(index, checkBox);
        mlist_jumlah.set(index, jumlah);
        mlist_sajian.set(index, sajian);

        mAdapter.notifyDataSetChanged();

        setTotal();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                int index = data.getIntExtra("index",0);
                mlist_checkbox.set(index, true);
                mlist_jumlah.set(index, data.getStringExtra("jumlah"));
                mlist_sajian.set(index, data.getStringExtra("sajian"));

                mAdapter.notifyDataSetChanged();

                setTotal();
                //Act4EditNama.setText("were back");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(getBaseContext(), "About", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu2:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
