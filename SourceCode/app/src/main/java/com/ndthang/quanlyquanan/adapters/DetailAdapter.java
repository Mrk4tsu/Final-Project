package com.ndthang.quanlyquanan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.models.Drink;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
    Context context;
    private List<Drink> drinks;
    public DetailAdapter(Context context,List<Drink> drinks) {
        this.drinks = drinks;
        this.context = context;
    }
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new DetailAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        String unit = drink.getUnitName();
        holder.name.setText(drink.getName());
        holder.priceDrink.setText(String.valueOf(drink.getPrice()));
        holder.totalPrice.setText("Tổng: " + String.valueOf(drink.getPrice() * drink.getQuantity()));
        holder.quantity.setText(String.valueOf(drink.getQuantity()) + " " + unit);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView priceDrink;
        TextView quantity;
        TextView totalPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameDrink);
            priceDrink = itemView.findViewById(R.id.priceDrink);
            quantity = itemView.findViewById(R.id.tv_quantity);
            totalPrice = itemView.findViewById(R.id.tv_total_amount);
        }
    }
}
