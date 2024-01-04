package com.ndthang.quanlyquanan.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.activities.UpdateDrinkActivity;
import com.ndthang.quanlyquanan.models.Drink;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    Context context;
    private List<Drink> drinks;

    public DrinkAdapter(Context context,List<Drink> drinks) {
        this.drinks = drinks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.nameTextView.setText(drink.getName());
        holder.unitTextView.setText(drink.getUnitName());

        final int itemPosition = position; // final variable to use in inner class

        // Gán sự kiện cho nút "Update"
        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, UpdateDrinkActivity.class);
                    intent.putExtra("drinkId", "drink" + itemPosition);
                    intent.putExtra("drinkName", drink.getName());
                    intent.putExtra("drinkUnit", drink.getUnitName());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView unitTextView;
        AppCompatImageButton updateButton;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            unitTextView = itemView.findViewById(R.id.unitTextView);
            updateButton = itemView.findViewById(R.id.updateDrink);
        }
    }
}
