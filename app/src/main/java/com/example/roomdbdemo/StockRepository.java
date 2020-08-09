package com.example.roomdbdemo;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

public class StockRepository {

    private String DB_NAME = "stockdb";

    private StockDatabase stockDatabase;

    Context context;

    public StockRepository(Context context) {
        this.context = context;
        stockDatabase = Room.databaseBuilder(context, StockDatabase.class, DB_NAME).build();

        Toast.makeText(context, "Database created...", Toast.LENGTH_LONG).show();
    }
}
