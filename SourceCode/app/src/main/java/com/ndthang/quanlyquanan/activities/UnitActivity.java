package com.ndthang.quanlyquanan.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.adapters.UnitAdapter;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextViewUnit;
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    private UnitAdapter unitAdapter;
    private List<Unit> unitList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mapping();


        unitList = new ArrayList<>();
        unitAdapter = new UnitAdapter(unitList, this);
        setUp();
        // Thiết lập LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Kết nối đến Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("units");
        // Lắng nghe sự kiện khi dữ liệu thay đổi
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                unitList.clear();

                for (DataSnapshot unitSnapshot : dataSnapshot.getChildren()) {
                    Unit unit = unitSnapshot.getValue(Unit.class);
                    unitList.add(unit);
                    Log.w("Đơn vị", "Đơn vị đang có: " + unit.getName());
                }

                // Cập nhật RecyclerView khi có dữ liệu mới
                unitAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi
            }
        });

        // Kết nối Adapter với RecyclerView
        recyclerView.setAdapter(unitAdapter);
        setClick();
    }
    private void mapping(){
        // Khởi tạo RecyclerView và Adapter
        recyclerView = findViewById(R.id.recyclerViewUnit);
        btnAdd = findViewById(R.id.btn_add_unit);
        autoCompleteTextViewUnit = findViewById(R.id.autoCompleteTextViewUnit);
    }
    private void setUp(){
        // Tạo một ArrayAdapter cho AutoCompleteTextView
        ArrayAdapter<Unit> autoCompleteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, unitList);
        // Set Adapter cho AutoCompleteTextView
        autoCompleteTextViewUnit.setAdapter(autoCompleteAdapter);

    }
    private void setClick(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(UnitActivity.this);
                builder.setTitle("Nhập dữ liệu");

                // Thiết lập layout cho AlertDialog
                final EditText input = new EditText(UnitActivity.this);
                builder.setView(input);
                // Thiết lập nút OK
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newUnitName = input.getText().toString().trim();
                        if (!TextUtils.isEmpty(newUnitName)){
                            // Kết nối đến node "units" trong Firebase
                            DatabaseReference unitsRef = FirebaseDatabase.getInstance().getReference("units");
                            // Tạo đối tượng Unit mới
                            Unit newUnit = new Unit(newUnitName);

                            // Thêm mới dữ liệu vào Firebase
                            unitsRef.push().setValue(newUnit);
                            Toast.makeText(UnitActivity.this, "Dữ liệu đã nhập: " + newUnitName, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UnitActivity.this, "Vui lòng nhập đơn vị mới", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Thiết lập nút Hủy
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi người dùng nhấn nút Hủy
                        dialog.cancel();
                    }
                });

                // Hiển thị AlertDialog
                builder.show();
            }
        });


        // Sự kiện khi người dùng chọn đơn vị từ danh sách gợi ý
        autoCompleteTextViewUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit selectedUnit = (Unit) parent.getItemAtPosition(position);
                // Xử lý khi người dùng chọn đơn vị từ danh sách gợi ý
                // (ví dụ, hiển thị thông tin đơn vị lên TextView hoặc thực hiện hành động nào đó)
                Toast.makeText(UnitActivity.this, "Đã chọn đơn vị: " + selectedUnit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish(); // Kết thúc Activity hiện tại và quay trở lại MainActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}