package com.ndthang.quanlyquanan.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateDrinkActivity extends AppCompatActivity {
    private EditText editTextNewName;
    private Spinner spinnerNewUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drink);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String drinkId = intent.getStringExtra("drinkId");
        String drinkName = intent.getStringExtra("drinkName");
        String drinkUnit = intent.getStringExtra("drinkUnit");

        // Hiển thị dữ liệu trên giao diện người dùng (có thể sử dụng EditText, Spinner, v.v.)
        EditText editTextName = findViewById(R.id.editTextSearchDrink);
        spinnerNewUnit = findViewById(R.id.spinnerUnits);
        editTextName.setText(drinkName);
        // Bạn có thể thêm logic để cập nhật thông tin khi người dùng nhấn nút cập nhật
        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin mới từ người dùng
                String newName = editTextNewName.getText().toString();
                String newUnit = spinnerNewUnit.getSelectedItem().toString();

                // Kiểm tra nếu tên mới không rỗng
                if (!TextUtils.isEmpty(newName)) {
                    // Thực hiện cập nhật thông tin trong Firebase (giả sử bạn có DatabaseReference là databaseReference)
                    updateDrink(drinkId, newName, newUnit);

                    // Hiển thị thông báo cập nhật thành công và kết thúc Activity
                    Toast.makeText(UpdateDrinkActivity.this, "Drink updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Hiển thị thông báo lỗi khi tên mới rỗng
                    Toast.makeText(UpdateDrinkActivity.this, "Please enter a new name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fetchUnitsFromFirebase();
    }
    // Phương thức để cập nhật thông tin đồ uống trong Firebase
    private void updateDrink(String drinkId, String newName, String newUnit) {
        DatabaseReference drinkRef = FirebaseDatabase.getInstance().getReference().child("drinks").child(drinkId);

        // Tạo một Map để cập nhật các thuộc tính mới
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        updateData.put("unitName", newUnit);

        // Cập nhật dữ liệu trong Firebase Realtime Database
        drinkRef.updateChildren(updateData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Cập nhật thành công, bạn có thể thực hiện các hành động khác nếu cần
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi cập nhật thất bại
                        Toast.makeText(UpdateDrinkActivity.this, "Failed to update drink", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void fetchUnitsFromFirebase() {
        DatabaseReference unitsReference = FirebaseDatabase.getInstance().getReference().child("units");
        unitsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> unitNames = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Unit unit = snapshot.getValue(Unit.class);
                    if (unit != null) {
                        unitNames.add(unit.getName());
                    }
                }

                // Create an ArrayAdapter and set it to the Spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateDrinkActivity.this, android.R.layout.simple_spinner_item, unitNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerNewUnit.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}