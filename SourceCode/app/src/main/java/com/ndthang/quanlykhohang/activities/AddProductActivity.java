package com.ndthang.quanlykhohang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ndthang.quanlykhohang.MainActivity;
import com.ndthang.quanlykhohang.R;
import com.ndthang.quanlykhohang.databases.DatabaseHelper;
import com.ndthang.quanlykhohang.fragments.ProductFragment;
import com.ndthang.quanlykhohang.helper.Utilities;

public class AddProductActivity extends AppCompatActivity {
    EditText priceProduct;
    EditText quantityProduct;
    EditText nameProduct;
    TextView linkGoToHome;
    Button btnAddProduct;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        getUI();
        actionAnonymous();
    }
    void actionAnonymous(){
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = nameProduct.getText().toString();
                String quantityP = quantityProduct.getText().toString();
                String priceP = priceProduct.getText().toString();
                if (!ten.isEmpty() && !quantityP.isEmpty() && !priceP.isEmpty()){
                    double price = Double.parseDouble(priceP);
                    int quantity = Integer.parseInt(priceP);

                    themDuLieuSanPham(ten, quantity, price);
                    Utilities.addInfo(AddProductActivity.this, "Thêm sản phẩm thành công!");
                    Intent intent = new Intent(AddProductActivity.this, ProductFragment.class);
                    startActivity(intent);
                }
                else
                    Utilities.addInfo(AddProductActivity.this, "Có lỗi xảy ra!");
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
    private void themDuLieuSanPham(String ten, int soLuong, double gia) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PRODUCT_COLUMN_NAME, ten);
        values.put(DatabaseHelper.PRODUCT_COLUMN_QUANTITY, soLuong);
        values.put(DatabaseHelper.PRODUCT_COLUMN_PRICE, gia);

        // Thêm dữ liệu vào bảng Product
        db.insert(DatabaseHelper.TABLE_PRODUCT, null, values);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đóng kết nối database khi activity bị hủy
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
    void getUI(){
        linkGoToHome = findViewById(R.id.go_to_home);
        btnAddProduct = findViewById(R.id.btn_add_product);
        quantityProduct = findViewById(R.id.edit_text_quantity_product_add);
        nameProduct = findViewById(R.id.edit_text_name_product_add);
        priceProduct = findViewById(R.id.edit_text_price_product_add);
    }
}