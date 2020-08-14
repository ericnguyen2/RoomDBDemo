package com.example.roomdbdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

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

    // Insert Task
    public void InsertTask(final Stock stock) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                stockDatabase.stockDAO().insertTask(stock);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, stock.ticker + " is inserted", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    // Get Data Task
    public List<Stock> getStocks() {
        List<Stock> stockList = stockDatabase.stockDAO().getAll();
        return stockList;
    }
}