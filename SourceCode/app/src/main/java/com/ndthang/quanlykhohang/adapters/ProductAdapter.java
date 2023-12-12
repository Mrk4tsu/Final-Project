package com.ndthang.quanlykhohang.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;

public class ProductAdapter extends CursorAdapter {
    public ProductAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewProductName = view.findViewById(R.id.textViewProductName);
        TextView textViewProductQuantity = view.findViewById(R.id.textViewProductQuantity);
        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);

        // Kiểm tra xem cột có tồn tại trong Cursor không
        int indexProductName = cursor.getColumnIndex(DatabaseHelper.PRODUCT_COLUMN_NAME);
        int indexProductQuantity = cursor.getColumnIndex(DatabaseHelper.PRODUCT_COLUMN_QUANTITY);
        int indexProductPrice = cursor.getColumnIndex(DatabaseHelper.PRODUCT_COLUMN_PRICE);

        if (indexProductName != -1) {
            String productName = cursor.getString(indexProductName);
            textViewProductName.setText(productName);
        }

        if (indexProductQuantity != -1) {
            int productQuantity = cursor.getInt(indexProductQuantity);
            textViewProductQuantity.setText("Quantity: " + productQuantity);
        }

        if (indexProductPrice != -1) {
            double productPrice = cursor.getDouble(indexProductPrice);
            textViewProductPrice.setText("Price: " + productPrice);
        }
    }
}
