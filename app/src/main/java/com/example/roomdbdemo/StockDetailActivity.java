package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StockDetailActivity extends AppCompatActivity {

    EditText edt_stockno, edt_ticker, edt_quantity, edt_price;
    Button btn_update;
    Button btn_delete;

    int sstockno;
    String sticker = "";
    Double squantity;
    Double sprice;

    String sstockno_to_update = "", sticker_to_update = "", squantity_to_update = "", ssprice_to_update = "";
    String sstockno_to_delete = "", sticker_to_delete = "", squantity_to_delete = "", ssprice_to_delete = "";

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
        btn_delete = findViewById(R.id.btn_delete);

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
                    Toast.makeText(getApplicationContext(), "Fill Detail.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Stock Updated.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstockno_to_delete = edt_stockno.getText().toString().trim();
                sticker_to_delete = edt_ticker.getText().toString().trim();
                squantity_to_delete = edt_quantity.getText().toString().trim();
                ssprice_to_delete = edt_price.getText().toString().trim();

                Stock stock_to_delete = new Stock(Integer.parseInt(sstockno_to_delete),
                        sticker_to_delete,
                        Double.parseDouble(squantity_to_delete),
                        Double.parseDouble(ssprice_to_delete));
                generate_delete_dialog(stock_to_delete);
            }
        });
    }

    // Generate Alert Dialog
    public void generate_delete_dialog(final Stock stock) {
        final Stock stock_about_to_delete = stock;

        AlertDialog.Builder builder = new AlertDialog.Builder(StockDetailActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("WARNING");
        builder.setMessage("Delete " + stock_about_to_delete.ticker + "?");
        builder.setIcon(android.R.drawable.ic_delete);

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StockRepository stockRepository = new StockRepository(getApplicationContext());
                stockRepository.DeleteTask(stock_about_to_delete);
                Toast.makeText(StockDetailActivity.this, "Stock Deleted.", Toast.LENGTH_LONG).show();
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