package com.ndthang.quanlyquanan.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.adapters.DrinkAdapter;
import com.ndthang.quanlyquanan.adapters.ManagerAdapter;
import com.ndthang.quanlyquanan.models.Drink;
import com.ndthang.quanlyquanan.models.Manager;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class ImportDrinkActivity extends AppCompatActivity {
    FloatingActionButton btnAddDrink;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ManagerAdapter adapter;
    private List<Manager> drinkList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_drink);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = findViewById(R.id.recyclerViewManager);
        SearchView searchView = findViewById(R.id.search_detail_manager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnAddDrink = findViewById(R.id.btn_add_manager);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("manager");

        drinkList = new ArrayList<>();
        adapter = new ManagerAdapter(this ,drinkList);
        recyclerView.setAdapter(adapter);
        fetchDataFromFirebase();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchDataFromFirebase(newText);
                return true;
            }
        });
        btnAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void fetchDataFromFirebase(String query) {
        databaseReference.orderByChild("name").startAt(query).endAt(query + "\uf8ff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        drinkList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Manager drink = snapshot.getValue(Manager.class);
                            drinkList.add(drink);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
    }
    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                drinkList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Manager drink = snapshot.getValue(Manager.class);
                    drinkList.add(drink);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
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