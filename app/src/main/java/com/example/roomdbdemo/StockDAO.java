package com.example.roomdbdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface StockDAO {

    @Insert
    Long insertTask(Stock stock);

    @Update
    void updateTask(Stock stock);

    @Delete
    void deleteTask(Stock stock);
}