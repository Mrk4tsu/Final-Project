package com.ndthang.quanlyquanan.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.models.Manager;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder>{
    Context context;
    private List<Manager> managerList;

    public ManagerAdapter(Context context,List<Manager> managers) {
        this.managerList = managers;
        this.context = context;
    }

    @Override
    public ManagerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager, parent, false);
        return new ManagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManagerAdapter.ViewHolder holder, int position) {
        Manager manager = managerList.get(position);
        holder.name.setText(manager.getName());
        holder.quantity.setText("Còn " + manager.getQuantity() + " " + manager.getUnit());

    }

    @Override
    public int getItemCount() {
        return managerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView quantity;
        AppCompatImageButton updateButton;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_drink);
            quantity = itemView.findViewById(R.id.manager_quantity);
        }
    }
}
