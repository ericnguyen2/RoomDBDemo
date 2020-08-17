package com.example.roomdbdemo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StockDAO {

    @Insert
    Long insertTask(Stock stock) throws Exception;

    @Update
    void updateTask(Stock stock);

    @Delete
    void deleteTask(Stock stock);

    @Query("SELECT * FROM stock ORDER BY stockno ASC")
    List<Stock> getAll();
}