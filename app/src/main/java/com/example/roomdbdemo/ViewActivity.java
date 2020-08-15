package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Stock> stockArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {
        StockRepository stockRepository;
        List<Stock> stockList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            stockRepository = new StockRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stockList = stockRepository.getStocks();
            stockArrayList = new ArrayList<>();

            for (int i = 0; i < stockList.size(); i++) {
                stockArrayList.add(stockList.get(i));
            }
            
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            CustomAdapter customAdapter = new CustomAdapter(stockArrayList, ViewActivity.this);
            recyclerView.setAdapter(customAdapter);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}