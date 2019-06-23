package com.terset.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityProject_PilihProcessor extends AppCompatActivity {

    DatabaseHelperProject myDB;
    String[] list;
    ListView pilihProcessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_pilih_processor);

        myDB = new DatabaseHelperProject(this);
        Cursor res = myDB.getAllData("tb_Processor");

        list = new String[res.getCount()];
        while(res.moveToNext()){
            list[res.getPosition()] = res.getString(1);
        }

        pilihProcessor = findViewById(R.id.projectInsert_PilihProcessor);
        pilihProcessor.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));

    }
}
