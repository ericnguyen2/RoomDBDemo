package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText edt_stockno, edt_ticker, edt_quantity, edt_price, edt_date;
    Button btn_submit;
    RadioButton rbtn_buy, rbtn_sell;
    String sstockno, sticker, squantity, sprice, sdate, stype = "Buy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edt_stockno = findViewById(R.id.edt_stockno);
        edt_ticker = findViewById(R.id.edt_ticker);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_price = findViewById(R.id.edt_price);
        edt_date = findViewById(R.id.edt_date);
        rbtn_buy = findViewById(R.id.rbtn_buy);
        rbtn_sell = findViewById(R.id.rbtn_sell);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_stockno.getText().toString().isEmpty()
                        || edt_ticker.getText().toString().isEmpty()
                        || edt_quantity.getText().toString().isEmpty()
                        || edt_price.getText().toString().isEmpty()
                        || edt_date.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill Detail.", Toast.LENGTH_LONG).show();
                } else {
                    sstockno = edt_stockno.getText().toString().trim();
                    sticker = edt_ticker.getText().toString().trim();
                    squantity = edt_quantity.getText().toString().trim();
                    sprice = edt_price.getText().toString().trim();
                    sdate = edt_date.getText().toString().trim();

                    if (rbtn_buy.isChecked()) {
                        stype = "Buy";
                    }
                    else if (rbtn_sell.isChecked()) {
                        stype = "Sell";
                    }

                    StockRepository stockRepository = new StockRepository(getApplicationContext());
                    Stock stock = new Stock(Integer.parseInt(sstockno),
                            sticker,
                            Double.parseDouble(squantity),
                            Double.parseDouble(sprice),
                            sdate,
                            stype);
                    stockRepository.InsertTask(stock);

                    edt_stockno.setText("");
                    edt_ticker.setText("");
                    edt_quantity.setText("");
                    edt_price.setText("");
                    edt_date.setText("");
                }
            }
        });
    }
}