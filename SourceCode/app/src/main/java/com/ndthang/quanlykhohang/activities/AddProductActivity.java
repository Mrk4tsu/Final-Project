package com.ndthang.quanlykhohang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ndthang.quanlykhohang.MainActivity;
import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.Utilities;
import com.ndthang.quanlykhohang.activities.fragments.HomeFragment;
import com.ndthang.quanlykhohang.activities.fragments.ProductFragment;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;
import com.ndthang.quanlykhohang.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    EditText priceProduct;
    EditText quantityProduct;
    EditText nameProduct;
    TextView linkGoToHome;
    Button btnAddProduct;
    List<Product> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        getUI();

        actionAnonymous();
    }
    void actionAnonymous(){
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
        linkGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addProduct() {
        String strName = nameProduct.getText().toString().trim();
        String strQuantity = quantityProduct.getText().toString().trim();
        String strPrice = priceProduct.getText().toString().trim();
        if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strQuantity)  || TextUtils.isEmpty(strPrice) ){
            return;
        }
        int quantity = Integer.parseInt(strQuantity);
        double price = Double.parseDouble(strPrice);
        Product product = new Product(strName, quantity, price);
        DatabaseHelper.getInstance(this).productDAO().insertProduct(product);
        Utilities.addInfo(this, "Thêm sản phẩm thành công!");

        nameProduct.setText("");
        quantityProduct.setText("");
        priceProduct.setText("");

        hideSoftKeyboard();
    }
    void getUI(){
        linkGoToHome = findViewById(R.id.go_to_home);
        btnAddProduct = findViewById(R.id.btn_add_product);
        quantityProduct = findViewById(R.id.edit_text_quantity_product_add);
        nameProduct = findViewById(R.id.edit_text_name_product_add);
        priceProduct = findViewById(R.id.edit_text_price_product_add);
    }
    public void hideSoftKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}