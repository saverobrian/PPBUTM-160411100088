package com.terset.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityProject_PilihHardware extends AppCompatActivity {

    DatabaseHelperProject myDB;
    String[] list, listID;
    ListView pilihHardware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_pilih_hardware);

        final Intent intent = getIntent();
        String Hardware = intent.getStringExtra("Hardware");

        myDB = new DatabaseHelperProject(this);
        Cursor res = myDB.getAllData(Hardware,intent.getStringExtra("Where"));

        list = new String[res.getCount()];
        listID = new String[res.getCount()];
        while(res.moveToNext()){
            list[res.getPosition()] = res.getString(1);
            listID[res.getPosition()] = res.getString(0);
        }

        pilihHardware = findViewById(R.id.projectInsert_PilihHardware);
        pilihHardware.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        pilihHardware.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = listID[position];
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Id",selection);
                returnIntent.putExtra("Tipe", intent.getStringExtra("Tipe"));
//                Toast.makeText(getApplicationContext(), intent.getStringExtra("Tipe"), Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }

}
