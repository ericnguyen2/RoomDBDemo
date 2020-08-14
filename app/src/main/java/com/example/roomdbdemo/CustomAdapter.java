package com.example.roomdbdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Stock> dataset;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_stockno;
        TextView tv_ticker;
        TextView tv_quantity;
        TextView tv_price;
        Button btn_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_stockno = itemView.findViewById(R.id.tv_stockno);
            this.tv_ticker = itemView.findViewById(R.id.tv_ticker);
            this.tv_quantity = itemView.findViewById(R.id.tv_quantity);
            this.tv_price = itemView.findViewById(R.id.tv_price);
            this.btn_title = itemView.findViewById(R.id.btn_title);
        }
    }

    public CustomAdapter(ArrayList<Stock> data, Context context) {
        this.dataset = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_stock, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView tv_stockno = holder.tv_stockno;
        TextView tv_ticker = holder.tv_ticker;
        TextView tv_quantity = holder.tv_quantity;
        TextView tv_price = holder.tv_price;
        Button btn_title = holder.btn_title;

        tv_stockno.setText(dataset.get(position).stockno + "");
        tv_ticker.setText(dataset.get(position).ticker + "");
        tv_quantity.setText(dataset.get(position).quantity + "");
        tv_price.setText(dataset.get(position).price + "");

        btn_title.setText(dataset.get(position).ticker.toUpperCase().charAt(0) + "");

        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        btn_title.setBackgroundColor(Color.rgb(red, green, blue));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
