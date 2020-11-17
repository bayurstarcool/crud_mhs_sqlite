package com.sigarda.crudmhssqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sigarda.crudmhssqlite.adapter.MhsCursorAdapter;
import com.sigarda.crudmhssqlite.database.MhsDatabaseHelper;

public class ListMahasiswa extends AppCompatActivity
{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        SQLiteOpenHelper sqLiteOpenHelper = new MhsDatabaseHelper(ListMahasiswa.this);
        listView = findViewById(R.id.list_mhs);

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try
        {
            db = sqLiteOpenHelper.getReadableDatabase();

            cursor = db.query("MAHASISWA",
                    new String[]{"_id","NAME", "NIM"},
                    null, null, null, null,
                    "NIM ASC");

            MhsCursorAdapter adapter = new MhsCursorAdapter(ListMahasiswa.this, cursor);

            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        finally
        {
            if(cursor != null && cursor.isClosed())
                cursor.close();

            if(db != null && db.isOpen())
                db.close();
        }

    }
    public void deleteMhs(String NIM){
        SQLiteOpenHelper sqLiteOpenHelper = new MhsDatabaseHelper(ListMahasiswa.this);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = sqLiteOpenHelper.getWritableDatabase();

            db.delete("MAHASISWA","NIM="+NIM,null);

            cursor = db.query("MAHASISWA",
                    new String[]{"_id","NAME", "NIM"},
                    null, null, null, null,
                    "NIM ASC");

            MhsCursorAdapter adapter = new MhsCursorAdapter(ListMahasiswa.this, cursor);

            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        finally
        {
            if(cursor != null && cursor.isClosed())
                cursor.close();

            if(db != null && db.isOpen())
                db.close();
        }
        Toast.makeText(this,"Mahasiswa "+NIM+" deleted",Toast.LENGTH_SHORT).show();
    }
}