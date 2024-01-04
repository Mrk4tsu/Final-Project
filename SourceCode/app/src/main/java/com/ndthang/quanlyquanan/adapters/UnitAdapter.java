package com.ndthang.quanlyquanan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.List;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder>{
    private List<Unit> unitList;
    private Context context;
    public UnitAdapter(List<Unit> unitList, Context context) {
        this.unitList = unitList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Unit unit = unitList.get(position);
        holder.unitNameTextView.setText(unit.getName());
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView unitNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitNameTextView = itemView.findViewById(R.id.unitNameTextView);
        }
    }
}
