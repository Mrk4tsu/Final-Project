package com.ndthang.quanlykhohang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.Utilities;
import com.ndthang.quanlykhohang.activities.fragments.ProductFragment;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;
import com.ndthang.quanlykhohang.entities.Product;

public class UpdateProductActivity extends AppCompatActivity {
    EditText priceProduct;
    EditText quantityProduct;
    EditText nameProduct;
    TextView linkGoToList;
    Button btnUpdateProduct;
    Product mProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        getUI();
        mProduct = (Product) getIntent().getExtras().get("object_product");
        if (mProduct != null){
            nameProduct.setText(mProduct.getName());
            quantityProduct.setText(String.valueOf(mProduct.getQuantity()));
            priceProduct.setText(String.valueOf(mProduct.getPrice()));
        }

        actionAnonymous();
    }
    void actionAnonymous(){
        linkGoToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProductActivity.this, ProductFragment.class);
                startActivity(intent);
            }
        });
        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });
    }
    private void updateProduct() {
        String strName = nameProduct.getText().toString().trim();
        String strQuantity = quantityProduct.getText().toString().trim();
        String strPrice = priceProduct.getText().toString().trim();
        if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strQuantity)  || TextUtils.isEmpty(strPrice) ){
            return;
        }
        int quantity = Integer.parseInt(strQuantity);
        double price = Double.parseDouble(strPrice);
        //Update product
        mProduct.setName(strName);
        mProduct.setQuantity(quantity);
        mProduct.setPrice(price);

        DatabaseHelper.getInstance(this).productDAO().updateProduct(mProduct);
        Utilities.addInfo(this, "Chỉnh sửa sản phẩm thành công!");

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

    void getUI(){
        linkGoToList = findViewById(R.id.go_to_list);
        btnUpdateProduct = findViewById(R.id.btn_update_product);
        quantityProduct = findViewById(R.id.edit_text_quantity_product_update);
        nameProduct = findViewById(R.id.edit_text_name_product_update);
        priceProduct = findViewById(R.id.edit_text_price_product_update);
    }
}