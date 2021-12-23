package com.example.modul2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DB_name = "film.db";
    public static final String table1_name = "tbfilm";
    public static final String colid = "id";
    public static final String coljudul = "judul";
    public static final String coltahun = "tahun";
    public static final String columur = "umur";
    public static final String colrating = "rating";
    public static final String colgenre = "genre";

    DBHelper(Context context) {
        super(context, DB_name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table " + table1_name +" (id INTEGER PRIMARY KEY AUTOINCREMENT,judul TEXT,tahun TEXT,umur TEXT, rating TEXT, genre TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS "+table1_name);
        onCreate(DB);
    }

    public Boolean insertfilmdata(String judul, String tahun, String umur, String rating, String genre) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(coljudul, judul);
        contentValues.put(coltahun, tahun);
        contentValues.put(columur, umur);
        contentValues.put(colrating, rating);
        contentValues.put(colgenre, genre);
        long result = DB.insert(table1_name,null ,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + table1_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String judul, String tahun, String umur, String rating, String genre){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(coljudul, judul);
        contentValues.put(coltahun, tahun);
        contentValues.put(columur, umur);
        contentValues.put(colrating, rating);
        contentValues.put(colgenre, genre);
        long result = DB.update(table1_name, contentValues, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Data Gagal Diupdate", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id){
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete(table1_name, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Data Gagal Dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
        }

    }
}


