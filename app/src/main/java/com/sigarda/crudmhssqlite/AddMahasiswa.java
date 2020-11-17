package com.sigarda.crudmhssqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sigarda.crudmhssqlite.database.MhsDatabaseHelper;

public class AddMahasiswa extends AppCompatActivity
{

    private EditText nameV;
    private EditText nimV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        this.nameV = findViewById(R.id.input_name);
        this.nimV = findViewById(R.id.input_nim);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button showBtn = findViewById(R.id.showBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ContentValues values = new ContentValues();

                values.put("NAME",nameV.getText().toString());
                values.put("NIM",nimV.getText().toString());

                SQLiteOpenHelper sqLiteOpenHelper = new MhsDatabaseHelper(AddMahasiswa.this);
                SQLiteDatabase db = null;

                try
                {
                    db = sqLiteOpenHelper.getWritableDatabase();
                    db.insert("MAHASISWA", null, values);

                    Toast.makeText(AddMahasiswa.this, "Insert Success", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(AddMahasiswa.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    if(db != null && db.isOpen())
                        db.close();
                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddMahasiswa.this, ListMahasiswa.class);
                startActivity(intent);
            }
        });

    }
}
