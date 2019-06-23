package com.terset.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelperProject extends SQLiteOpenHelper {

    public static final String Database_Name = "db_project";
    public static final String Table_Processor = "tb_Processor";
    public static final String Table_Socket = "tb_Socket";
    public static final String Table_MotherBoard = "tb_MotherBoard";
    public static final String Table_GraphicCard = "tb_GraphicCard";
    public static final String Table_Memory = "tb_Memory";

    public static Context contexts;


    public DatabaseHelperProject(Context context) {
        super(context, Database_Name, null, 1);
        //Toast.makeText(context,"bisa",Toast.LENGTH_SHORT).show();
        contexts = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Processor + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "MEREK TEXT NOT NULL,"+
                "SOCKET TEXT NOT NULL,"+
                "HARGA INTEGER NOT NULL" +
                ")");
        Log.d("create","create");
        db.execSQL("create table " + Table_Socket + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "TIPEPROCESSOR TEXT NOT NULL" +
                ")");
        db.execSQL("create table " + Table_MotherBoard + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "SOCKET TEXT NOT NULL,"+
                "DDR TEXT NOT NULL,"+
                "HARGA INTEGER NOT NULL" +
                ")");
        db.execSQL("create table " + Table_GraphicCard + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "HARGA INTEGER NOT NULL" +
                ")");
        db.execSQL("create table " + Table_Memory + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "DDR TEXT NOT NULL,"+
                "HARGA INTEGER NOT NULL" +
                ")");
        db.execSQL("create table build (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMA TEXT NOT NULL," +
                "PROCESSOR INTEGER NOT NULL," +
                "MOTHERBOARD INTEGER NOT NULL," +
                "GRAPHICCARD INTEGER NOT NULL," +
                "MEMORY INTEGER NOT NULL)");

        db.execSQL("INSERT INTO tb_Processor (NAMA, MEREK, SOCKET, HARGA) VALUES ('core i3-7100K 3.0GHz', 'intel', 'LGA 1150', 2265000)");
        db.execSQL("INSERT INTO tb_Processor (NAMA, MEREK, SOCKET, HARGA) VALUES ('core i5-7600K 3.8GHz', 'intel', 'LGA 1151', 3975000)");
        db.execSQL("INSERT INTO tb_Processor (NAMA, MEREK, SOCKET, HARGA) VALUES ('core i7-7700K 4.2GHz', 'intel', 'LGA 1151', 5550000)");
        db.execSQL("INSERT INTO tb_Processor (NAMA, MEREK, SOCKET, HARGA) VALUES ('Ryzen 7 1700 3.0GHz', 'amd', 'AM4', 3800000)");
        db.execSQL("INSERT INTO tb_Processor (NAMA, MEREK, SOCKET, HARGA) VALUES ('Phenom II X2 555 3.2GHz', 'amd', 'AM3', 440000)");
        db.execSQL("INSERT INTO tb_Socket (NAMA, TIPEPROCESSOR) VALUES ('LGA 1150','intel')");
        db.execSQL("INSERT INTO tb_Socket (NAMA, TIPEPROCESSOR) VALUES ('LGA 1151','intel')");
        db.execSQL("INSERT INTO tb_Socket (NAMA, TIPEPROCESSOR) VALUES ('AM4','amd')");
        db.execSQL("INSERT INTO tb_Socket (NAMA, TIPEPROCESSOR) VALUES ('AM3','amd')");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('ASRock B250M Pro4 DDR4', 'LGA 1151', 'DDR4', 1230000)");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('ASRock Fata11ty Z270 DDR4', 'LGA 1151', 'DDR4', 2485000)");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('ASRock H81-Pro BTC DDR3', 'LGA 1150', 'DDR3', 935000)");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('Asus H81M-E DDR3', 'LGA 1150', 'DDR3', 820000)");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('Biostar A320MH', 'AM4', 'DDR4', 692000)");
        db.execSQL("INSERT INTO tb_MotherBoard (NAMA, SOCKET, DDR, HARGA) VALUES ('Gigabyte GA-78LMT', 'AM3', 'DDR3', 931000)");
        db.execSQL("INSERT INTO tb_GraphicCard (NAMA, HARGA) VALUES ('GALAX GeForce GTX 1080', 9600000)");
        db.execSQL("INSERT INTO tb_GraphicCard (NAMA, HARGA) VALUES ('GALAX GeForce GTX 1080', 7450000)");
        db.execSQL("INSERT INTO tb_GraphicCard (NAMA, HARGA) VALUES ('XFX Radeon RX 460', 1750000)");
        db.execSQL("INSERT INTO tb_Memory (NAMA, DDR, HARGA) VALUES ('Apacher DDR3 PC12800', 'DDR3', 209000)");
        db.execSQL("INSERT INTO tb_Memory (NAMA, DDR, HARGA) VALUES ('Gskill DDR4 RipJawsV PC19200', 'DDR4', 458000)");
        db.execSQL("INSERT INTO tb_Memory (NAMA, DDR, HARGA) VALUES ('Corsair DDR4 Vengeance LPX PC21000', 'DDR4', 905000)");
        db.execSQL("INSERT INTO build (NAMA,PROCESSOR, MOTHERBOARD, GRAPHICCARD, MEMORY) VALUES ('Contoh',1, 1, 1, 1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Processor);
        onCreate(db);

    }


    public boolean insertDataProcessor(String nama, String merek, String socket, int harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("MEREK", merek);
        contentValues.put("SOCKET", socket);
        contentValues.put("HARGA", harga);
        if(db.insert(Table_Processor, null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertDataSocket(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        if(db.insert(Table_Socket, null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertDataMotherBoard(String nama, String socket, String ddr, int harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("SOCKET", socket);
        contentValues.put("DDR", ddr);
        contentValues.put("HARGA", harga);
        if(db.insert(Table_MotherBoard, null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertDataGraphicCard(String nama, int harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("HARGA", harga);
        if(db.insert(Table_GraphicCard, null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean insertDataMemory(String nama, String ddr, int harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("DDR", ddr);
        contentValues.put("HARGA", harga);
        if(db.insert(Table_Memory, null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertDataBuild(String nama, String idProcessor, String idMotherBoard, String idGraphicCard, String idMemory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("PROCESSOR", idProcessor);
        contentValues.put("MOTHERBOARD", idMotherBoard);
        contentValues.put("GRAPHICCARD", idGraphicCard);
        contentValues.put("MEMORY", idMemory);
        if(db.insert("build", null, contentValues) == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateDataBuild(String id,String nama, String idProcessor, String idMotherBoard, String idGraphicCard, String idMemory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("PROCESSOR", idProcessor);
        contentValues.put("MOTHERBOARD", idMotherBoard);
        contentValues.put("GRAPHICCARD", idGraphicCard);
        contentValues.put("MEMORY", idMemory);
        if( db.update("build", contentValues, "ID = "+id,null) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteDataBuild(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("build", "ID = " + id, null) > 0;
    }


    public Cursor getAllData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName,null);
        return res;
    }

    public Cursor getAllData(String tableName, String where){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName + " where " + where,null);
        return res;
    }

    public int countAllData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName,null);
        return res.getCount();
    }

    public Cursor getData(String tableName, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName + " where id = " + id,null);
        res.moveToFirst();
        return res;
    }
}
