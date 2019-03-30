package com.terset.myapplication;

import android.Manifest;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Activity2 extends AppCompatActivity {
    private static  final int REQUEST_CALL = 1;

    EditText Act2EditNama;

    CheckBox Act2MenuCoffeCBox1, Act2MenuCoffeCBox2, Act2MenuCoffeCBox3;
    ArrayList<String> cBoxResult;

    RadioGroup Act2SajianRGroup;
    RadioButton Act2SajianRButton1, Act2SajianRButton2;
    String sajian;

    Button act2JumlahMin, act2JumlahPlus;
    int jumlah;
    TextView act2Jumlah;

    TextView act2Total;

    EditText act2Bayar;

    Button act2Pesan;
    Button act2Hubungi;

    RadioGroup Act2BahasaRGroup;
    RadioButton Act2BahasaRButton1, Act2BahasaRButton2;
    TextView Act2BahasaJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(Activity2.this);
        setContentView(R.layout.activity_2);

        Act2EditNama = (EditText) findViewById(R.id.Act2EditNama);
        //Act2EditNama.setText(R.string.masukan_nama);
        jumlah = 0;

        Act2MenuCoffeCBox1 = (CheckBox) findViewById(R.id.Act2MenuCoffeCBox1);
        Act2MenuCoffeCBox2 = (CheckBox) findViewById(R.id.Act2MenuCoffeCBox2);
        Act2MenuCoffeCBox3 = (CheckBox) findViewById(R.id.Act2MenuCoffeCBox3);
        cBoxResult = new ArrayList<>();
        Act2MenuCoffeCBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Act2MenuCoffeCBox1.isChecked()){
                    cBoxResult.add("Arabica");
                }
                else{
                    cBoxResult.remove("Arabica");
                }
                updateTotal();
            }
        });
        Act2MenuCoffeCBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Act2MenuCoffeCBox2.isChecked()){
                    cBoxResult.add("Robusta");
                }
                else{
                    cBoxResult.remove("Robusta");
                }
                updateTotal();
            }
        });
        Act2MenuCoffeCBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Act2MenuCoffeCBox3.isChecked()){
                    cBoxResult.add("American");
                }
                else{
                    cBoxResult.remove("American");
                }
                updateTotal();
            }
        });

        Act2SajianRGroup = (RadioGroup) findViewById(R.id.Act2SajianRGroup);
        Act2SajianRButton1 = (RadioButton) findViewById(R.id.Act2SajianRButton1);
        Act2SajianRButton2 = (RadioButton) findViewById(R.id.Act2SajianRButton2);
        Act2SajianRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(Act2SajianRButton1.isChecked()){
                    sajian = Act2SajianRButton1.getText().toString();
                }
                if(Act2SajianRButton2.isChecked()){
                    sajian = Act2SajianRButton2.getText().toString();
                }
            }
        });

        act2JumlahMin = (Button) findViewById(R.id.act2JumlahMin);;
        act2JumlahPlus = (Button) findViewById(R.id.act2JumlahPlus);
        act2Jumlah = (TextView) findViewById(R.id.act2Jumlah);
        act2JumlahMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah--;
                if(jumlah < 0){
                    jumlah = 0;
                }
                act2Jumlah.setText(""+jumlah);
                updateTotal();
            }
        });
        act2JumlahPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah++;
                if(jumlah > 9999){
                    jumlah = 9999;
                }
                act2Jumlah.setText(""+jumlah);
                updateTotal();
            }
        });
        act2Jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    jumlah = Integer.valueOf(act2Jumlah.getText().toString());
                }catch (Exception e){
                    jumlah = 0;
                    act2Jumlah.setText(""+jumlah);
                }
                updateTotal();
            }
        });

        act2Total = (TextView) findViewById(R.id.act2Total);
        act2Bayar = (EditText) findViewById(R.id.act2Bayar);

        act2Pesan = (Button) findViewById(R.id.act2Pesan);
        act2Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2_1();
            }
        });

        act2Hubungi = (Button) findViewById(R.id.act2Hubungi);
        act2Hubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent callIntent = new Intent(Intent.ACTION_DIAL);
                MakePhoneCall();
            }
        });

        Act2BahasaJudul = (TextView) findViewById(R.id.Act2BahasaJudul);
        Act2BahasaRGroup  = (RadioGroup) findViewById(R.id.Act2BahasaRGroup);
        Act2BahasaRButton1 = (RadioButton) findViewById(R.id.Act2BahasaRButton1);
        Act2BahasaRButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale(getBaseContext(),"id");
                recreate();
            }
        });
        Act2BahasaRButton2 = (RadioButton) findViewById(R.id.Act2BahasaRButton2);
        Act2BahasaRButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale(getBaseContext(),"en");
                recreate();
            }
        });
        String localeDefault = Locale.getDefault().toString();
        if(localeDefault.equals("in")){
            Act2BahasaRButton1.setChecked(true);
        }
        else if(localeDefault.equals("en")){
            Act2BahasaRButton2.setChecked(true);
        }
        Act2BahasaRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Act2BahasaJudul.setText("berubah");
                if(Act2BahasaRButton1.isChecked()){
                    //toIndonesia();
                }
                if(Act2BahasaRButton2.isChecked()){
                    //toEnglish();
                }
            }
        });
    }
    public void openActivity2_1(){
        String nama = Act2EditNama.getText().toString();
        int bayar;
        try{
            bayar = Integer.valueOf(act2Bayar.getText().toString());
        }
        catch (Exception e){
            bayar = 0;
        }

        Intent intent = new Intent(this, Activity2_1.class);
        if(!nama.isEmpty()){
            intent.putExtra("nama", nama);
            if(cBoxResult.size() != 0){
                intent.putExtra("pesan", cBoxResult);
                if(sajian != null){
                    intent.putExtra("sajian", sajian);
                    if(jumlah != 0){
                        intent.putExtra("jumlah", jumlah);
                        if(getTotal() <= bayar && bayar != 0){
                            intent.putExtra("total", getTotal());
                            intent.putExtra("bayar", bayar);
                            startActivity(intent);
                            Toast.makeText(this, "Uang Cukup,", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Uang Kurang", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(this, "Masukan Jumlah", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Masukan Sajian", Toast.LENGTH_SHORT).show();
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
    public void updateTotal(){
        act2Total.setText("TOTAL : "+ getTotal());
    }
    public int getTotal(){
        int total = 0;
        if(Act2MenuCoffeCBox1.isChecked()){
            total += 10000 * jumlah;
        }
        if(Act2MenuCoffeCBox2.isChecked()){
            total += 15000 * jumlah;
        }
        if(Act2MenuCoffeCBox3.isChecked()){
            total += 20000 * jumlah;
        }
        return total;
    }
    public void toEnglish(){
        Act2BahasaJudul.setText(R.string.bahasa);
    }
    public void toIndonesia(){
        Act2BahasaJudul.setText(R.string.bahasa);
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

    public void MakePhoneCall(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getBaseContext(), ""+Manifest.permission.CALL_PHONE, Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(Activity2.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
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
}
