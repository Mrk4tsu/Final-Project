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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndthang.quanlyquanan.R;
import com.ndthang.quanlyquanan.adapters.DrinkAdapter;
import com.ndthang.quanlyquanan.models.Drink;
import com.ndthang.quanlyquanan.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {
    FloatingActionButton btnAddDrink;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private DrinkAdapter adapter;
    private List<Drink> drinkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SearchView searchView = findViewById(R.id.search_drink);
        recyclerView = findViewById(R.id.recyclerViewDrink);
        btnAddDrink = findViewById(R.id.btn_add_drink);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("drinks");

        drinkList = new ArrayList<>();
        adapter = new DrinkAdapter(this ,drinkList);
        recyclerView.setAdapter(adapter);

        fetchDataFromFirebase();
        fetchUnitsFromFirebase();

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

    public void addDrink(View view) {
        EditText editTextName = findViewById(R.id.editTextSearchDrink);
        Spinner spinnerUnits = findViewById(R.id.spinnerUnits);

        String drinkName = editTextName.getText().toString();
        String selectedUnitId = spinnerUnits.getSelectedItem().toString();

        if (!TextUtils.isEmpty(drinkName) && !TextUtils.isEmpty(selectedUnitId)) {
            // Kết nối đến node "units" trong Firebase
            DatabaseReference drinksRef = FirebaseDatabase.getInstance().getReference("drinks");
            // Create a new Drink object
            Drink newDrink = new Drink(drinkName, selectedUnitId);

            // Thêm mới dữ liệu vào Firebase
            drinksRef.push().setValue(newDrink);

//            // Add the new drink to Firebase directly under "drinks"
//            String key = databaseReference.child("drinks").push().getKey();
//            databaseReference.child("drinks").child("drink" + key).setValue(newDrink);

            // Clear input fields
            editTextName.setText("");
            spinnerUnits.setSelection(0); // Assuming the first item in the spinner is a default or prompt item
        } else {
            // Display an error message or handle the case where either the name or unit is empty
            Toast.makeText(this, "Please enter both drink name and select a unit", Toast.LENGTH_SHORT).show();
        }
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DrinkActivity.this, android.R.layout.simple_spinner_item, unitNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner spinnerUnits = findViewById(R.id.spinnerUnits);
                spinnerUnits.setAdapter(adapter);
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