package com.terset.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityProject_insert extends AppCompatActivity {

    DatabaseHelperProject myDB;

    TextView processorText, motherBoardText, graphicCardText, memoryText;

    RadioGroup groupRadio;
    RadioButton radioIntel, radioAmd;

    ArrayList<String> listSocket;
    Spinner spinnerSocket;

    String processorId;
    String motherBoardId;
    String graphicCardId;
    String memomryId;
    String memomryDdr;

    Button processorPilih, motherBoardPilih, graphicCardPilih, memoryPilih;

    Intent getIntent;
    String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_insert);

        processorText = findViewById(R.id.projectInsert_ProcessorText);
        motherBoardText = findViewById(R.id.projectInsert_MotherBoardText);
        graphicCardText = findViewById(R.id.projectInsert_GrahicCardText);
        memoryText = findViewById(R.id.projectInsert_MemoryText);

        groupRadio = findViewById(R.id.projectInsert_GroupRadio);
        radioIntel = findViewById(R.id.projectInsert_RadioIntel);
        radioAmd = findViewById(R.id.projectInsert_RadioAmd);
        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.projectInsert_RadioIntel:
//                        Toast.makeText(getBaseContext(), "projectInsert_RadioIntel", Toast.LENGTH_SHORT).show();
                        RefreshPage("tipeProcessor");
                        break;
                    case R.id.projectInsert_RadioAmd:
                        RefreshPage("tipeProcessor");
                        break;

                }
            }
        });

        myDB = new DatabaseHelperProject(this);

        // insert Socket
        listSocket = new ArrayList<>();
        String radioSelected;
        if(radioIntel.isChecked()){
            radioSelected = "intel";
        }
        else{
            radioSelected = "amd";
        }
        Cursor res = myDB.getAllData("tb_Socket", "TIPEPROCESSOR = '" + radioSelected + "'");
        while (res.moveToNext()){
            listSocket.add(res.getString(1));
        }

        spinnerSocket = findViewById(R.id.projectInsert_SpinnerSocket);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listSocket);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerSocket.setAdapter(adapter);
        spinnerSocket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int spinnerCounter = 0;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerCounter++ > 0){
                    RefreshPage("socket");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        processorPilih = findViewById(R.id.projectInsert_ProcessorPilih);
        processorPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityProject_PilihHardware.class);
                intent.putExtra("Tipe", "Processor");
                intent.putExtra("Hardware", "tb_Processor");
                intent.putExtra("Where", "SOCKET = '"+listSocket.get(spinnerSocket.getSelectedItemPosition())+"'");
                startActivityForResult(intent, 1);
            }
        });

        motherBoardPilih = findViewById(R.id.projectInsert_MotherBoardPilih);
        motherBoardPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityProject_PilihHardware.class);
                intent.putExtra("Tipe", "MotherBoard");
                intent.putExtra("Hardware", "tb_MotherBoard");
                Toast.makeText(getApplicationContext(), listSocket.get(spinnerSocket.getSelectedItemPosition())+"", Toast.LENGTH_SHORT).show();
//                if(memomryDdr == null){
                    intent.putExtra("Where", "SOCKET = '"+ listSocket.get(spinnerSocket.getSelectedItemPosition())+"' AND 1");
