package com.example.uts_akb_2020;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    //Nama DB
    public static final String DATABASE_NAME = "biodata.db";
    //Nama Tabel
    public static final String TABLE_NAME = "table_mahasiswa";
    //Versi DB
    public static final int DATABASE_VERSION = 1;
    //Tabel Field
    public static final String COL_1 = "NIM";
    public static final String COL_2 = "NAMA";
    public static final String COL_3 = "KELAS";
    public static final String COL_4 = "PRODI";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table table_mahasiswa(nim integer primary key , nama text,  kelas text, prodi text );");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Simpan Data
    public boolean insertData(String nim, String nama, String kelas, String prodi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nim);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, kelas);
        contentValues.put(COL_4, prodi);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //Mengambil Data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM table_mahasiswa", null);
        return res;
    }
    //Merubah Data
    public boolean updateData(String nim, String nama, String kelas, String prodi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nim);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, kelas);
        contentValues.put(COL_4, prodi);
        db.update(TABLE_NAME, contentValues, "NIM=?", new String[]{nim});
        return true;
    }
    //Menghapus Data
    public int deleteData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NIM = ?", new String[]{nim});
    }
}

