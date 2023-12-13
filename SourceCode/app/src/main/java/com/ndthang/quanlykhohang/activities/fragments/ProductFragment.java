package com.ndthang.quanlykhohang.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.adapters.ProductAdapter;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;
import com.ndthang.quanlykhohang.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    private RecyclerView listViewProduct;
    private ProductAdapter productAdapter;
    public List<Product> mProductList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        mProductList = new ArrayList<>();
        getUI(view);

        mProductList = DatabaseHelper.getInstance(getContext()).productDAO().getListProduct();

        productAdapter = new ProductAdapter();
        productAdapter.setData(mProductList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewProduct.setLayoutManager(linearLayoutManager);
        listViewProduct.setAdapter(productAdapter);


        return view;
    }
    public void getUI(@NonNull View view){
        listViewProduct = view.findViewById(R.id.recycleViewProduct);
    }
}