//                }
//                else{
//                    intent.putExtra("Where", "SOCKET = '"+ listSocket.get(spinnerSocket.getSelectedItemPosition())+"' AND DDR = '"+memomryDdr+"'");
//                }
                startActivityForResult(intent, 1);
            }
        });

        graphicCardPilih = findViewById(R.id.projectInsert_GrahicCardPilih);
        graphicCardPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityProject_PilihHardware.class);
                intent.putExtra("Tipe", "GraphicCard");
                intent.putExtra("Hardware", "tb_GraphicCard");
                intent.putExtra("Where", "1");
                intent.putExtra("1", "");
                startActivityForResult(intent, 1);
            }
        });

        memoryPilih = findViewById(R.id.projectInsert_MemoryPilih);
        memoryPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityProject_PilihHardware.class);
                intent.putExtra("Tipe", "Memory");
                intent.putExtra("Hardware", "tb_Memory");
                if(memomryDdr == null){
                    intent.putExtra("Where", "1");
                }
                else{
                    intent.putExtra("Where", "DDR = '"+memomryDdr+"'");
                }
                startActivityForResult(intent, 1);
            }
        });

        // Insert / Add
        getIntent = getIntent();
        mode = getIntent.getStringExtra("mode");
        if(getIntent.getStringExtra("mode").equals("update")){
            Cursor selection = myDB.getData("build", getIntent.getStringExtra("id"));
            processorId = selection.getString(2);
            motherBoardId = selection.getString(3);
            graphicCardId = selection.getString(4);
            memomryId = selection.getString(5);
            RefreshPage("update");

        }
        else{
            Toast.makeText(getApplicationContext(),"mode : add",Toast.LENGTH_SHORT).show();
        }
    }
    public void RefreshPage(String tipe){
        if(tipe.equals("update")){
            Cursor selection = myDB.getData("tb_Processor",processorId);
            if(selection.getString(2).equals("intel")){
//                radioIntel.setChecked(true);
            }
            else{
//                radioAmd.setChecked(true);
            }
            String text = "";
            for(int i = 0; i < listSocket.size(); i++){
                if(listSocket.get(i).equals(selection.getString(3))){
//                    Toast.makeText(getApplicationContext(),"mode : update" + listSocket.get(i),Toast.LENGTH_SHORT).show();
//                    spinnerSocket.setSelection(i);
                }
            }
            processorText.setText(selection.getString(1));
            selection = myDB.getData("tb_MotherBoard", motherBoardId);
//            Toast.makeText(getApplicationContext(),motherBoardId,Toast.LENGTH_SHORT).show();
            motherBoardText.setText(selection.getString(1));
            selection = myDB.getData("tb_GraphicCard", graphicCardId);
            graphicCardText.setText(selection.getString(1));
            selection = myDB.getData("tb_Memory", graphicCardId);
            memoryText.setText(selection.getString(1));
        }
        else if(tipe.equals("tipeProcessor")){
            processorId = null;
            motherBoardId = null;
            graphicCardId = null;
            memomryId = null;
            memomryDdr = null;

            processorText.setText("Pilih Processor");
            motherBoardText.setText("Pilih MotherBoard");
            graphicCardText.setText("Pilih Graphic Card");
            memoryText.setText("Pilih Memory");

            // insert Socket
            listSocket = new ArrayList<>();
            String radioSelected;
            if(radioIntel.isChecked()){
                radioSelected = "intel";
            }
            else{
                radioSelected = "amd";
            }
            Cursor res = myDB.getAllData("tb_Socket", "TIPEPROCESSOR = '" + radioSelected + "'");
            while (res.moveToNext()){
                listSocket.add(res.getString(1));
            }

            //Refresh spinner
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listSocket);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinnerSocket.setAdapter(adapter);
        }
        else if(tipe.equals("socket")){
            processorId = null;
            motherBoardId = null;
            graphicCardId = null;
            memomryId = null;
            memomryDdr = null;

            processorText.setText("Pilih Processor");
            motherBoardText.setText("Pilih MotherBoard");
            graphicCardText.setText("Pilih Graphic Card");
            memoryText.setText("Pilih Memory");
        }
        else if(tipe.equals("memory")){
            memomryId = null;
            memoryText.setText("Pilih Memory");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                if(data.getStringExtra("Tipe").equals("Processor")){
                    processorText.setText(myDB.getData("tb_Processor", data.getStringExtra("Id")).getString(1));
                    processorId = data.getStringExtra("Id");
//                    Toast.makeText(this, processorId, Toast.LENGTH_SHORT).show();
                }
                if(data.getStringExtra("Tipe").equals("MotherBoard")){
                    motherBoardText.setText(myDB.getData("tb_MotherBoard", data.getStringExtra("Id")).getString(1));
                    motherBoardId = data.getStringExtra("Id");
                    memomryDdr = myDB.getData("tb_MotherBoard", motherBoardId).getString(3);
                    RefreshPage("memory");
                }
                else if(data.getStringExtra("Tipe").equals("GraphicCard")){
                    graphicCardText.setText(myDB.getData("tb_GraphicCard", data.getStringExtra("Id")).getString(1));
                    graphicCardId = data.getStringExtra("Id");
                }
                else if(data.getStringExtra("Tipe").equals("Memory")){
                    memoryText.setText(myDB.getData("tb_Memory", data.getStringExtra("Id")).getString(1));
                    memomryId = data.getStringExtra("Id");
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_project, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSimpan:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityProject_insert.this);
                alertDialogBuilder.setTitle("Simpan Build");
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.activity_project_simpan, null);
                final TextView inputNama = view.findViewById(R.id.projectInsert_SimpanText);
                alertDialogBuilder.setView(view);
                alertDialogBuilder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor selection = myDB.getData("build", getIntent.getStringExtra("id"));
                        if(mode.equals("update")){
                            String text = processorId + motherBoardId + graphicCardId + memomryId;
                            Boolean insert = myDB.updateDataBuild(getIntent.getStringExtra("id"),inputNama.getText()+"", processorId,motherBoardId , graphicCardId ,memomryId);
                            Toast.makeText(getBaseContext(), "Simpan"+text+insert.toString()+inputNama.getText(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String text = processorId + motherBoardId + graphicCardId + memomryId;
                            Boolean insert = myDB.insertDataBuild(inputNama.getText()+"", processorId,motherBoardId , graphicCardId ,memomryId);
                            Toast.makeText(getBaseContext(), "Simpan"+text+insert.toString()+inputNama.getText(), Toast.LENGTH_SHORT).show();
                        }
                        Intent intentHome = new Intent(getApplicationContext(),ActivityProject.class);
                        startActivity(intentHome);
                    }
                });
                alertDialogBuilder.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
