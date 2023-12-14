package com.ndthang.quanlykhohang.activities.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.Utilities;
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
    TextView textViewMessage;
    Button buttonYes, buttonNo, btnDeleteAll;
    EditText editTextSearch;
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

            @Override
            public void deleteProduct(Product product) {
                clickDeleteProduct(product);
            }
        });
        loadData();
        actionAnonymous();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listViewProduct.setLayoutManager(linearLayoutManager);
        listViewProduct.setAdapter(productAdapter);

        return view;
    }
    private void actionAnonymous(){
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeleteAllProduct();
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchProduct();
                }
                return false;
            }
        });
    }

    private void handleSearchProduct() {
        String keyword = editTextSearch.getText().toString().trim();
        mProductList = new ArrayList<>();
        mProductList = DatabaseHelper.getInstance(getContext()).productDAO().searchProduct(keyword);
        productAdapter.setData(mProductList);


    }
    private void clickDeleteAllProduct() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        dialog.setContentView(R.layout.custom_dialog_infomation);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        textViewMessage = dialog.findViewById(R.id.text_message);
        buttonYes = dialog.findViewById(R.id.btnYes);
        buttonNo = dialog.findViewById(R.id.btnNo);

        textViewMessage.setText("Bạn có chắc chắn xóa toàn bộ sản phẩm?");

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper.getInstance(getContext()).productDAO().deleteAllProduct();
                Utilities.addInfo(getContext(), "Đã xóa toàn bộ sản phẩm");

                loadData();
                dialog.dismiss();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void clickDeleteProduct(final Product product){
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        dialog.setContentView(R.layout.custom_dialog_infomation);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        textViewMessage = dialog.findViewById(R.id.text_message);
        buttonYes = dialog.findViewById(R.id.btnYes);
        buttonNo = dialog.findViewById(R.id.btnNo);

        textViewMessage.setText("Bạn có chắc chắn xóa sản phẩm này?");

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper.getInstance(getContext()).productDAO().deleteProduct(product);
                Utilities.addInfo(getContext(), "Đã xóa sản phẩm");

                loadData();
                dialog.dismiss();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
        btnDeleteAll = view.findViewById(R.id.btn_delete_all);
        editTextSearch = view.findViewById(R.id.input_name_product_to_search);
    }
    private void loadData(){
        mProductList = DatabaseHelper.getInstance(getContext()).productDAO().getListProduct();
        productAdapter.setData(mProductList);
    }
}
