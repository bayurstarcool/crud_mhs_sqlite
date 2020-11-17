package com.sigarda.crudmhssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addMhsBtn = findViewById(R.id.addMhsBtn);
        Button showListMhsBtn = findViewById(R.id.showListMhsBtn);

        addMhsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddMahasiswa.class);
                startActivity(intent);
            }
        });

        showListMhsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ListMahasiswa.class);
                startActivity(intent);
            }
        });

    }
}