package com.terset.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityProject extends AppCompatActivity {

    DatabaseHelperProject myDB;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> mlist_id = new ArrayList<>();
    private ArrayList<String> mlist_nama = new ArrayList<>();
    private ArrayList<String> mlist_harga = new ArrayList<>();
//    private ArrayList<ArrayList<String>> mlist_detailId = new ArrayList<>();
    private ArrayList<String> mlist_detail = new ArrayList<>();

    private Button project_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        myDB = new DatabaseHelperProject(this);
        Cursor res = myDB.getAllData("build");

        while (res.moveToNext()){
            mlist_id.add(res.getString(0));
            mlist_nama.add(res.getString(1));
            int hargaProcessor = myDB.getData("tb_Processor", res.getString(2)).getInt(4);
            int hargaMotherBoard = myDB.getData("tb_MotherBoard", res.getString(3)).getInt(4);
            int hargaGraphicCard = myDB.getData("tb_GraphicCard", res.getString(4)).getInt(2);
            int hargaMemory = myDB.getData("tb_Memory", res.getString(5)).getInt(3);
            int harga = hargaProcessor + hargaMotherBoard + hargaGraphicCard + hargaMemory;
            Log.d("create","ISO");
            mlist_harga.add("Rp. "+String.valueOf(harga));
//            ArrayList<String> isiId = new ArrayList<String>();
//            isiId.add(myDB.getData("tb_Processor",res.getString(2)).getString(0));
//            isiId.add(myDB.getData("tb_MotherBoard",res.getString(3)).getString(0));
//            isiId.add(myDB.getData("tb_GraphicCard",res.getString(4)).getString(0));
//            isiId.add(myDB.getData("tb_Memory",res.getString(5)).getString(0));
//            mlist_detailId.add(isiId);
            String namaProcessor = myDB.getData("tb_Processor", res.getString(2)).getString(1);
            String namaMotherBoard = myDB.getData("tb_MotherBoard", res.getString(3)).getString(1);
            String namaGraphicCard = myDB.getData("tb_GraphicCard", res.getString(4)).getString(1);
            String namaMemory = myDB.getData("tb_Memory", res.getString(5)).getString(1);
            String namaNama = namaProcessor + " + " + namaMotherBoard + " + " + namaGraphicCard + " + " + namaMemory;
            mlist_detail.add(namaNama);
        }

        mRecyclerView = findViewById(R.id.project_recycleView);
        mAdapter = new RecycleViewAdapterProject(this, mlist_id,mlist_nama, mlist_harga, mlist_detail);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        //Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
        mRecyclerView.setLayoutManager(mLayoutManager);

        project_tambah = findViewById(R.id.project_tambah);
        project_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getBaseContext(), ActivityProject_insert.class);
                intent.putExtra("mode","add");
                startActivity(intent);
                finish();
            }
        });
    }
}
