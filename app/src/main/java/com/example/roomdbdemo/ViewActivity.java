package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Stock> stockArrayList, stockArrayList_search;
    EditText edt_search;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                Filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
            stockArrayList_search = new ArrayList<>();

            for (int i = 0; i < stockList.size(); i++) {
                stockArrayList.add(stockList.get(i));
                stockArrayList_search.add(stockList.get(i));
            }
            
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            customAdapter = new CustomAdapter(stockArrayList, ViewActivity.this);
            recyclerView.setAdapter(customAdapter);
        }
    }

    // Filter data
    public void Filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Log.d("filter", charText + "");

        stockArrayList.clear();

        if (charText.length() == 0) {
            stockArrayList.addAll(stockArrayList_search);
            Log.d("load data", "All");
        }
        else {
            Log.d("load data", "Filtered");

            for (Stock stock:stockArrayList_search) {
                if (stock.ticker.toLowerCase(Locale.getDefault()).contains(charText)
                || String.valueOf(stock.quantity).toLowerCase(Locale.getDefault()).contains(charText)) {
                    stockArrayList.add(stock);
                }
            }
        }
        customAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}