package com.ndthang.quanlyquanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.activities.DrinkActivity;
import com.ndthang.quanlyquanan.activities.DrinkDetailActivity;
import com.ndthang.quanlyquanan.activities.ImportDrinkActivity;
import com.ndthang.quanlyquanan.activities.UnitActivity;
import com.ndthang.quanlyquanan.adapters.UnitAdapter;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout goToUnitActivity;
    LinearLayout goGoToDrinkActivity;
    LinearLayout goGoToImportActivity;
    LinearLayout goGoToManagerActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUI();
        goToUnitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UnitActivity.class);
                startActivity(intent);
            }
        });
        goGoToDrinkActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivity(intent);
            }
        });
        goGoToImportActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkDetailActivity.class);
                startActivity(intent);
            }
        });
        goGoToManagerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImportDrinkActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getUI(){
        goToUnitActivity = findViewById(R.id.go_to_unitactivity);
        goGoToDrinkActivity = findViewById(R.id.go_to_drink_activity);
        goGoToImportActivity = findViewById(R.id.go_to_import_activity);
        goGoToManagerActivity = findViewById(R.id.go_to_manager_activity);
    }
}