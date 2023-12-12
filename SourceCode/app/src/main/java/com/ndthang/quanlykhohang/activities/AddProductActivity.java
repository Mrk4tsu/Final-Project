package com.ndthang.quanlykhohang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ndthang.quanlykhohang.MainActivity;
import com.ndthang.quanlykhohang.R;

public class AddProductActivity extends AppCompatActivity {
    TextView linkGoToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getUI();
        actionAnonymous();
    }
    void actionAnonymous(){
        linkGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void getUI(){
        linkGoToHome = findViewById(R.id.go_to_home);
    }
}