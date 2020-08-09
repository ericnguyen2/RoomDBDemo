package com.example.roomdbdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface StockDAO {

    @Insert
    Long insertStock(Stock stock);

    @Update
    void updateStock(Stock stock);

    @Delete
    void deleteStock(Stock stock);
}
