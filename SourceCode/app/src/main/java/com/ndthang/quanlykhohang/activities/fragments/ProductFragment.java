package com.ndthang.quanlykhohang.activities.fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.ndthang.quanlykhohang.activities.UpdateProductActivity;
import com.ndthang.quanlykhohang.adapters.ProductAdapter;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;
import com.ndthang.quanlykhohang.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    private static final int MY_REQUEST_CODE = 10;
    private RecyclerView listViewProduct;
    private ProductAdapter productAdapter;
    public List<Product> mProductList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        mProductList = new ArrayList<>();
        getUI(view);

        productAdapter = new ProductAdapter(new ProductAdapter.IClickItemProduct() {
            @Override
            public void updateProduct(Product product) {
                clickUpdateProduct(product);
            }
        });
        loadData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewProduct.setLayoutManager(linearLayoutManager);
        listViewProduct.setAdapter(productAdapter);

        return view;
    }
    private void clickUpdateProduct(Product product){
        Intent intent = new Intent(getActivity(), UpdateProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);

        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }

    }

    public void getUI(@NonNull View view){
        listViewProduct = view.findViewById(R.id.recycleViewProduct);
    }
    private void loadData(){
        mProductList = DatabaseHelper.getInstance(getContext()).productDAO().getListProduct();
        productAdapter.setData(mProductList);
    }
}
