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
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.adapters.DetailAdapter;
import com.ndthang.quanlyquanan.adapters.DrinkAdapter;
import com.ndthang.quanlyquanan.models.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkDetailActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private DetailAdapter adapter;
    private List<Drink> drinkList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        SearchView searchView = findViewById(R.id.search_detail_drink);
        recyclerView = findViewById(R.id.recyclerViewDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("drinks");
        drinkList = new ArrayList<>();
        adapter = new DetailAdapter(this, drinkList);
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
    }
    private void fetchDataFromFirebase(String query) {
        databaseReference.orderByChild("name").startAt(query).endAt(query + "\uf8ff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        drinkList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Drink drink = snapshot.getValue(Drink.class);
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
                    Drink drink = snapshot.getValue(Drink.class);
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