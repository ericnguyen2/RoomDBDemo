package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StockDetailActivity extends AppCompatActivity {

    EditText edt_stockno, edt_ticker, edt_quantity, edt_price;
    Button btn_update;

    int sstockno;
    String sticker = "";
    Double squantity;
    Double sprice;

    String sstockno_to_update = "", sticker_to_update = "", squantity_to_update = "", ssprice_to_update = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        // Find view by ids
        edt_stockno = findViewById(R.id.edt_stockno);
        edt_ticker = findViewById(R.id.edt_ticker);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_price = findViewById(R.id.edt_price);
        btn_update = findViewById(R.id.btn_update);

        // Get values from custom adapter
        Bundle data = getIntent().getExtras();
        if (data != null) {
            sstockno = data.getInt("stockno");
            sticker = data.getString("ticker");
            squantity = data.getDouble("quantity");
            sprice = data.getDouble("price");
        }

        // Set values to UI
        edt_stockno.setText(sstockno + "");
        edt_ticker.setText(sticker + "");
        edt_quantity.setText(squantity + "");
        edt_price.setText(sprice + "");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_ticker.getText().toString().isEmpty() ||
                    edt_quantity.getText().toString().isEmpty() ||
                    edt_price.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill Detail", Toast.LENGTH_LONG).show();
                }
                else {
                    sstockno_to_update = edt_stockno.getText().toString().trim();
                    sticker_to_update = edt_ticker.getText().toString().trim();
                    squantity_to_update = edt_quantity.getText().toString().trim();
                    ssprice_to_update = edt_price.getText().toString().trim();
                }

                StockRepository stockRepository = new StockRepository(getApplicationContext());
                Stock stock = new Stock(Integer.parseInt(sstockno_to_update),
                                        sticker_to_update,
                                        Double.parseDouble(squantity_to_update),
                                        Double.parseDouble(ssprice_to_update));
                stockRepository.UpdateTask(stock);
                Toast.makeText(getApplicationContext(), "Stock Updated", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}