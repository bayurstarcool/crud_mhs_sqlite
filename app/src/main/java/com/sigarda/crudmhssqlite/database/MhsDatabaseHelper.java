package com.sigarda.crudmhssqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MhsDatabaseHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "MahasiswaDB";
    private static final int DB_VERSION = 1;

    /**
     * Konstruktor
     * @param context konteks activity
     */
    public MhsDatabaseHelper(Context context)
    {
        super(context, MhsDatabaseHelper.DB_NAME, null, MhsDatabaseHelper.DB_VERSION);
    }

    /**
     * Dijalankan ketika pertamakali database dibuat
     * @param db SQLite Database
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE MAHASISWA (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, NIM TEXT)");
    }

    /**
     * Dijalankan ketika melakukan upgrade db
     * @param sqLiteDatabase db
     * @param oldVersion versi lama (int)
     * @param newVersion versi baru (int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

    }

    /**
     * Dijalankan ketika melakukan downgrade db
     * @param db SQLite db
     * @param oldVersion versi lama
     * @param newVersion versi baru
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}