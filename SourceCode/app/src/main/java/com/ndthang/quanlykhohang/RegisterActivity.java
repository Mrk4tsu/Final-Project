package com.ndthang.quanlykhohang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private TextView linkGoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getUI();

        actionAnonymous();
    }
    private void actionAnonymous(){
        Intent goToHome = new Intent(RegisterActivity.this, MainActivity.class);
        Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        linkGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goToLogin);
            }
        });
    }
    void getUI(){
        linkGoLogin = findViewById(R.id.link_go_sign_in);
    }
}