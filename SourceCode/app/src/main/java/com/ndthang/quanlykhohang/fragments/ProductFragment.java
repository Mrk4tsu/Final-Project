package com.ndthang.quanlykhohang.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.adapters.ProductAdapter;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;

public class ProductFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ProductAdapter productAdapter;
    private ListView listViewProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        db = dbHelper.getWritableDatabase();

        // Lấy dữ liệu từ bảng Product
        Cursor cursor = layDuLieuTuBangProduct();

        // Gán Adapter cho ListView
        productAdapter = new ProductAdapter(getActivity(), cursor);
        listViewProduct = view.findViewById(R.id.listViewProduct);
        listViewProduct.setAdapter(productAdapter);

        return view;
    }
    private Cursor layDuLieuTuBangProduct() {
        String query = "SELECT id as _id, name, quantity, price FROM " + DatabaseHelper.TABLE_PRODUCT;
        return db.rawQuery(query, null);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
