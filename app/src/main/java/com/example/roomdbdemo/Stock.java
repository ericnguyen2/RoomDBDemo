package com.example.roomdbdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Stock {

    @PrimaryKey
    public int stockno;

    @ColumnInfo(name = "ticker")
    public String ticker;

    @ColumnInfo(name = "quantity")
    public Double quantity;

    @ColumnInfo(name = "price")
    public Double price;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "type")
    public String type;

    public Stock(int stockno, String ticker, Double quantity, Double price, String date, String type) {
        this.stockno = stockno;
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.type = type;
    }
}