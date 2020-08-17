package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class StockDetailActivity extends AppCompatActivity {

    EditText edt_stockno, edt_ticker, edt_quantity, edt_price, edt_date;
    Button btn_update;
    Button btn_delete;

    int sstockno;
    String sticker = "";
    Double squantity;
    Double sprice;
    String sdate = "";
    String stype = "";

    String sstockno_to_update = "", sticker_to_update = "", squantity_to_update = "", sprice_to_update = "", sdate_to_update = "", stype_to_update = "";
    String sstockno_to_delete = "", sticker_to_delete = "", squantity_to_delete = "", sprice_to_delete = "", sdate_to_delete = "", stype_to_delete = "";

    RadioButton rbtn_buy, rbtn_sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        // Find view by ids
        edt_stockno = findViewById(R.id.edt_stockno);
        edt_ticker = findViewById(R.id.edt_ticker);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_price = findViewById(R.id.edt_price);
        edt_date = findViewById(R.id.edt_date);
        rbtn_buy = findViewById(R.id.rbtn_buy);
        rbtn_sell = findViewById(R.id.rbtn_sell);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        // Get values from custom adapter
        Bundle data = getIntent().getExtras();
        if (data != null) {
            sstockno = data.getInt("stockno");
            sticker = data.getString("ticker");
            squantity = data.getDouble("quantity");
            sprice = data.getDouble("price");
            sdate = data.getString("date");
            stype = data.getString("type");
        }

        // Set values to UI
        edt_stockno.setText(sstockno + "");
        edt_ticker.setText(sticker + "");
        edt_quantity.setText(squantity + "");
        edt_price.setText(sprice + "");
        edt_date.setText(sdate + "");

        if (stype.trim().toLowerCase().equalsIgnoreCase("buy")) {
            rbtn_buy.setChecked(true);
        }
        else if (stype.trim().toLowerCase().equalsIgnoreCase("sell")) {
            rbtn_sell.setChecked(true);
        }

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_ticker.getText().toString().isEmpty() ||
                    edt_quantity.getText().toString().isEmpty() ||
                    edt_price.getText().toString().isEmpty() ||
                    edt_date.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill Detail.", Toast.LENGTH_LONG).show();
                }
                else {
                    sstockno_to_update = edt_stockno.getText().toString().trim();
                    sticker_to_update = edt_ticker.getText().toString().trim();
                    squantity_to_update = edt_quantity.getText().toString().trim();
                    sprice_to_update = edt_price.getText().toString().trim();
                    sdate_to_update = edt_date.getText().toString().trim();

                    if (rbtn_buy.isChecked()) {
                        stype_to_update = "Buy";
                    }
                    else if (rbtn_sell.isChecked()) {
                        stype_to_update = "Sell";
                    }
                }

                StockRepository stockRepository = new StockRepository(getApplicationContext());

                Stock stock = new Stock(Integer.parseInt(sstockno_to_update),
                                        sticker_to_update,
                                        Double.parseDouble(squantity_to_update),
                                        Double.parseDouble(sprice_to_update),
                                        sdate_to_update,
                                        stype_to_update);
                stockRepository.UpdateTask(stock);
                Toast.makeText(getApplicationContext(), "Transaction Updated.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstockno_to_delete = edt_stockno.getText().toString().trim();
                sticker_to_delete = edt_ticker.getText().toString().trim();
                squantity_to_delete = edt_quantity.getText().toString().trim();
                sprice_to_delete = edt_price.getText().toString().trim();
                sdate_to_delete = edt_date.getText().toString().trim();

                if (rbtn_buy.isChecked()) {
                    stype_to_delete = "Buy";
                }
                else if (rbtn_sell.isChecked()) {
                    stype_to_delete = "Sell";
                }

                Stock stock_to_delete = new Stock(Integer.parseInt(sstockno_to_delete),
                        sticker_to_delete,
                        Double.parseDouble(squantity_to_delete),
                        Double.parseDouble(sprice_to_delete),
                        sdate_to_delete,
                        stype_to_delete);
                generate_delete_dialog(stock_to_delete);
            }
        });
    }

    // Generate Alert Dialog
    public void generate_delete_dialog(final Stock stock) {
        final Stock stock_about_to_delete = stock;

        AlertDialog.Builder builder = new AlertDialog.Builder(StockDetailActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("WARNING");
        builder.setMessage("Delete Transaction #" + stock_about_to_delete.stockno + "?");
        builder.setIcon(android.R.drawable.ic_delete);

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StockRepository stockRepository = new StockRepository(getApplicationContext());
                stockRepository.DeleteTask(stock_about_to_delete);
                Toast.makeText(StockDetailActivity.this, "Transaction Deleted.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Dismiss
            }
        });

        AlertDialog deleteDialog = builder.create();
        deleteDialog.show();
    }
}