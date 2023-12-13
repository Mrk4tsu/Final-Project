package com.ndthang.quanlykhohang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ndthang.quanlykhohang.MainActivity;
import com.ndthang.quanlykhohang.R;

public class LoginActivity extends AppCompatActivity {
    private TextView linkGoRegister;
    private Button btnActionLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUI();
        actionAnonymous();
    }
    private void actionAnonymous() {
        linkGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnActionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getUI(){
        linkGoRegister = findViewById(R.id.link_go_register);
        btnActionLogin = findViewById(R.id.btn_action_login);
    }
}