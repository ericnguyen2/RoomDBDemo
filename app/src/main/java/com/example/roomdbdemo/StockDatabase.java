package com.example.roomdbdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Stock.class}, version = 1, exportSchema = false)
public abstract class StockDatabase extends RoomDatabase {

    public abstract StockDAO stockDAO();
}